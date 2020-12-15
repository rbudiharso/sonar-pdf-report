package com.cybage.sonar.report.pdf.util;

/**
 * Credentials.
 */
public class Credentials {

	private String url = null;
	private String username = null;
	private String password = null;

	public Credentials(final String url, final String username, final String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 * 
	 */
	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}
}
