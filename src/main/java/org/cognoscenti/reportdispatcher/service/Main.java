package org.cognoscenti.reportdispatcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public class Main {
	public static void main(String[] args) {
		MailServiceImpl mailService = new MailServiceImpl();
		mailService.setUsername("cognoscentisoftware@gmail.com");
		mailService.setPassword("cognoscenti123789");
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		mailService.setProps(props);
		
		ReportDispatchRecord reportDispatchRecord = new ReportDispatchRecord();
		reportDispatchRecord.setReportDispatchRecordId(1L);
		List<String> emails = new ArrayList<String>();
		emails.add("cognoscentisoftware@gmail.com");
		emails.add("gerardpdelasarmas@gmail.com");
		emails.add("delasg@ph.ibm.com");
		reportDispatchRecord.setEmails(emails);
		reportDispatchRecord.setCronSchedule("59 * * * * ?");
		
		List<String> attachments = new ArrayList<String>();
		attachments.add("sample-mail-attachment.txt");
		reportDispatchRecord.setAttachments(attachments);
		
		/*mailService.sendMail(reportDispatchRecord);*/
		
		SchedulerServiceImpl scheduler = new SchedulerServiceImpl();
		scheduler.setMailService(mailService);
		
		//scheduler.addSchedule(reportDispatchRecord);
		System.out.println(reportDispatchRecord.getScheduleDescription());
	}
}