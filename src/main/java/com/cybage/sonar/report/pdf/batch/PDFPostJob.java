package com.cybage.sonar.report.pdf.batch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.postjob.PostJob;
import org.sonar.api.batch.postjob.PostJobContext;
import org.sonar.api.batch.postjob.PostJobDescriptor;
import org.sonar.api.config.Configuration;

import com.cybage.sonar.report.pdf.util.LeakPeriods;

import java.util.Arrays;

public class PDFPostJob implements PostJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFPostJob.class);

	private final FileSystem fs;
	private Configuration configuration;

	public PDFPostJob(Configuration configuration, FileSystem fs) {
		this.fs = fs;
		this.configuration = configuration;
	}

	public static final String SKIP_PDF_KEY = "sonar.pdf.skip";
	public static final boolean SKIP_PDF_DEFAULT_VALUE = false;

	public static final String REPORT_TYPE = "report.type";
	public static final String REPORT_TYPE_DEFAULT_VALUE = "pdf";

	public static final String USERNAME = "sonar.login";
	public static final String USERNAME_DEFAULT_VALUE = "";

	public static final String PASSWORD = "sonar.password";
	public static final String PASSWORD_DEFAULT_VALUE = "";

	public static final String SONAR_HOST_URL = "sonar.host.url";
	public static final String SONAR_HOST_URL_DEFAULT_VALUE = "http://localhost:9000";

	public static final String SONAR_PROJECT_VERSION = "sonar.projectVersion";
	public static final String SONAR_PROJECT_VERSION_DEFAULT_VALUE = "1.0";

	public static final String SONAR_LANGUAGE = "sonar.language";

	public static final String OTHER_METRICS = "sonar.pdf.other.metrics";

	public static final String TYPES_OF_ISSUE = "sonar.pdf.issue.details";

	public static final String LEAK_PERIOD = "sonar.leak.period";
	public static final String LEAK_PERIOD_DEFAULT_VALUE = LeakPeriods.PREVIOUS_VERSION;

	@Override
	public void describe(PostJobDescriptor arg0) {

	}

	@Override
	public void execute(PostJobContext postJobContext) {
		if (postJobContext.config().hasKey(SKIP_PDF_KEY)
				&& postJobContext.config().getBoolean(SKIP_PDF_KEY).get() == true) {
			LOGGER.info("Skipping generation of PDF Report..");
		} else {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String projectKey = postJobContext.config().get("sonar.projectKey").get();

			LOGGER.info("Executing decorator: PDF Report");
			String sonarHostUrl = postJobContext.config().hasKey(SONAR_HOST_URL)
					? postJobContext.config().get(SONAR_HOST_URL).get() : SONAR_HOST_URL_DEFAULT_VALUE;
			String username = postJobContext.config().hasKey(USERNAME) ? postJobContext.config().get(USERNAME).get()
					: USERNAME_DEFAULT_VALUE;
			String password = postJobContext.config().hasKey(PASSWORD) ? postJobContext.config().get(PASSWORD).get()
					: PASSWORD_DEFAULT_VALUE;
			String reportType = postJobContext.config().hasKey(REPORT_TYPE)
					? postJobContext.config().get(REPORT_TYPE).get() : REPORT_TYPE_DEFAULT_VALUE;
			String projectVersion = postJobContext.config().hasKey(SONAR_PROJECT_VERSION)
					? postJobContext.config().get(SONAR_PROJECT_VERSION).get() : SONAR_PROJECT_VERSION_DEFAULT_VALUE;
			List<String> sonarLanguage = postJobContext.config().hasKey(SONAR_LANGUAGE)
					? Arrays.asList(postJobContext.config().getStringArray(SONAR_LANGUAGE)) : null;
			Set<String> otherMetrics = postJobContext.config().hasKey(OTHER_METRICS)
					? new HashSet<String>(Arrays.asList(postJobContext.config().getStringArray(OTHER_METRICS))) : null;
			Set<String> typesOfIssue = postJobContext.config().hasKey(TYPES_OF_ISSUE)
					? new HashSet<String>(Arrays.asList(postJobContext.config().getStringArray(TYPES_OF_ISSUE)))
					: new HashSet<>();
			String leakPeriod = postJobContext.config().hasKey(LEAK_PERIOD)
					? postJobContext.config().get(LEAK_PERIOD).get() : LEAK_PERIOD_DEFAULT_VALUE;

			LOGGER.info("Leak Period : " + leakPeriod);

			PDFGenerator generator = new PDFGenerator(projectKey, projectVersion, sonarLanguage, otherMetrics,
					typesOfIssue, leakPeriod, fs, sonarHostUrl, username, password, reportType);

			try {
				generator.execute();
			} catch (Exception ex) {
				LOGGER.error("Error in generating PDF report.");
			}

		}
		/*
		 * String path = fs.workDir().getAbsolutePath() + "/" +
		 * projectKey.replace(':', '-') + ".pdf";
		 * 
		 * File pdf = new File(path); if (pdf.exists()) {
		 * FileUploader.upload(pdf, sonarHostUrl, username, password); } else {
		 * LOGGER.
		 * error("PDF file not found in local filesystem. Report could not be sent to server."
		 * ); }
		 */

	}

}
