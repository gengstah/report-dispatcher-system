package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

/**
 * Service interface for sending mails
 * 
 * @author Geng
 * @version 1.0
 */
public interface MailService {
	/**
	 * Sends a mail with the given {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * @see org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord
	 */
	void sendMail(ReportDispatchRecord reportDispatchRecord);
}