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
@Table(name = "tbl_kho")
public class WareHouseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maKho")
	private Integer id;

	@Column(name = "tenKho")
	private String name;

	@Column(name = "moTa")
	private String des;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maChiNhanh", nullable = false)
	private BranchEntity branchEntity;

	@OneToMany(mappedBy = "wareHouseEntity")
	private Set<ItemEntity> itemEntities;

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

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public Set<ItemEntity> getItemEntities() {
		return itemEntities;
	}

	public void setItemEntities(Set<ItemEntity> itemEntities) {
		this.itemEntities = itemEntities;
	}

}
