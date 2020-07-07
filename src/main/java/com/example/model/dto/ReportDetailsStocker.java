package com.example.model.dto;

import java.util.List;

public class ReportDetailsStocker {
	private ReportDTO baseReport;
	private List<ItemDTO> itemReports;

	public ReportDetailsStocker() {
		super();
	}

	public ReportDetailsStocker(ReportDTO baseReport, List<ItemDTO> itemReports) {
		super();
		this.baseReport = baseReport;
		this.itemReports = itemReports;
	}

	public ReportDTO getBaseReport() {
		return baseReport;
	}

	public void setBaseReport(ReportDTO baseReport) {
		this.baseReport = baseReport;
	}

	public List<ItemDTO> getItemReports() {
		return itemReports;
	}

	public void setItemReports(List<ItemDTO> itemReports) {
		this.itemReports = itemReports;
	}

}
