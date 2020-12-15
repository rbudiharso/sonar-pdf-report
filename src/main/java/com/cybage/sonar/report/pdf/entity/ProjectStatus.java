package com.cybage.sonar.report.pdf.entity;

import java.util.List;

public class ProjectStatus {

	private String status;
	private List<Condition> conditions = null;
	private List<StatusPeriod> statusPeriods = null;

	public ProjectStatus(String status, List<Condition> conditions, List<StatusPeriod> statusPeriods) {
		super();
		this.status = status;
		this.conditions = conditions;
		this.statusPeriods = statusPeriods;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<StatusPeriod> getStatusPeriods() {
		return statusPeriods;
	}

	public void setStatusPeriods(List<StatusPeriod> statusPeriods) {
		this.statusPeriods = statusPeriods;
	}

	@Override
	public String toString() {
		return "ProjectStatus [status=" + status + ", conditions=" + conditions + ", statusPeriods=" + statusPeriods
				+ "]";
	}
}
