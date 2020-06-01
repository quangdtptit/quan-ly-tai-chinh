package com.example.model;

import java.security.Timestamp;
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
@Table(name = "tbl_hoadonbanhang")
public class BillItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maHoaDonBanHang")
	private Integer id;

	@Column(name = "soLuong")
	private int total;

	@Column(name = "amount")
	private long amount;

	@Column(name = "moTa")
	private String des;

	@Column(name = "time")
	private Timestamp createTime;

	@Column(name = "tenNhanVien")
	private String createBy;

	@OneToMany(mappedBy = "billItemEntity")
	private Set<ItemBoughtEntity> itemBoughtEntities;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang", nullable = false)
	private CustomerEntity customerEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Set<ItemBoughtEntity> getItemBoughtEntities() {
		return itemBoughtEntities;
	}

	public void setItemBoughtEntities(Set<ItemBoughtEntity> itemBoughtEntities) {
		this.itemBoughtEntities = itemBoughtEntities;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

}
