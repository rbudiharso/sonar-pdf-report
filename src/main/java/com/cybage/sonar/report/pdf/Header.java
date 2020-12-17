package com.cybage.sonar.report.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cybage.sonar.report.pdf.entity.Project;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.events.PdfPageEventForwarder;

public class Header extends PdfPageEventForwarder {

    private static final Logger LOG = LoggerFactory.getLogger(Header.class);

    private final URL     logo;
    private final Project project;

    public Header(final URL logo, final Project project) {
        this.logo = logo;
        this.project = project;
    }

    @Override
    public void onEndPage(final PdfWriter writer, final Document document) {
        try {
            Image     logoImage = Image.getInstance(logo);
            Rectangle page      = document.getPageSize();
            PdfPTable head      = new PdfPTable(4);
            head.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            head.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            head.addCell(logoImage);
            Phrase projectName = new Phrase(project.getName(),
                    FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, BaseColor.GRAY));
            Phrase phrase = new Phrase("Sonar PDF Report",
                    FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, BaseColor.GRAY));
            head.getDefaultCell().setColspan(2);
            head.addCell(phrase);
            head.getDefaultCell().setColspan(1);
            head.addCell(projectName);
            head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - 20, writer.getDirectContent());
            head.setSpacingAfter(10);
        } catch (BadElementException | IOException e) {
            LOG.error("Can not generate PDF header", e);
        }
    }

}
