package com.example.model.dto;

public class WorkDTO {

	private int day;
	private float numberWork;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public float getNumberWork() {
		return numberWork;
	}

	public void setNumberWork(float numberWork) {
		this.numberWork = numberWork;
	}

	@Override
	public String toString() {
		return "WorkDTO [day=" + day + ", numberWork=" + numberWork + "]";
	}

}
