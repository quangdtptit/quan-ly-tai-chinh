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
@Table(name = "tbl_bangluong")
public class SalaryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maBangLuong")
	private Integer id;

	@Column(name = "thuong")
	private long reward;

	@Column(name = "phat")
	private long punish;

	@Column(name = "moTa")
	private String des;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien", nullable = false)
	private StaffEntity staffEntity;
	
	@OneToMany(mappedBy = "salaryEntity")
	private Set<WorkEntity> workEntities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getReward() {
		return reward;
	}

	public void setReward(long reward) {
		this.reward = reward;
	}

	public long getPunish() {
		return punish;
	}

	public void setPunish(long punish) {
		this.punish = punish;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public StaffEntity getStaffEntity() {
		return staffEntity;
	}

	public void setStaffEntity(StaffEntity staffEntity) {
		this.staffEntity = staffEntity;
	}

	public Set<WorkEntity> getWorkEntities() {
		return workEntities;
	}

	public void setWorkEntities(Set<WorkEntity> workEntities) {
		this.workEntities = workEntities;
	}
	
	

}
