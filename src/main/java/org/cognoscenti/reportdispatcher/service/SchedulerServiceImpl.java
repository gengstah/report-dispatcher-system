package org.cognoscenti.reportdispatcher.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.job.ReportDispatcherJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.JobDetailBean;

public class SchedulerServiceImpl implements SchedulerService {
	private final Logger logger = Logger.getLogger(getClass());
	public static final String REPORT_DISPATCH = "REPORT DISPATCH";
    private MailService mailService;
    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void createSchedule(ReportDispatchRecord reportDispatchRecord) {
		schedulerFactory = new StdSchedulerFactory();
		
		try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) { logger.error(e.getMessage()); }
		
		JobDetailBean jobDetail = new JobDetailBean();
        jobDetail.setName(REPORT_DISPATCH+" JOB " + reportDispatchRecord.getReportDispatchRecordId());
        jobDetail.setJobClass(ReportDispatcherJob.class);
        
        Map jobDataAsMap = new HashMap();
        jobDataAsMap.put("reportDispatchRecord", reportDispatchRecord);
        jobDataAsMap.put("mailService", reportDispatchRecord);
        jobDetail.setJobDataAsMap(jobDataAsMap);
        
        CronTrigger trigger = new CronTrigger();
		trigger.setName(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId());
		trigger.setGroup("Report Dispatch");
		
		try {
			trigger.setCronExpression(reportDispatchRecord.getCronSchedule());
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) { logger.error(e.getMessage()); 
        } catch (ParseException e) { logger.error(e.getMessage()); }
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
}