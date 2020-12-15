package com.cybage.sonar.report.pdf.util;

import java.util.HashMap;
import java.util.Map;

import com.cybage.sonar.report.pdf.Style;
import com.itextpdf.text.Font;

public class Rating {

	private static Map<String, String> ratings;

	static {
		ratings = new HashMap<>();
		ratings.put("1.0", "A");
		ratings.put("2.0", "B");
		ratings.put("3.0", "C");
		ratings.put("4.0", "D");
		ratings.put("5.0", "E");
	}

	public static String getRating(String rating) {
		return ratings.get(rating);
	}

	public static Font getRatingStyle(String rating) {
		if (rating.equals("1.0")) {
			return Style.DASHBOARD_RATING_FONT_A;
		} else if (rating.equals("2.0")) {
			return Style.DASHBOARD_RATING_FONT_B;
		} else if (rating.equals("3.0")) {
			return Style.DASHBOARD_RATING_FONT_C;
		} else if (rating.equals("4.0")) {
			return Style.DASHBOARD_RATING_FONT_D;
		} else if (rating.equals("5.0")) {
			return Style.DASHBOARD_RATING_FONT_E;
		}
		return null;
	}
}
