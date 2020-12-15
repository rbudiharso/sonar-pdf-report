package com.cybage.sonar.report.pdf.design;

import com.cybage.sonar.report.pdf.Style;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public class CustomTable extends PdfPTable {

	public CustomTable(Integer cols) {
		super(cols);
		this.setWidthPercentage(Style.TABLE_SUBMETRIC_WIDTH_PERCENTAGE);
		this.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	}

}
