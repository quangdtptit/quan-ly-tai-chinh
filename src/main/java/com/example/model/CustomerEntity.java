package com.example.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_khachhang")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maKhachHang")
	private Integer id;

	@Column(name = "tenKhachHang")
	private String name;

	@Column(name = "diaChi")
	private String address;

	@Column(name = "moTa")
	private String des;

	@OneToMany(mappedBy = "customerEntity")
	private Set<BillItemEntity> billItemEntities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Set<BillItemEntity> getBillItemEntities() {
		return billItemEntities;
	}

	public void setBillItemEntities(Set<BillItemEntity> billItemEntities) {
		this.billItemEntities = billItemEntities;
	}

}
