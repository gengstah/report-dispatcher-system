package org.cognoscenti.reportdispatcher.job;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.service.MailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ReportDispatcherJob extends QuartzJobBean {
	private MailService mailService;
	private ReportDispatchRecord reportDispatchRecord;
	
	public ReportDispatcherJob() { }
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		mailService.sendMail(reportDispatchRecord);
	}

	/**
	 * @return the mailService
	 */
	public MailService getMailService() {
		return mailService;
	}

	/**
	 * @param mailService the mailService to set
	 */
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * @return the reportDispatchRecord
	 */
	public ReportDispatchRecord getReportDispatchRecord() {
		return reportDispatchRecord;
	}

	/**
	 * @param reportDispatchRecord the reportDispatchRecord to set
	 */
	public void setReportDispatchRecord(ReportDispatchRecord reportDispatchRecord) {
		this.reportDispatchRecord = reportDispatchRecord;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportDispatcherJob [mailService=" + mailService
				+ ", reportDispatchRecord=" + reportDispatchRecord + "]";
	}
}