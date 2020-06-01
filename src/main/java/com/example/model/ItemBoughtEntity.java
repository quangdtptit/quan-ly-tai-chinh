package com.example.model;

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
@Table(name = "tbl_hanghoadaban")
public class ItemBoughtEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maHangHoaDaBan")
	private Integer id;

	@Column(name = "tenHangHoa")
	private String name;

	@Column(name = "price")
	private long price;

	@Column(name = "moTa")
	private String des;

	@Column(name = "soLuong")
	private int number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHangHoa", nullable = false)
	private ItemEntity itemEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDonBanHang", nullable = false)
	private BillItemEntity billItemEntity;

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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public BillItemEntity getBillItemEntity() {
		return billItemEntity;
	}

	public void setBillItemEntity(BillItemEntity billItemEntity) {
		this.billItemEntity = billItemEntity;
	}

}
