package org.report;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ReportingClass {
	
	public static void generateReport(String json) {

		// To Locate Where Report is stored 
		File file = new File("C:\\Users\\jayakumart\\eclipse-workspace\\Fleet_Automation\\Report");
		
		Configuration con = new Configuration(file, "Fleet Management WebPage");
		con.addClassifications("User_Story", "1000");
		con.addClassifications("Module", "Login_Module");
		con.addClassifications("Version", "17 JDK");
		
		List<String> li = new LinkedList<String>();
		li.add(json);
		
		ReportBuilder r = new ReportBuilder(li, con);
		r.generateReports();
		
	}

}
