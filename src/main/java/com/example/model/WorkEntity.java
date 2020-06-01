package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_bangcong")
public class WorkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maBangCong")
	private Integer id;

	@Column(name = "day")
	private Date day;

	@Column(name = "soCong")
	private int number;

	@Column(name = "moTa")
	private String des;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maBangLuong", nullable = false)
	private SalaryEntity salaryEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public SalaryEntity getSalaryEntity() {
		return salaryEntity;
	}

	public void setSalaryEntity(SalaryEntity salaryEntity) {
		this.salaryEntity = salaryEntity;
	}

}
