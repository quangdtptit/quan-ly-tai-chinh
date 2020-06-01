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
@Table(name = "tbl_chinhanh")
public class BranchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maChiNhanh")
	private Integer id;

	@Column(name = "tenChiNhanh")
	private String name;

	@Column(name = "moTa")
	private String des;

	@OneToMany(mappedBy = "branchEntity")
	private Set<WareHouseEntity> wareHouseEntities;

	@OneToMany(mappedBy = "branchEntity")
	private Set<StaffEntity> staffEntities;

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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Set<WareHouseEntity> getWareHouseEntities() {
		return wareHouseEntities;
	}

	public void setWareHouseEntities(Set<WareHouseEntity> wareHouseEntities) {
		this.wareHouseEntities = wareHouseEntities;
	}

	public Set<StaffEntity> getStaffEntities() {
		return staffEntities;
	}

	public void setStaffEntities(Set<StaffEntity> staffEntities) {
		this.staffEntities = staffEntities;
	}

}
