package com.cybage.sonar.report.pdf.util;

import com.cybage.sonar.report.pdf.Style;
import com.itextpdf.text.BaseColor;

public class ProjectStatusKeys {
	public static final String STATUS_OK = "OK";
	public static final String STATUS_WARN = "WARN";
	public static final String STATUS_ERROR = "ERROR";
	public static final String STATUS_NONE = "None";

	public static final String EQ = "=";
	public static final String NE = "!=";
	public static final String LT = "<";
	public static final String GT = ">";

	public static final String getComparatorAsString(String comparator) {
		if (comparator.equals("EQ")) {
			return EQ;
		} else if (comparator.equals("NE")) {
			return NE;
		} else if (comparator.equals("LT")) {
			return LT;
		} else if (comparator.equals("GT")) {
			return GT;
		}
		return null;
	}

	public static final String getStatusAsString(String status) {
		if (status.equals(STATUS_OK)) {
			return "Passed";
		} else if (status.equals(STATUS_ERROR)) {
			return "Failed";
		}
		return null;
	}

	public static final BaseColor getStatusBaseColor(String status) {
		if (status.equals(STATUS_OK)) {
			return Style.QUALITY_GATE_PASSED_COLOR;
		} else if (status.equals(STATUS_ERROR)) {
			return Style.QUALITY_GATE_FAILED_COLOR;
		}
		return null;
	}

}
