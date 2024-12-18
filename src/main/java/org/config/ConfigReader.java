package org.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	 private Properties properties;

	    public ConfigReader() {
	        properties = new Properties();
	        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
	            if (input == null) {
	                System.out.println("Sorry, unable to find config.properties");
	                return;
	            }
	            properties.load(input);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	    public String getUrl() {
	        return properties.getProperty("url");
	    }

	    public String getUsername() {
	        return properties.getProperty("username");
	    }

	    public String getPassword() {
	        return properties.getProperty("password");
	    }
	    public String getScreenshotPath() {
	        return properties.getProperty("screenshot.path");
	    }
	    public String getReportPath() {
	        String reportPath = properties.getProperty("report.path");
	        if (reportPath == null || reportPath.isEmpty()) {
	            System.out.println("Report path is not specified in the config file.");
	        }
	        return reportPath;
	    }

	    public String getSMTPSenderEmail() {
	        return properties.getProperty("SENDEREMAIL");
	    }
	    public String getSMTPPassword() {
	        return properties.getProperty("SENDERPASSWORD");
	    }
	    public String getSMTPRecipientEmail() {
	        return properties.getProperty("RECEPIENTEMAIL");
	    }
	    
}
