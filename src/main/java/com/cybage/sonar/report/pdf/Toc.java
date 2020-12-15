package com.cybage.sonar.report.pdf;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Toc extends PdfPageEventHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Events.class);

	private Document toc;
	private ByteArrayOutputStream tocOutputStream;
	private PdfPTable content;
	private PdfWriter writer;

	public Toc() throws DocumentException {
		toc = new Document(PageSize.A4, 50, 50, 110, 50);
		content = new PdfPTable(2);
		Rectangle page = toc.getPageSize();
		content.setWidths(new int[] { 5, 2 });
		content.setTotalWidth(page.getWidth() - toc.leftMargin() - toc.rightMargin());
		content.getDefaultCell().setUseVariableBorders(true);
		content.getDefaultCell().setBorderColorBottom(BaseColor.WHITE);
		content.getDefaultCell().setBorderColorRight(BaseColor.WHITE);
		content.getDefaultCell().setBorderColorLeft(BaseColor.WHITE);
		content.getDefaultCell().setBorderColorTop(BaseColor.WHITE);
		content.getDefaultCell().setBorderWidthBottom(2f);
	}

	@Override
	public void onChapter(final PdfWriter writer, final Document document, final float position,
			final Paragraph title) {
		content.getDefaultCell().setBorderColorBottom(BaseColor.LIGHT_GRAY);
		content.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		content.getDefaultCell().setUseBorderPadding(true);
		content.addCell(new Phrase(title.getContent(), new Font(Font.FontFamily.HELVETICA, 11)));
		content.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		content.addCell(new Phrase("Page " + document.getPageNumber(), new Font(Font.FontFamily.HELVETICA, 11)));
		content.getDefaultCell().setBorderColorBottom(BaseColor.WHITE);
		content.getDefaultCell().setUseBorderPadding(false);
	}

	@Override
	public void onChapterEnd(final PdfWriter writer, final Document document, final float position) {
		content.addCell("");
		content.addCell("");
	}

	@Override
	public void onSection(final PdfWriter writer, final Document document, final float position, final int depth,
			final Paragraph title) {
		content.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		switch (depth) {
		case 2:
			content.getDefaultCell().setIndent(10);
			content.addCell(new Phrase(title.getContent(), new Font(Font.FontFamily.HELVETICA, 10)));
			content.getDefaultCell().setIndent(0);
			content.addCell("");
			break;
		default:
			content.getDefaultCell().setIndent(20);
			content.addCell(new Phrase(title.getContent(), new Font(Font.FontFamily.HELVETICA, 9)));
			content.getDefaultCell().setIndent(0);
			content.addCell("");
		}
	}

	@Override
	public void onCloseDocument(final PdfWriter writer, final Document document) {
		try {
			toc.add(content);
		} catch (DocumentException e) {
			LOGGER.error("Can not add TOC", e);
		}
	}

	public Document getTocDocument() {
		return toc;
	}

	public ByteArrayOutputStream getTocOutputStream() {
		return tocOutputStream;
	}

	public void setHeader(final Header header) {
		tocOutputStream = new ByteArrayOutputStream();
		writer = null;
		try {
			writer = PdfWriter.getInstance(toc, tocOutputStream);
			writer.setPageEvent(header);
		} catch (DocumentException e) {
			LOGGER.error("Can not add TOC", e);
		}
	}
}
