package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.converter.StaffConverter;
import com.example.model.BranchEntity;
import com.example.model.StaffEntity;
import com.example.model.dto.StaffDTO;
import com.example.repository.BranchRepository;
import com.example.repository.StaffRepository;
import com.example.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private StaffConverter staffConverter;

	@Override
	public StaffDTO getById(Integer id) {
		StaffEntity staffEntity = staffRepository.findById(id).get();
		if (staffEntity == null)
			return null;
		return staffConverter.toDTO(staffEntity);
	}

	@Override
	public List<StaffDTO> getAll() {
		List<StaffDTO> result = new ArrayList<>();
		List<StaffEntity> staffEntities = staffRepository.findAll();
		staffEntities.forEach(e -> {
			result.add(staffConverter.toDTO(e));
		});
		return result;
	}

	@Override
	public StaffDTO save(StaffDTO staffDTO) {
		BranchEntity branchEntity = branchRepository.findById(staffDTO.getIdBranch()).get();
		StaffEntity staffEntity = staffConverter.toEntity(staffDTO);
		try {
			if (staffEntity.getPassword().trim().equals("")) {
				staffEntity.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));
			}
		} catch (NullPointerException e) {
		}
		staffEntity.setBranchEntity(branchEntity);
		staffEntity = staffRepository.save(staffEntity);
		StaffDTO result = staffConverter.toDTO(staffEntity);
		return result;
	}
}
