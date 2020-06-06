package com.example.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_hanghoa")
@EntityListeners(AuditingEntityListener.class)
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maHangHoa")
	private Integer id;

	@Column(name = "tenHangHoa")
	private String name;

	@Column(name = "price")
	private long price;

	@Column(name = "moTa")
	private String des;

	@Column(name = "soLuong")
	private int total;

	@CreatedDate
	@Column(name = "createDate")
	private Date createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKho", nullable = false)
	private WareHouseEntity wareHouseEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDonNhapKho", nullable = false)
	private BillWareHouseEntity billWareHouseEntity;

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

	public WareHouseEntity getWareHouseEntity() {
		return wareHouseEntity;
	}

	public void setWareHouseEntity(WareHouseEntity wareHouseEntity) {
		this.wareHouseEntity = wareHouseEntity;
	}

	public BillWareHouseEntity getBillWareHouseEntity() {
		return billWareHouseEntity;
	}

	public void setBillWareHouseEntity(BillWareHouseEntity billWareHouseEntity) {
		this.billWareHouseEntity = billWareHouseEntity;
	}

	public Set<ItemBoughtEntity> getItemBoughtEntities() {
		return itemBoughtEntities;
	}

	public void setItemBoughtEntities(Set<ItemBoughtEntity> itemBoughtEntities) {
		this.itemBoughtEntities = itemBoughtEntities;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
