package com.example.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadExcelItemDTO {

	private MultipartFile file;
	private String nameSupplier;
	private Integer idWareHouse;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNameSupplier() {
		return nameSupplier;
	}

	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	public Integer getIdWareHouse() {
		return idWareHouse;
	}

	public void setIdWareHouse(Integer idWareHouse) {
		this.idWareHouse = idWareHouse;
	}

	@Override
	public String toString() {
		return "UploadExcelItemDTO [file=" + file.getOriginalFilename() + ", nameSupplier=" + nameSupplier + ", idWareHouse=" + idWareHouse
				+ "]";
	}
	
	

}
