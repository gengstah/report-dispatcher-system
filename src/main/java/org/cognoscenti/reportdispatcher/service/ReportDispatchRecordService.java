package org.cognoscenti.reportdispatcher.service;

import java.util.List;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

/**
 * The interface that provides service methods 
 * on managing a {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
 * 
 * @author Geng
 * @version 1.0
 */
public interface ReportDispatchRecordService {
	/**
	 * Persists {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @param o The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be added
	 * @return The managed entity version of {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 */
	ReportDispatchRecord addReportDispatchRecord(ReportDispatchRecord o);
	
	/**
	 * Updates {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @param o The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} to be updated
	 * @return The managed entity version of {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 */
	ReportDispatchRecord updateReportDispatchRecord(ReportDispatchRecord o);
	
	/**
	 * Removes {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @param id The id of the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * to be removed
	 */
	void removeReportDispatchRecord(Long id);
	
	/**
	 * Finds a {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} using its id
	 * 
	 * @param id The id of the {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * @return The {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord} object
	 */
	ReportDispatchRecord getReportDispatchRecord(Long id);
	
	/**
	 * Lists all {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 * 
	 * @return The list of {@link org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord}
	 */
	List<ReportDispatchRecord> listReportDispatchRecord();
}