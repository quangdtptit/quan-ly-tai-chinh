package com.example.model.dto;

import java.sql.Timestamp;

import com.example.model.annotation.ColumnExcel;
import com.example.model.enumerate.CustomCellType;

public class ItemDTO {

	private Integer id;

	@ColumnExcel(col = 0, title = "Tên hàng", type = CustomCellType._STRING)
	private String name;

	@ColumnExcel(col = 1, title = "Giá", type = CustomCellType._DOLLARS)
	private long price;

	@ColumnExcel(col = 2, title = "Số lượng", type = CustomCellType._INTEGER)
	private int total;

	@ColumnExcel(col = 3, title = "Mô tả", type = CustomCellType._STRING)
	private String des;

	private Timestamp createDate;

	public ItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemDTO(String name, long price, int total) {
		super();
		this.name = name;
		this.price = price;
		this.total = total;
	}

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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", name=" + name + ", price=" + price + ", total=" + total + ", des=" + des
				+ ", createDate=" + createDate + "]";
	}

}
