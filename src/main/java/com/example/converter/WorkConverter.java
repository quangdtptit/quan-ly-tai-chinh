package com.example.converter;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.example.model.WorkEntity;
import com.example.model.dto.WorkDTO;

@Component
public class WorkConverter {

	public WorkDTO toDTO(WorkEntity entity) {
		WorkDTO dto = new WorkDTO();
		Calendar cal = Calendar.getInstance();
		cal.setTime(entity.getDay());
		dto.setDay(cal.get(Calendar.DAY_OF_MONTH));
		dto.setNumberWork(entity.getNumber());
		return dto;
	}

}
