package com.example.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dto.BillWareHouseDTO;
import com.example.model.dto.ItemDTO;
import com.example.model.dto.ReportDTO;
import com.example.model.dto.ReportDetailsStocker;
import com.example.service.BillWareHouseService;

@Service
public class BillWareHouseServiceImpl implements BillWareHouseService {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<BillWareHouseDTO> reportByDate(int type) {
		String sql = this.getQueryReport(type);
		List<BillWareHouseDTO> result = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class)
				.addScalar("nameDay", new StringType()).addScalar("sumTotal", new LongType())
				.addScalar("sumAmount", new LongType())
				.setResultTransformer(Transformers.aliasToBean(BillWareHouseDTO.class)).list();
		return result;
	}

	@Override
	public List<ReportDetailsStocker> reportByDateDetails(int type, String valueDate) {
		List<ReportDetailsStocker> result = new ArrayList<>();
		String sql = this.getQueryReportDetails(type);
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("valueDate", valueDate);
		List<Object[]> objList = query.getResultList();
		ReportDTO temp = null;
		ReportDTO reportDTO = new ReportDTO();
		List<ItemDTO> itemDTOs = new ArrayList<>();
		for (Object[] e : objList) {
			reportDTO = new ReportDTO(e[0].toString(), (Integer) e[1], e[2].toString(), e[3].toString());
			if (!reportDTO.equals(temp) && temp != null) {
				ReportDetailsStocker element = new ReportDetailsStocker(temp, itemDTOs);
				result.add(element);
				itemDTOs = new ArrayList<>();
			}
			ItemDTO itemDTO = new ItemDTO(e[4].toString(), ((BigInteger) e[5]).longValue(), ((Integer) e[6]));
			itemDTOs.add(itemDTO);
			temp = reportDTO;
		}
		// ADD LAST ELEMENT
		ReportDetailsStocker element = new ReportDetailsStocker(reportDTO, itemDTOs);
		result.add(element);

		return result;
	}

	private String getQueryReport(int type) {
		if (type == 1) {
			return "SELECT CAST(HDNK.time AS DATE) AS nameDay, SUM(HDNK.total) AS sumTotal, SUM(HDNK.amount) AS sumAmount\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n" + "GROUP BY CAST(HDNK.time AS DATE)";
		}
		if (type == 2) {
			return "SELECT DATEPART(month, HDNK.time) AS nameDay, SUM(HDNK.total) AS sumTotal, SUM(HDNK.amount) AS sumAmount\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n" + "GROUP BY DATEPART(month, HDNK.time)";
		}
		if (type == 3) {
			return "SELECT DATEPART(quarter, HDNK.time) AS nameDay, SUM(HDNK.total) AS sumTotal, SUM(HDNK.amount) AS sumAmount\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n" + "GROUP BY DATEPART(quarter, HDNK.time)";
		}
		return null;
	}

	private String getQueryReportDetails(int type) {
		if (type == 1) {
			return "SELECT CAST(HDNK.time AS DATE) as nameDay, HDNK.maHoaDonNhapKho, HDNK.tenNguoiNhap, NCC.tenNCC , HH.tenHangHoa , HH.price, HH.soLuong\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n"
					+ "INNER JOIN tbl_nhacungcap AS NCC ON NCC.maNhaCungCap = HDNK.maNhaCungCap\r\n"
					+ "INNER JOIN tbl_hanghoa AS HH ON HH.maHoaDonNhapKho = HDNK.maHoaDonNhapKho\r\n"
					+ "WHERE CAST(HDNK.time AS DATE) = :valueDate";
		}
		if (type == 2) {
			return "SELECT CAST(HDNK.time AS DATE) as nameDay, HDNK.maHoaDonNhapKho, HDNK.tenNguoiNhap, NCC.tenNCC , HH.tenHangHoa , HH.price, HH.soLuong\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n"
					+ "INNER JOIN tbl_nhacungcap AS NCC ON NCC.maNhaCungCap = HDNK.maNhaCungCap\r\n"
					+ "INNER JOIN tbl_hanghoa AS HH ON HH.maHoaDonNhapKho = HDNK.maHoaDonNhapKho\r\n"
					+ "WHERE DATEPART(month, HDNK.time) = :valueDate";
		}
		if (type == 3) {
			return "SELECT CAST(HDNK.time AS DATE) as nameDay, HDNK.maHoaDonNhapKho, HDNK.tenNguoiNhap, NCC.tenNCC , HH.tenHangHoa , HH.price, HH.soLuong\r\n"
					+ "FROM tbl_hoadonnhapkho AS HDNK\r\n"
					+ "INNER JOIN tbl_nhacungcap AS NCC ON NCC.maNhaCungCap = HDNK.maNhaCungCap\r\n"
					+ "INNER JOIN tbl_hanghoa AS HH ON HH.maHoaDonNhapKho = HDNK.maHoaDonNhapKho\r\n"
					+ "WHERE DATEPART(quarter, HDNK.time) = :valueDate";
		}
		return null;
	}

	public static void main(String[] args) {
		List<ReportDTO> list = new ArrayList<>();
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setNameDay("THU 2");
		list.add(reportDTO);
		reportDTO = new ReportDTO();
		System.out.println(list.get(0).getNameDay());
	}

}
