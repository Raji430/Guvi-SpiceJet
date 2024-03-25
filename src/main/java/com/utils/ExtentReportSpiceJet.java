package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportSpiceJet {
	
	public static ExtentReports getReport()
	{		
		//Create Report
		String reportPath = System.getProperty("user.dir")+ "\\SpiceJetTestReport.html";			
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("SpiceJetTest Report");
		
		//Add automation data to physical report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);		
		return extent;
	}
}
