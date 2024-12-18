package org.runner;

import java.io.File;

import org.config.ConfigReader;
//import org.config.EmailReportManager;
import org.config.EmailUtil;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.ReportingClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
dryRun = !true,
glue= {"org.steps","hooks"},
monochrome = true,
//,tags = "@smoke"	
snippets=SnippetType.CAMELCASE,
plugin = { "pretty", "html:report/cucumber-reports/report.html",
		"json:report/cucumber-reports/report.json",
		"junit:report/cucumber-reports/report.xml" })

public class TestRunnerClass  {


	@AfterClass
	public static void report() {
      ReportingClass.generateReport("target\\jsonReport.json");
      System.out.println("Email started to trigger in testrunner class");
//
//		 EmailReportManager.getReportInstance().flush();
//		 System.out.println("Email Entered1");
   // Example: Send email with the report generated
   // Fetch the report path from config.properties
   // Example: Fetching the report path and sending email
      ConfigReader config = new ConfigReader();
//      String reportPath = config.getReportPath();
//      EmailUtil.sendEmail(reportPath);
      
      File reportFile = new File(config.getReportPath());
      if (!reportFile.exists()) {
          System.out.println("Test report not found at: " + reportFile.getAbsolutePath());
      } else {
          EmailUtil.sendEmail(reportFile.getAbsolutePath());
      }

	}
}
