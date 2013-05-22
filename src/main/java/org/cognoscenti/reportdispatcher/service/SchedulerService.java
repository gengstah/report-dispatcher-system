package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

/**
 * Service interface for managing schedulers, jobs and triggers
 * 
 * @author Geng
 * @version 1.0
 */
public interface SchedulerService {
	/**
	 * Add the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to the scheduler
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be 
	 * added to scheduler
	 */
	void addSchedule(ReportDispatchRecord reportDispatchRecord);
	
	/**
	 * Update the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} in the scheduler
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be 
	 * updated in the scheduler
	 */
	void updateSchedule(ReportDispatchRecord reportDispatchRecord);
	
	/**
	 * Remove the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} of the given id 
	 * in the scheduler
	 * 
	 * @param id The id of the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be 
	 * removed
	 */
	void removeSchedule(Long id);
	
	/**
	 * Start the trigger for the given {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be 
	 * started
	 */
	void start(ReportDispatchRecord reportDispatchRecord);
	
	/**
	 * Stop the trigger for the given {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be 
	 * stopped
	 */
	void stop(ReportDispatchRecord reportDispatchRecord);
	
	/**
	 * Determines if the given {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} is active or 
	 * not
	 * 
	 * @param reportDispatchRecord The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} whose 
	 * job is in question
	 * @return True if active, otherwise, false
	 */
	boolean isActive(ReportDispatchRecord reportDispatchRecord);
}