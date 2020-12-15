package com.cybage.sonar.report.pdf.batch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.fs.FileSystem;

import com.cybage.sonar.report.pdf.ExecutivePDFReporter;
import com.cybage.sonar.report.pdf.PDFReporter;
import com.cybage.sonar.report.pdf.entity.exception.ReportException;
import com.cybage.sonar.report.pdf.util.Credentials;
import com.itextpdf.text.DocumentException;

public class PDFGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

	private String sonarHostUrl;
	private String username;
	private String password;
	private String reportType;
	private String projectKey;
	private String projectVersion;
	private List<String> sonarLanguage;
	private Set<String> otherMetrics;
	private Set<String> typesOfIssue;
	private String leakPeriod;
	private FileSystem fs;

	public PDFGenerator(final String projectKey, final String projectVersion, final List<String> sonarLanguage,
			final Set<String> otherMetrics, final Set<String> typesOfIssue, final String leakPeriod,
			final FileSystem fs, final String sonarHostUrl, final String username, final String password,
			final String reportType) {
		this.projectKey = projectKey;
		this.projectVersion = projectVersion;
		this.sonarLanguage = sonarLanguage;
		this.otherMetrics = otherMetrics;
		this.typesOfIssue = typesOfIssue;
		this.leakPeriod = leakPeriod;
		this.fs = fs;
		this.sonarHostUrl = sonarHostUrl;
		this.username = username;
		this.password = password;
		this.reportType = reportType;
	}

	public void execute() {
		Properties config = new Properties();
		Properties configLang = new Properties();

		try {
			if (sonarHostUrl != null) {
				if (sonarHostUrl.endsWith("/")) {
					sonarHostUrl = sonarHostUrl.substring(0, sonarHostUrl.length() - 1);
				}
				config.put("sonar.base.url", sonarHostUrl);
				config.put("front.page.logo", "sonar.png");
			} else {
				config.load(this.getClass().getResourceAsStream("/report.properties"));
			}
			configLang.load(this.getClass().getResourceAsStream("/report-texts-en.properties"));

			Credentials credentials = new Credentials(config.getProperty("sonar.base.url"), username, password);

			String sonarProjectId = projectKey;
			String sonarProjectVersion = projectVersion;
			List<String> sonarLanguage = this.sonarLanguage;
			Set<String> otherMetrics = this.otherMetrics;
			Set<String> typesOfIssue = this.typesOfIssue;
			String leakPeriod = this.leakPeriod;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

			String path = fs.workDir().getAbsolutePath() + "/" + sonarProjectId.replace(':', '-') + "-"
					+ sdf.format(new Timestamp(System.currentTimeMillis())) + ".pdf";

			PDFReporter reporter = null;
			if (reportType != null) {
				if (reportType.equals("pdf")) {
					// LOGGER.info("PDF report type selected");
					reporter = new ExecutivePDFReporter(credentials, this.getClass().getResource("/sonar.png"),
							sonarProjectId, sonarProjectVersion, sonarLanguage, otherMetrics, typesOfIssue, leakPeriod,
							config, configLang);
				}
			} else {
				LOGGER.info("No report type provided. Default report type selected (PDF)");
			}

			ByteArrayOutputStream baos = reporter.getReport();
			FileOutputStream fos = new FileOutputStream(new File(path));
			baos.writeTo(fos);
			fos.flush();
			fos.close();
			LOGGER.info("PDF report generated (see " + sonarProjectId.replace(':', '-') + "-"
					+ sdf.format(new Timestamp(System.currentTimeMillis())) + ".pdf on build output directory)");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			LOGGER.error("Problem in generating PDF file.");
			e.printStackTrace();
		} catch (ReportException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
