package org.cognoscenti.reportdispatcher.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.dao.DataAccessObject;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

/**
 * Default implementation of {@link org.cognoscenti.reportdispatcher.service.ReportDispatchRecordService}
 * 
 * @author Geng
 * @version 1.0
 * @see org.cognoscenti.reportdispatcher.service.ReportDispatchRecordService
 */
public class ReportDispatchRecordServiceImpl implements
		ReportDispatchRecordService {
	private final Logger logger = Logger.getLogger(getClass());
	private DataAccessObject dao;
	
	private void removeEmptyAttachments(ReportDispatchRecord o) {
		for(int i=0;i<o.getAttachments().size();i++)
			if(o.getAttachments().get(i) == null || o.getAttachments().get(i).equals("")) { 
				o.getAttachments().remove(i--); 
			}
	}
	
	@Override
	public ReportDispatchRecord addReportDispatchRecord(ReportDispatchRecord o) {
		logger.info("Adding a new ReportDispatchRecord: " + o);
		removeEmptyAttachments(o);
		return (ReportDispatchRecord) dao.add(o);
	}

	@Override
	public ReportDispatchRecord updateReportDispatchRecord(ReportDispatchRecord o) {
		logger.info("Updating a ReportDispatchRecord: " + o);
		removeEmptyAttachments(o);
		return (ReportDispatchRecord) dao.update(o);
	}

	@Override
	public void removeReportDispatchRecord(Long id) {
		logger.info("Removing a ReportDispatchRecord with id: " + id);
		ReportDispatchRecord o = getReportDispatchRecord(id);
		
		logger.info("Found the ReportDispatchRecord with id :" + id + "\n" + o);
		dao.remove(o);
	}

	@Override
	public ReportDispatchRecord getReportDispatchRecord(Long id) {
		logger.info("Fetching a ReportDispatchRecord: " + id);
		
		ReportDispatchRecord reportDispatchRecord = dao.get(id, ReportDispatchRecord.class);
		fetchLazyCollections(reportDispatchRecord);
		
		return reportDispatchRecord;
	}

	@Override
	public List<ReportDispatchRecord> listReportDispatchRecord() {
		logger.info("Fetching all ReportDispatchRecord");
		List<ReportDispatchRecord> reportDispatchRecords = dao.list(ReportDispatchRecord.class);
		
		logger.info("Fetching all lazy element collection");
		for(ReportDispatchRecord reportDispatchRecord : reportDispatchRecords) {
			fetchLazyCollections(reportDispatchRecord);
		}
		
		return reportDispatchRecords;
	}

	private void fetchLazyCollections(ReportDispatchRecord reportDispatchRecord) {
		for(String email : reportDispatchRecord.getEmails()) {
			logger.info(email.toString());
		}
		
		for(String attachment : reportDispatchRecord.getAttachments()) {
			logger.info(attachment.toString());
		}
	}

	/**
	 * @return the dao
	 */
	public DataAccessObject getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
}