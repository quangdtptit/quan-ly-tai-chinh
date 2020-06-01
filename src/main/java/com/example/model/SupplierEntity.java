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
@Table(name = "tbl_nhacungcap")
public class SupplierEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maNhaCungCap")
	private Integer id;

	@Column(name = "tenNCC")
	private String name;

	@Column(name = "moTa")
	private String des;

	@OneToMany(mappedBy = "supplierEntity")
	private Set<BillWareHouseEntity> billWareHouseEntities;

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

	public Set<BillWareHouseEntity> getBillWareHouseEntities() {
		return billWareHouseEntities;
	}

	public void setBillWareHouseEntities(Set<BillWareHouseEntity> billWareHouseEntities) {
		this.billWareHouseEntities = billWareHouseEntities;
	}

}
