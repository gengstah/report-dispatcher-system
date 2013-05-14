package org.cognoscenti.reportdispatcher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.cognoscenti.reportdispatcher.domain.Attachment;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public class Main {
	public static void main(String[] args) {
		ReportDispatchRecordServiceImpl service = new ReportDispatchRecordServiceImpl();
		service.setUsername("cognoscentisoftware@gmail.com");
		service.setPassword("cognoscenti123789");
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		service.setProps(props);
		
		ReportDispatchRecord reportDispatchRecord = new ReportDispatchRecord();
		List<String> emails = new ArrayList<String>();
		emails.add("cognoscentisoftware@gmail.com");
		emails.add("gerardpdelasarmas@gmail.com");
		emails.add("delasg@ph.ibm.com");
		reportDispatchRecord.setEmails(emails);
		
		List<Attachment> attachments = new ArrayList<Attachment>();
		attachments.add(new Attachment("/sample-mail-attachment.txt"));
		reportDispatchRecord.setAttachments(attachments);
		
		service.sendMail(reportDispatchRecord);
	}
}