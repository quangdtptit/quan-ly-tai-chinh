package com.example.service.impl;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.SalaryEntity;
import com.example.model.StaffEntity;
import com.example.model.WorkEntity;
import com.example.model.dto.SalaryDTO;
import com.example.model.dto.WorkDTO;
import com.example.repository.SalaryRepository;
import com.example.repository.StaffRepository;
import com.example.repository.WorkRepository;
import com.example.service.SalaryService;
import com.example.utils.ExcelUtil;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private WorkRepository workRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<SalaryDTO> findAllSalaryByMonth(Integer month) {
		String sql = "SELECT NV.maNhanVien, NV.hoTen, BL.maBangLuong, BL.thuong, BL.phat, A.soCong, NV.role, BL.status\r\n"
				+ "FROM tbl_bangluong AS BL, tbl_nhanvien AS NV,(SELECT BC.maBangLuong, SUM(BC.soCong) as soCong \r\n"
				+ "									FROM tbl_bangcong AS BC\r\n"
				+ "									WHERE DATEPART(month, BC.[day]) = :month\r\n"
				+ "									GROUP BY BC.maBangLuong) AS A\r\n"
				+ "WHERE BL.maBangLuong = A.maBangLuong\r\n" + "AND BL.maNhanVien = NV.maNhanVien";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("month", month);
		List<Object[]> objs = query.getResultList();
		List<SalaryDTO> result = new ArrayList<>();
		for (Object[] obj : objs) {
			SalaryDTO salaryDTO = new SalaryDTO();
			salaryDTO.setIdStaff(Integer.parseInt(obj[0].toString()));
			salaryDTO.setNameStaff(obj[1].toString());
			salaryDTO.setIdSalary(Integer.parseInt(obj[2].toString()));
			salaryDTO.setReward(Integer.parseInt(obj[3].toString()));
			salaryDTO.setPunish(Integer.parseInt(obj[4].toString()));
			salaryDTO.setNumberWork(Double.parseDouble(obj[5].toString()));
			salaryDTO.setRoleStaff(obj[6].toString());
			salaryDTO.setStatus(obj[7].toString());
			result.add(salaryDTO);
		}
		return result;
	}

	@Transactional
	@Override
	public boolean uploadFile(File file, Integer month, Integer year) {
		try {
			ExcelUtil excelUtil = new ExcelUtil();
			List<SalaryDTO> salaryDTOs = excelUtil.readFileSalary(file);
			for (SalaryDTO salaryDTO : salaryDTOs) {
				StaffEntity staffEntity = staffRepository.findById(salaryDTO.getIdStaff()).get();
				SalaryEntity salaryEntity = new SalaryEntity();
				salaryEntity.setStaffEntity(staffEntity);
				salaryEntity.setReward(0);
				salaryEntity.setPunish(0);
				salaryEntity.setStatus("Chưa thanh toán");
				salaryEntity = salaryRepository.save(salaryEntity);
				List<WorkEntity> workEntities = new ArrayList<>();
				for (WorkDTO workDTO : salaryDTO.getWorkDTOs()) {
					java.util.Date date = new GregorianCalendar(year, month - 1, workDTO.getDay()).getTime();
					WorkEntity workEntity = new WorkEntity();
					workEntity.setSalaryEntity(salaryEntity);
					workEntity.setDay(new Date(date.getTime()));
					workEntity.setNumber(workDTO.getNumberWork());
					workEntities.add(workEntity);
				}
				workRepository.saveAll(workEntities);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SalaryDTO> findAllByType(String type) {
		String sql = "SELECT NV.maNhanVien, NV.hoTen, NV.role, BL.maBangLuong, X.tongCong, X.type, BL.thuong , BL.phat\r\n"
				+ "FROM tbl_bangluong AS BL, tbl_nhanvien AS NV, (SELECT DATEPART(" + type
				+ ", BC.day) AS type, SUM(BC.soCong) AS tongCong, BC.maBangLuong\r\n"
				+ "																							FROM tbl_bangcong AS BC\r\n"
				+ "																							GROUP BY DATEPART("
				+ type + ", BC.day),BC.maBangLuong ) AS X\r\n" + "WHERE X.maBangLuong = BL.maBangLuong\r\n"
				+ "AND BL.maNhanVien = NV.maNhanVien\r\n" + "AND BL.status = 'Đã thanh toán'\r\n" + "ORDER BY X.type";
		Query query = entityManager.createNativeQuery(sql);
		List<SalaryDTO> result = new ArrayList<>();
		List<Object[]> list = query.getResultList();
		for (Object[] obj : list) {
			SalaryDTO salaryDTO = new SalaryDTO();
			salaryDTO.setIdStaff(Integer.parseInt(obj[0].toString()));
			salaryDTO.setNameStaff(obj[1].toString());
			salaryDTO.setRoleStaff(obj[2].toString());
			salaryDTO.setIdSalary(Integer.parseInt(obj[3].toString()));
			salaryDTO.setNumberWork(Double.parseDouble(obj[4].toString()));
			if (type.equals("month"))
				salaryDTO.setType("Tháng " + obj[5].toString());
			else
				salaryDTO.setType("Quý " + obj[5].toString());
			salaryDTO.setReward(Integer.parseInt(obj[6].toString()));
			salaryDTO.setPunish(Integer.parseInt(obj[7].toString()));
			result.add(salaryDTO);
		}
		return result;
	}

	@Override
	public List<SalaryDTO> findAllDetails(Integer number, String type) {
		String sql = "SELECT NV.maNhanVien, NV.hoTen, NV.role, BL.maBangLuong, X.tongCong, X.type, BL.thuong , BL.phat\r\n"
				+ "FROM tbl_bangluong AS BL, tbl_nhanvien AS NV, (SELECT DATEPART(" + type
				+ ", BC.day) AS type, SUM(BC.soCong) AS tongCong, BC.maBangLuong\r\n"
				+ "																							FROM tbl_bangcong AS BC\r\n"
				+ "																							GROUP BY DATEPART("
				+ type + ", BC.day),BC.maBangLuong ) AS X\r\n" + "WHERE X.maBangLuong = BL.maBangLuong\r\n"
				+ "AND BL.maNhanVien = NV.maNhanVien\r\n" + "AND BL.status = 'Đã thanh toán'\r\n"
				+ "AND X.type = :number\r\n" + "ORDER BY X.type";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("number", number);
		List<Object[]> list = query.getResultList();
		List<SalaryDTO> temp = new ArrayList<>();
		for (Object[] obj : list) {
			SalaryDTO salaryDTO = new SalaryDTO();
			salaryDTO.setIdStaff(Integer.parseInt(obj[0].toString()));
			salaryDTO.setNameStaff(obj[1].toString());
			salaryDTO.setRoleStaff(obj[2].toString());
			salaryDTO.setIdSalary(Integer.parseInt(obj[3].toString()));
			salaryDTO.setNumberWork(Double.parseDouble(obj[4].toString()));
			if (type.equals("month"))
				salaryDTO.setType("Tháng " + obj[5].toString());
			else
				salaryDTO.setType("Quý " + obj[5].toString());
			salaryDTO.setReward(Integer.parseInt(obj[6].toString()));
			salaryDTO.setPunish(Integer.parseInt(obj[7].toString()));
			temp.add(salaryDTO);
		}
		

		List<SalaryDTO> salaries = new ArrayList<>();

		for (SalaryDTO e : temp) {
			SalaryDTO salaryTemp = isExitsStaff(e.getIdStaff(), salaries);
			if (salaryTemp != null) {
				salaryTemp.setNumberWork(e.getNumberWork() + salaryTemp.getNumberWork());
				salaryTemp.setSum(salaryTemp.getSum() + e.getSum());
				salaryTemp.setPunish(salaryTemp.getPunish() + e.getPunish());
				salaryTemp.setReward(salaryTemp.getReward() + e.getReward());
			} else {
				salaries.add(e);
				e.setSum(e.getSum());
			}
		}
		return salaries;
	}

	private SalaryDTO isExitsStaff(Integer idStaff, List<SalaryDTO> salaries) {
		SalaryDTO salaryDTO = salaries.stream().filter(e -> e.getIdStaff().equals(idStaff)).findAny().orElse(null);
		return salaryDTO;
	}

}
