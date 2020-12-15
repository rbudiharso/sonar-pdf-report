package com.cybage.sonar.report.pdf.entity;

public class FileInfo {

	/**
	 * Sonar resource key.
	 */
	private String key;

	/**
	 * Resource name (filename).
	 */
	private String name;

	/**
	 * Resource name (filename).
	 */
	private String path;

	/**
	 * Number of violations ins this resource (file).
	 */
	private String violations;

	/**
	 * Class complexity.
	 */
	private String complexity;

	/**
	 * Duplicated lines in this resource (file)
	 */
	private String duplicatedLines;

	/**
	 * It defines the content of this object: used for violations info,
	 * complexity info or duplications info.
	 */
	public static final int VIOLATIONS_CONTENT = 1;
	public static final int CCN_CONTENT = 2;
	public static final int DUPLICATIONS_CONTENT = 3;

	public boolean isContentSet(final int content) {
		boolean result = false;
		if (content == VIOLATIONS_CONTENT) {
			result = !this.getViolations().equals("0");
		} else if (content == CCN_CONTENT) {
			result = !this.getComplexity().equals("0");
		} else if (content == DUPLICATIONS_CONTENT) {
			result = !this.getDuplicatedLines().equals("0");
		}
		return result;
	}

	public String getKey() {
		return key;
	}

	public String getViolations() {
		return violations;
	}

	public String getComplexity() {
		return complexity;
	}

	public String getName() {
		return name;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setViolations(final String violations) {
		this.violations = violations;
	}

	public void setComplexity(final String complexity) {
		this.complexity = complexity;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDuplicatedLines() {
		return duplicatedLines;
	}

	public void setDuplicatedLines(final String duplicatedLines) {
		this.duplicatedLines = duplicatedLines;
	}

	@Override
	public String toString() {
		return "FileInfo [key=" + key + ", name=" + name + ", path=" + path + ", violations=" + violations
				+ ", complexity=" + complexity + ", duplicatedLines=" + duplicatedLines + "]";
	}

}
