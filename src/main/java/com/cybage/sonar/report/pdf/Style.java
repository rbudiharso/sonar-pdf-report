package com.cybage.sonar.report.pdf;

import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

public class Style {

	/**
	 * Font used in main chapters title
	 */
	public static final Font CHAPTER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.GRAY);

	/**
	 * Font used in sub-chapters title
	 */
	public static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.GRAY);

	/**
	 * Font used in graphics foots
	 */
	public static final Font FOOT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.GRAY);

	/**
	 * Font used in general plain text
	 */
	public static final Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);

	/**
	 * Font used in general plain text
	 */
	public static final Font NORMAL_HIGHLIGHTED_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL,
			BaseColor.RED);

	/**
	 * Font used in code text (bold)
	 */
	public static final Font MONOSPACED_BOLD_FONT = new Font(Font.FontFamily.COURIER, 11, Font.BOLD, BaseColor.BLACK);

	/**
	 * Font used in code text
	 */
	public static final Font MONOSPACED_FONT = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL, BaseColor.BLACK);

	/**
	 * Font used in table of contents title
	 */
	public static final Font TOC_TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.GRAY);

	/**
	 * Font used in front page (Project name)
	 */
	public static final Font FRONTPAGE_FONT_1 = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, BaseColor.BLACK);

	/**
	 * Font used in front page (Project description)
	 */
	public static final Font FRONTPAGE_FONT_2 = new Font(Font.FontFamily.HELVETICA, 18, Font.NORMAL, BaseColor.BLACK);

	/**
	 * Font used in front page (Project date)
	 */
	public static final Font FRONTPAGE_FONT_3 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLDITALIC,
			BaseColor.GRAY);

	/**
	 * Underlined font
	 */
	public static final Font UNDERLINED_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE, BaseColor.BLACK);

	/**
	 * Dashboard metric title font
	 */
	public static final Font DASHBOARD_TITLE_FONT = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL,
			new BaseColor(6, 12, 76));

	/**
	 * Dashboard metric value font
	 */
	public static final Font DASHBOARD_DATA_FONT = new Font(FontFamily.COURIER, 28, Font.NORMAL, BaseColor.DARK_GRAY);

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_DATA_FONT_2 = new Font(FontFamily.COURIER, 11, Font.NORMAL, BaseColor.DARK_GRAY);

	/**
	 * Dashboard metric details font
	 */
	public static final Font QUALITY_GATE_TITLE_FONT = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL,
			new BaseColor(6, 12, 76));

	/**
	 * Dashboard metric details font
	 */
	public static final Font QUALITY_GATE_PASSED_FONT = new Font(FontFamily.COURIER, 11, Font.NORMAL,
			BaseColor.DARK_GRAY);

	/**
	 * Dashboard metric details font
	 */
	public static final Font QUALITY_GATE_PASSED_FONT_2 = new Font(FontFamily.COURIER, 11, Font.NORMAL,
			BaseColor.DARK_GRAY);

	/**
	 * Dashboard metric details font
	 */
	public static final Font QUALITY_GATE_FAILED_FONT = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL,
			BaseColor.WHITE);

	/**
	 * Dashboard metric details font
	 */
	public static final Font QUALITY_GATE_FAILED_FONT_2 = new Font(FontFamily.COURIER, 11, Font.NORMAL,
			BaseColor.WHITE);

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_RATING_FONT_A = new Font(FontFamily.COURIER, 28, Font.NORMAL,
			new BaseColor(0, 153, 0));

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_RATING_FONT_B = new Font(FontFamily.COURIER, 28, Font.NORMAL,
			new BaseColor(156, 218, 12));

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_RATING_FONT_C = new Font(FontFamily.COURIER, 28, Font.NORMAL,
			new BaseColor(255, 255, 0));

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_RATING_FONT_D = new Font(FontFamily.COURIER, 28, Font.NORMAL,
			new BaseColor(255, 128, 0));

	/**
	 * Dashboard metric details font
	 */
	public static final Font DASHBOARD_RATING_FONT_E = new Font(FontFamily.COURIER, 28, Font.NORMAL,
			new BaseColor(255, 0, 0));

	/**
	 * Dashboard metric table background color
	 */
	public static final BaseColor DASHBOARD_NEW_METRIC_BACKGROUND_COLOR = new BaseColor(188, 226, 72);

	public static final BaseColor QUALITY_GATE_PASSED_COLOR = new BaseColor(188, 226, 72);

	public static final BaseColor QUALITY_GATE_FAILED_COLOR = new BaseColor(255, 0, 0);

	public static final Integer TABLE_SUBMETRIC_WIDTH_PERCENTAGE = 95;

	public static final Float TABLE_MAINMETRIC_WIDTH_PERCENTAGE = 95.5F;

	/**
	 * Tendency icons height + 2 (used in tables style)
	 */
	public static final int TENDENCY_ICONS_HEIGHT = 20;

	public static final float FRONTPAGE_LOGO_POSITION_X = 114;

	public static final float FRONTPAGE_LOGO_POSITION_Y = 542;

	public static void noBorderTable(final PdfPTable table) {
		table.getDefaultCell().setBorderColor(BaseColor.WHITE);
	}

	/**
	 * This method makes a simple table with content.
	 * 
	 * @param left
	 *            Data for left column
	 * @param right
	 *            Data for right column
	 * @param title
	 *            The table title
	 * @param noData
	 *            Showed when left or right are empty
	 * @return The table (iText table) ready to add to the document
	 */
	public static PdfPTable createSimpleTable(final List<String> left, final List<String> right, final String title,
			final String noData) {
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setColspan(2);
		table.addCell(new Phrase(title, Style.DASHBOARD_TITLE_FONT));
		table.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
		table.addCell("");
		table.getDefaultCell().setColspan(1);
		table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);

		Iterator<String> itLeft = left.iterator();
		Iterator<String> itRight = right.iterator();

		while (itLeft.hasNext()) {
			String textLeft = itLeft.next();
			String textRight = itRight.next();
			table.addCell(textLeft);
			table.addCell(textRight);
		}

		if (left.isEmpty()) {
			table.getDefaultCell().setColspan(2);
			table.addCell(noData);
		}

		table.setSpacingBefore(20);
		table.setSpacingAfter(20);

		return table;
	}

	public static PdfPTable createTwoColumnsTitledTable(final List<String> titles, final List<String> content) {
		PdfPTable table = new PdfPTable(10);
		Iterator<String> itLeft = titles.iterator();
		Iterator<String> itRight = content.iterator();
		while (itLeft.hasNext()) {
			String textLeft = itLeft.next();
			String textRight = itRight.next();
			table.getDefaultCell().setColspan(1);
			table.addCell(textLeft);
			table.getDefaultCell().setColspan(9);
			table.addCell(textRight);
		}
		table.setSpacingBefore(20);
		table.setSpacingAfter(20);
		table.setLockedWidth(false);
		table.setWidthPercentage(90);
		return table;
	}
}
