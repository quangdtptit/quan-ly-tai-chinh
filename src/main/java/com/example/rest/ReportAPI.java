package com.example.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.BillWareHouseDTO;
import com.example.model.dto.ReportDetailsStocker;
import com.example.service.BillWareHouseService;

@RestController
@RequestMapping("/api/v1/report")
public class ReportAPI {

	@Autowired
	private BillWareHouseService billService;

	@PostMapping("/stocker/{name}")
	public Map<String, Object> reportStocker(@PathVariable(name = "name") String name,
			@RequestBody BillWareHouseDTO billWareHouseDTO) {
		Map<String, Object> result = new HashMap<>();
		if ("graph".equals(name)) {

		}
		if ("table".equals(name)) {
			List<BillWareHouseDTO> datas = billService.reportByDate(billWareHouseDTO.getType());
			result.put("datas", datas);
			long totalNumber = 0L;
			long totalAmount = 0L;
			for (BillWareHouseDTO dto : datas) {
				totalAmount += dto.getSumAmount();
				totalNumber += dto.getSumTotal();
			}
			result.put("totalAmount", totalAmount);
			result.put("totalNumber", totalNumber);
		}
		return result;
	}

	@PostMapping("/stocker/table/details")
	public List<ReportDetailsStocker> reportStockerDetails(
			@RequestParam(required = false, defaultValue = "1") Integer type, @RequestParam String valueDate) {
		return billService.reportByDateDetails(type, valueDate);
	}

}
