package org.cognoscenti.reportdispatcher.validator;

import java.util.List;
import java.util.regex.Pattern;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.service.SchedulerServiceImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReportDispatchRecordValidator implements Validator {
	private final static Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ReportDispatchRecord.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReportDispatchRecord record = (ReportDispatchRecord) target;
		
		if(!SchedulerServiceImpl.isValidCronExpression(record.getCronSchedule())) errors.rejectValue("cronSchedule", "cronSchedule.invalid");
		
		List<String> attachments = record.getAttachments();
		if(attachments.size() == 0) errors.rejectValue("attachments", "attachments.empty");
		
		List<String> emails = record.getEmails();
		if(emails.size() == 0) errors.rejectValue("emails", "emails.empty");
		for(String email : emails)
			if(!isEmail(email)) errors.rejectValue("emails", "emails.invalid");
	}
	
	private boolean isEmail(String value) {
		return EMAIL_PATTERN.matcher(value).matches();
	}
}