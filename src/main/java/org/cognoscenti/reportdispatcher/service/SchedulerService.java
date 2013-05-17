package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public interface SchedulerService {
	void addSchedule(ReportDispatchRecord reportDispatchRecord);
	void updateSchedule(ReportDispatchRecord reportDispatchRecord);
	void removeSchedule(ReportDispatchRecord reportDispatchRecord);
	void start(ReportDispatchRecord reportDispatchRecord);
	void stop(ReportDispatchRecord reportDispatchRecord);
	boolean isActive(ReportDispatchRecord reportDispatchRecord);
}