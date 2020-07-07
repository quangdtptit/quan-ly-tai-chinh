package com.example.model.dto;

public class BillWareHouseDTO {

	private int type;

	private String nameDay;

	private long sumTotal;

	private long sumAmount;

	public String getNameDay() {
		return nameDay;
	}

	public void setNameDay(String nameDay) {
		this.nameDay = nameDay;
	}

	public long getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(long sumTotal) {
		this.sumTotal = sumTotal;
	}

	public long getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(long sumAmount) {
		this.sumAmount = sumAmount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BillWareHouseDTO [nameDay=" + nameDay + ", sumTotal=" + sumTotal + ", sumAmount=" + sumAmount + "]";
	}

}
