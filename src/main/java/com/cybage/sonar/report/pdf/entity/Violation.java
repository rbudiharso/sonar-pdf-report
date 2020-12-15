package com.cybage.sonar.report.pdf.entity;

public class Violation {

	private String resource;
	private String line;
	private String source;

	public Violation(final String line, final String resource, final String source) {
		this.line = line;
		this.resource = resource;
		this.source = source;
	}

	public String getResource() {
		return resource;
	}

	public String getLine() {
		return line;
	}

	public void setResource(final String resource) {
		this.resource = resource;
	}

	public void setLine(final String line) {
		this.line = line;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public static String getViolationLevelByKey(final String level) {
		String violationLevel = null;
		if (level.equals(Priority.INFO)) {
			violationLevel = "info_violations";
		} else if (level.equals(Priority.MINOR)) {
			violationLevel = "minor_violations";
		} else if (level.equals(Priority.MAJOR)) {
			violationLevel = "major_violations";
		} else if (level.equals(Priority.CRITICAL)) {
			violationLevel = "critical_violations";
		} else if (level.equals(Priority.BLOCKER)) {
			violationLevel = "blocker_violations";
		}
		return violationLevel;
	}

}
