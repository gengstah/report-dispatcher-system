package org.cognoscenti.reportdispatcher.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.job.ReportDispatcherJob;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.JobDetailBean;

public class SchedulerServiceImpl implements SchedulerService {
	private final Logger logger = Logger.getLogger(getClass());
	public static final String REPORT_DISPATCH = "REPORT DISPATCH";
	public static final String REPORT_DISPATCH_GROUP = "REPORT DISPATCH GROUP";
	private ReportDispatchRecordService reportDispatchRecordService;
    private MailService mailService;
    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;
    private boolean autoStart;
    
    public void init() {
    	logger.info("Getting a StdSchedulerFactory");
    	schedulerFactory = new StdSchedulerFactory();
    	
    	getScheduler();
		addAllReportDispatchRecordsToScheduler();
		startScheduler();
		
		if(!autoStart) {
			stopAllTrigger();
		}
    }

	private void getScheduler() {
		logger.info("Getting a scheduler");
    	
		try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) { logger.error(e.getMessage()); }
	}
	
	private void addAllReportDispatchRecordsToScheduler() {
		logger.info("Getting the list of ReportDispatchRecord to be added to the scheduler");
		List<ReportDispatchRecord> reportDispatchRecords = reportDispatchRecordService.listReportDispatchRecord();
		
		for(ReportDispatchRecord reportDispatchRecord : reportDispatchRecords) {
			schedule(reportDispatchRecord);
			
			logger.info(reportDispatchRecord + " has been added to the scheduler");
		}
	}
	
	private void startScheduler() {
		logger.info("Starting the scheduler");
		
		try {
			scheduler.start();
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
	}
	
	private void stopAllTrigger() {
		logger.info("Stopping all triggers");
		String[] triggerNames = new String[0];
		
		try {
			triggerNames = scheduler.getTriggerNames(REPORT_DISPATCH_GROUP);
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
		
		for(String triggerName : triggerNames) {
			logger.info("Stopping trigger with trigger name: " + triggerName);
			stop(triggerName);
		}
	}
    
    public void destroy() {
    	logger.info("Scheduler is shutting down");
    	try {
			scheduler.shutdown(true);
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void schedule(ReportDispatchRecord reportDispatchRecord) {
		logger.info("Constructing JobDetailBean");
		JobDetailBean jobDetail = new JobDetailBean();
        jobDetail.setName(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId());
        jobDetail.setGroup(REPORT_DISPATCH_GROUP);
        jobDetail.setJobClass(ReportDispatcherJob.class);
        
        Map jobDataAsMap = new HashMap();
        jobDataAsMap.put("reportDispatchRecord", reportDispatchRecord);
        jobDataAsMap.put("mailService", mailService);
        jobDetail.setJobDataAsMap(jobDataAsMap);
        
        logger.info("Constructing CronTrigger");
        CronTrigger trigger = new CronTrigger();
		trigger.setName(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId());
		trigger.setGroup(REPORT_DISPATCH_GROUP);
		
		try {
			trigger.setCronExpression(reportDispatchRecord.getCronSchedule());
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) { logger.error(e.getMessage()); 
        } catch (ParseException e) { logger.error(e.getMessage()); }
	}
	
	@Override
	public void start(ReportDispatchRecord reportDispatchRecord) {
		start(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId());
	}
	
	private void start(String triggerName) {
		try {
			scheduler.resumeTrigger(triggerName, REPORT_DISPATCH_GROUP);
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
	}

	@Override
	public void stop(ReportDispatchRecord reportDispatchRecord) {
		stop(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId());
	}
	
	private void stop(String triggerName) {
		try {
			scheduler.pauseTrigger(triggerName, REPORT_DISPATCH_GROUP);
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
	}
	
	@Override
	public boolean isActive(ReportDispatchRecord reportDispatchRecord) {
		try {
			if(!(scheduler.getTriggerState(REPORT_DISPATCH + reportDispatchRecord.getReportDispatchRecordId(), REPORT_DISPATCH_GROUP) == Trigger.STATE_PAUSED))
				return true;
		} catch (SchedulerException e) { logger.error(e.getMessage()); }
		
		return false;
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
	 * @return the reportDispatchRecordService
	 */
	public ReportDispatchRecordService getReportDispatchRecordService() {
		return reportDispatchRecordService;
	}

	/**
	 * @param reportDispatchRecordService the reportDispatchRecordService to set
	 */
	public void setReportDispatchRecordService(
			ReportDispatchRecordService reportDispatchRecordService) {
		this.reportDispatchRecordService = reportDispatchRecordService;
	}

	/**
	 * @return the autoStart
	 */
	public boolean isAutoStart() {
		return autoStart;
	}

	/**
	 * @param autoStart the autoStart to set
	 */
	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}
}