package com.cybage.sonar.report.pdf.util;

public class SonarUtil {

	public static String getWorkDurConversion(Integer minutes) {
		Integer hours = null;
		Integer days = null;

		// 1140
		if (minutes >= 60 && minutes < 480) {
			hours = minutes / 60;
			minutes = minutes % 60;
			return hours + "h " + minutes + "min";
		} else if (minutes >= 480) {
			days = (minutes / 60) / 8;
			// minutes = minutes - (minutes * days);
			minutes = minutes % 480;
			hours = minutes / 60;
			minutes = minutes % 60;
			return days + "d " + hours + "h " + minutes + "min";
		} else {
			return minutes + "min";
		}
	}

	public static String getFormattedValue(Object value, String dataType) {
		switch (dataType) {
		case MetricDataTypes.WORKDUR:
			return getWorkDurConversion(Integer.parseInt(String.valueOf(value)));
		case MetricDataTypes.PERCENT:
			return String.valueOf(value) + "%";
		case MetricDataTypes.RATING:
			return Rating.getRating(String.valueOf(value));
		case MetricDataTypes.MILLISEC:
			return getWorkDurConversion((Integer.parseInt(String.valueOf(value)) / 1000) / 60);
		case MetricDataTypes.BOOL:
			return String.valueOf(value).equals("TRUE") ? "TRUE" : "FALSE";
		default:
			return String.valueOf(value);
		}
	}

}
