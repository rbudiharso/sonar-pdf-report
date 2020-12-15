package com.cybage.sonar.report.pdf.design;

import com.cybage.sonar.report.pdf.Style;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;

public class CustomMainTable extends PdfPTable {

	public CustomMainTable(Integer cols) {
		super(cols);
		this.setWidthPercentage(Style.TABLE_MAINMETRIC_WIDTH_PERCENTAGE);
		this.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		this.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	}

}
