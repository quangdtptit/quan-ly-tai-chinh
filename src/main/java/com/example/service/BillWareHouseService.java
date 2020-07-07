package com.example.service;

import java.util.List;

import com.example.model.dto.BillWareHouseDTO;
import com.example.model.dto.ReportDetailsStocker;

public interface BillWareHouseService {
	List<BillWareHouseDTO> reportByDate(int type);
	
	List<ReportDetailsStocker> reportByDateDetails(int type, String valueDate);

}
