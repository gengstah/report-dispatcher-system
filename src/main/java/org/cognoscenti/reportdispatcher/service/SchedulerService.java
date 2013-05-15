package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public interface SchedulerService {
	void createSchedule(ReportDispatchRecord reportDispatchRecord);
}