package com.cybage.sonar.report.pdf.design;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class CustomCellTitle extends PdfPCell {
	
	public CustomCellTitle(Phrase phrase) {
		super(phrase);
		this.setVerticalAlignment(ALIGN_CENTER);
		this.setHorizontalAlignment(ALIGN_LEFT);
		this.setExtraParagraphSpace(5);
	}
}
