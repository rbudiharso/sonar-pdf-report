package com.cybage.sonar.report.pdf.entity;

public class Condition {

	private String status;
	private String metricKey;
	private String comparator;
	private Integer periodIndex;
	private String errorThreshold;
	private String actualValue;
	private String warningThreshold;

	public Condition(String status, String metricKey, String comparator, Integer periodIndex, String errorThreshold,
			String actualValue, String warningThreshold) {
		super();
		this.status = status;
		this.metricKey = metricKey;
		this.comparator = comparator;
		this.periodIndex = periodIndex;
		this.errorThreshold = errorThreshold;
		this.actualValue = actualValue;
		this.warningThreshold = warningThreshold;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMetricKey() {
		return metricKey;
	}

	public void setMetricKey(String metricKey) {
		this.metricKey = metricKey;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	public Integer getPeriodIndex() {
		return periodIndex;
	}

	public void setPeriodIndex(Integer periodIndex) {
		this.periodIndex = periodIndex;
	}

	public String getErrorThreshold() {
		return errorThreshold;
	}

	public void setErrorThreshold(String errorThreshold) {
		this.errorThreshold = errorThreshold;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public String getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(String warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

	@Override
	public String toString() {
		return "Condition [status=" + status + ", metricKey=" + metricKey + ", comparator=" + comparator
				+ ", periodIndex=" + periodIndex + ", errorThreshold=" + errorThreshold + ", actualValue=" + actualValue
				+ ", warningThreshold=" + warningThreshold + "]";
	}
}
