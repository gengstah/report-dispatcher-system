package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public interface MailService {
	void sendMail(ReportDispatchRecord reportDispatchRecord);
}