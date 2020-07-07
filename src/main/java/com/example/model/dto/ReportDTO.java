package com.example.model.dto;

public class ReportDTO {

	private String nameDay;
	private Integer idBillWareHouse;
	private String nameWareHouse;
	private String nameSupplier;

	public ReportDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReportDTO(String nameDay, Integer idBillWareHouse, String nameWareHouse, String nameSupplier) {
		this.nameDay = nameDay;
		this.idBillWareHouse = idBillWareHouse;
		this.nameWareHouse = nameWareHouse;
		this.nameSupplier = nameSupplier;
	}

	public String getNameDay() {
		return nameDay;
	}

	public void setNameDay(String nameDay) {
		this.nameDay = nameDay;
	}

	public Integer getIdBillWareHouse() {
		return idBillWareHouse;
	}

	public void setIdBillWareHouse(Integer idBillWareHouse) {
		this.idBillWareHouse = idBillWareHouse;
	}

	public String getNameWareHouse() {
		return nameWareHouse;
	}

	public void setNameWareHouse(String nameWareHouse) {
		this.nameWareHouse = nameWareHouse;
	}

	public String getNameSupplier() {
		return nameSupplier;
	}

	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBillWareHouse == null) ? 0 : idBillWareHouse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportDTO other = (ReportDTO) obj;
		if (idBillWareHouse == null) {
			if (other.idBillWareHouse != null)
				return false;
		} else if (!idBillWareHouse.equals(other.idBillWareHouse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportDTO [nameDay=" + nameDay + ", idBillWareHouse=" + idBillWareHouse + ", nameWareHouse="
				+ nameWareHouse + ", nameSupplier=" + nameSupplier + "]";
	}

}
