package com.cybage.sonar.report.pdf.entity;

public class Period {

	private Integer index;
	private String value;

	public Period(Integer index, String value) {
		super();
		this.index = index;
		this.value = value;
	}

	public Period() {
		this.index = -1;
		this.value = null;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Period [index=" + index + ", value=" + value + "]";
	}

}