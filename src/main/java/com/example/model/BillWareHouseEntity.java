package com.example.model;

import java.sql.Timestamp;
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
@Table(name = "tbl_hoadonnhapkho")
public class BillWareHouseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maHoaDonNhapKho")
	private Integer id;

	@Column(name = "tenNguoiNhap")
	private String createBy;

	@Column(name = "total")
	private int total;

	@Column(name = "amount")
	private long amount;

	@Column(name = "time")
	private Timestamp createTime;

	@Column(name = "moTa")
	private String des;

	@OneToMany(mappedBy = "billWareHouseEntity")
	private Set<ItemEntity> itemEntities;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhaCungCap", nullable = false)
	private SupplierEntity supplierEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Set<ItemEntity> getItemEntities() {
		return itemEntities;
	}

	public void setItemEntities(Set<ItemEntity> itemEntities) {
		this.itemEntities = itemEntities;
	}

	public SupplierEntity getSupplierEntity() {
		return supplierEntity;
	}

	public void setSupplierEntity(SupplierEntity supplierEntity) {
		this.supplierEntity = supplierEntity;
	}

}
