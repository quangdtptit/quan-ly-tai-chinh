package com.example.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_hanghoaxuatkho")
public class ItemOutputEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hang_hoa_xuat_kho")
	private Integer id;

	@Column(name = "ten_hang_hoa")
	private String name;

	@Column(name = "price")
	private long price;

	@Column(name = "moTa")
	private String des;

	@Column(name = "soLuong")
	private int total;

	@OneToOne
	@JoinColumn(name = "id_hang_hoa", unique = true)
	private ItemEntity itemEntity;

	@OneToMany(mappedBy = "itemEntity")
	private Set<ItemBoughtEntity> itemBoughtEntities;

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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public Set<ItemBoughtEntity> getItemBoughtEntities() {
		return itemBoughtEntities;
	}

	public void setItemBoughtEntities(Set<ItemBoughtEntity> itemBoughtEntities) {
		this.itemBoughtEntities = itemBoughtEntities;
	}

}
