package com.example.model.dto;

import java.sql.Date;

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

	private Date createDate;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
