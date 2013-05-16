package org.cognoscenti.reportdispatcher.service;

import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

public interface SchedulerService {
	void schedule(ReportDispatchRecord reportDispatchRecord);
	void start(ReportDispatchRecord reportDispatchRecord);
	void stop(ReportDispatchRecord reportDispatchRecord);
	boolean isActive(ReportDispatchRecord reportDispatchRecord);
}