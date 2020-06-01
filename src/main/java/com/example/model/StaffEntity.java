package com.example.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_nhanvien")
public class StaffEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maNhanVien")
	private Integer id;

	@Column(name = "diaChi")
	private String address;

	@Column(name = "sdt")
	private String phone;

	@Column(name = "hoTen")
	private String fullName;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "moTa")
	private String des;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maChiNhanh", nullable = false)
	private BranchEntity branchEntity;

	@OneToMany(mappedBy = "staffEntity")
	private Set<SalaryEntity> salaryEntities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public Set<SalaryEntity> getSalaryEntities() {
		return salaryEntities;
	}

	public void setSalaryEntities(Set<SalaryEntity> salaryEntities) {
		this.salaryEntities = salaryEntities;
	}

}
