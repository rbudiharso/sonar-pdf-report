package com.cybage.sonar.report.pdf.entity;

public class Rule {

	// Rule key
	private String key;

	// Rule name
	private String name;

	// Rule count
	private Long count;

	// Language Name
	private String languageName;

	// Severity
	private String severity;

	public Rule() {
		super();
		this.key = "";
		this.name = "";
		this.count = 0L;
		this.languageName = "";
		this.severity = "";
	}

	public Rule(String key, String name, Long count, String languageName, String severity) {
		super();
		this.key = key;
		this.name = name;
		this.count = count;
		this.languageName = languageName;
		this.severity = severity;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Override
	public String toString() {
		return "Rule [key=" + key + ", name=" + name + ", count=" + count + ", languageName=" + languageName
				+ ", severity=" + severity + "]";
	}

}
