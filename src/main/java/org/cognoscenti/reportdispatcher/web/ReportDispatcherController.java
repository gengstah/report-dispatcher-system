package org.cognoscenti.reportdispatcher.web;

import org.cognoscenti.reportdispatcher.service.ReportDispatchRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportDispatcherController {
	private ReportDispatchRecordService service;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayHomePage(Model model) {
		//model.addAttribute("reportDispatchRecords", service.listReportDispatchRecord());
		
		return "home";
	}

	/**
	 * @return the service
	 */
	public ReportDispatchRecordService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ReportDispatchRecordService service) {
		this.service = service;
	}
}