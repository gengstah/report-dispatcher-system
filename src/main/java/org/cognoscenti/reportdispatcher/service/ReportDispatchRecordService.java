package org.cognoscenti.reportdispatcher.service;

import java.util.List;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public interface ReportDispatchRecordService {
	ReportDispatchRecord addReportDispatchRecord(ReportDispatchRecord o);
	ReportDispatchRecord updateReportDispatchRecord(ReportDispatchRecord o);
	void removeReportDispatchRecord(ReportDispatchRecord o);
	ReportDispatchRecord getReportDispatchRecord(Long id);
	List<ReportDispatchRecord> listReportDispatchRecord();
}