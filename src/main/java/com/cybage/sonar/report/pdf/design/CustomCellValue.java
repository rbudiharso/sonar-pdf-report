package com.cybage.sonar.report.pdf.design;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class CustomCellValue extends PdfPCell {

	public CustomCellValue(Phrase phrase) {
		super(phrase);
		this.setVerticalAlignment(ALIGN_CENTER);
		this.setHorizontalAlignment(ALIGN_RIGHT);
		this.setExtraParagraphSpace(5);
	}
}
