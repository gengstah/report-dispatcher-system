package org.cognoscenti.reportdispatcher.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.dao.DataAccessObject;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public class ReportDispatchRecordServiceImpl implements
		ReportDispatchRecordService {
	private final Logger logger = Logger.getLogger(getClass());
	private DataAccessObject dao;
	
	@Override
	public ReportDispatchRecord addReportDispatchRecord(ReportDispatchRecord o) {
		logger.info("Adding a new ReportDispatchRecord: " + o);
		return (ReportDispatchRecord) dao.add(o);
	}

	@Override
	public ReportDispatchRecord updateReportDispatchRecord(ReportDispatchRecord o) {
		logger.info("Updating a ReportDispatchRecord: " + o);
		return (ReportDispatchRecord) dao.update(o);
	}

	@Override
	public void removeReportDispatchRecord(ReportDispatchRecord o) {
		logger.info("Removing a ReportDispatchRecord: " + o);
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