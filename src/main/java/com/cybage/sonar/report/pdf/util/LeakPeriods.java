package com.cybage.sonar.report.pdf.util;

import java.util.Arrays;
import java.util.List;

public class LeakPeriods {

	// Leak Period Constants
	public static final String PREVIOUS_VERSION = "previous_version";
	public static final String PREVIOUS_ANALYSIS = "previous_analysis";
	public static final String DAYS = "days";

	public static final List<String> getAllLeakPeriods() {
		return Arrays.asList(PREVIOUS_VERSION, PREVIOUS_ANALYSIS, DAYS);
	}

}
