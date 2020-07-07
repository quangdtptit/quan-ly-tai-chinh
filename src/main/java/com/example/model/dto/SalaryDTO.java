package com.example.model.dto;

import java.util.List;

import com.example.model.annotation.ColumnExcel;
import com.example.model.enumerate.CustomCellType;

public class SalaryDTO {

	private Integer idSalary;

	@ColumnExcel(col = 0, title = "Mã nhân viên", type = CustomCellType._INTEGER)
	private Integer idStaff;

	@ColumnExcel(col = 1, title = "Tên nhân viên", type = CustomCellType._STRING)
	private String nameStaff;

	@ColumnExcel(col = 2, title = "Chức vụ", type = CustomCellType._STRING)
	private String roleStaff;

	@ColumnExcel(col = 3, title = "Thưởng", type = CustomCellType._INTEGER)
	private int reward;

	@ColumnExcel(col = 4, title = "Phạt", type = CustomCellType._INTEGER)
	private int punish;

	@ColumnExcel(col = 5, title = "Tổng số công", type = CustomCellType._DOUBLE)
	private double numberWork;

	@ColumnExcel(col = 6, title = "Tạm tính", type = CustomCellType._DOLLARS)
	private int sum;

	@ColumnExcel(col = 7, title = "Trạng thái", type = CustomCellType._STRING)
	private String status;

	private List<WorkDTO> workDTOs;
	
	private int moneyOnWork;
	
	private String type;

	public Integer getIdSalary() {
		return idSalary;
	}

	public void setIdSalary(Integer idSalary) {
		this.idSalary = idSalary;
	}

	public Integer getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(Integer idStaff) {
		this.idStaff = idStaff;
	}

	public String getNameStaff() {
		return nameStaff;
	}

	public void setNameStaff(String nameStaff) {
		this.nameStaff = nameStaff;
	}

	public int getPunish() {
		return punish;
	}

	public double getNumberWork() {
		return numberWork;
	}

	public void setNumberWork(double numberWork) {
		this.numberWork = numberWork;
	}

	public int getSum() {
		if(this.sum > 0)
			return sum;
		int moneyOnWork = 0;
		if ("ADMIN".equals(roleStaff)) {
			moneyOnWork = 500000;
		}
		if ("WAREHOUSE".equals(roleStaff)) {
			moneyOnWork = 400000;
		}
		if ("USER".equals(roleStaff)) {
			moneyOnWork = 300000;
		}
		if("ACCOUNT".equals(roleStaff)) {
			moneyOnWork = 300000;
		}
		int temp = (int) (moneyOnWork * this.numberWork + reward - punish);
		return temp;
	}

	public String getRoleStaff() {
		return roleStaff;
	}

	public void setRoleStaff(String roleStaff) {
		this.roleStaff = roleStaff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<WorkDTO> getWorkDTOs() {
		return workDTOs;
	}

	public void setWorkDTOs(List<WorkDTO> workDTOs) {
		this.workDTOs = workDTOs;
	}

	public int getMoneyOnWork() {
		if ("ADMIN".equals(roleStaff)) {
			return 500000;
		}
		if ("WAREHOUSE".equals(roleStaff)) {
			return 400000;
		}
		if ("USER".equals(roleStaff)) {
			return 300000;
		}
		if ("ACCOUNT".equals(roleStaff)) {
			return 300000;
		}
		return 0;
	}

	public void setMoneyOnWork(int moneyOnWork) {
		this.moneyOnWork = moneyOnWork;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public void setPunish(int punish) {
		this.punish = punish;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SalaryDTO [idSalary=" + idSalary + ", idStaff=" + idStaff + ", nameStaff=" + nameStaff + ", roleStaff="
				+ roleStaff + ", reward=" + reward + ", punish=" + punish + ", numberWork=" + numberWork + ", sum="
				+ sum + ", status=" + status + ", workDTOs=" + workDTOs + ", moneyOnWork=" + moneyOnWork + ", type="
				+ type + "]";
	}

}
