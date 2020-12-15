package com.cybage.sonar.report.pdf.entity;

import java.util.List;

public class Measure {

	private String metric;
	private String value;
	private String metricTitle;
	private String dataType;
	private String domain;
	private Boolean higherValuesAreBetter;
	private List<Period> periods = null;

	public Measure(String metric, String value, String metricTitle, String dataType, String domain,
			Boolean higherValuesAreBetter, List<Period> periods) {
		super();
		this.metric = metric;
		this.value = value;
		this.metricTitle = metricTitle;
		this.dataType = dataType;
		this.domain = domain;
		this.higherValuesAreBetter = higherValuesAreBetter;
		this.periods = periods;
	}

	public Measure() {
		this.metric = null;
		this.value = null;
		this.metricTitle = null;
		this.dataType = null;
		this.domain = null;
		this.higherValuesAreBetter = null;
		this.periods = null;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMetricTitle() {
		return metricTitle;
	}

	public void setMetricTitle(String metricTitle) {
		this.metricTitle = metricTitle;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Boolean getHigherValuesAreBetter() {
		return higherValuesAreBetter;
	}

	public void setHigherValuesAreBetter(Boolean higherValuesAreBetter) {
		this.higherValuesAreBetter = higherValuesAreBetter;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	@Override
	public String toString() {
		return "Measure [metric=" + metric + ", value=" + value + ", metricTitle=" + metricTitle + ", dataType="
				+ dataType + ", domain=" + domain + ", higherValuesAreBetter=" + higherValuesAreBetter + ", periods="
				+ periods + "]";
	}

}