package org.cognoscenti.reportdispatcher.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.service.ReportDispatchRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportDispatcherController {
	private final Logger logger = Logger.getLogger(getClass());
	private ReportDispatchRecordService reportDispatchRecordService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayHomePage(Model model) {
		model.addAttribute("reportDispatchRecords", reportDispatchRecordService.listReportDispatchRecord());
		
		return "home";
	}
	
	@RequestMapping(value="/record/new", method=RequestMethod.GET)
	public String displayReportDispatchRecordForm(Model model) {
		model.addAttribute("record", new ReportDispatchRecord());
		
		return "newReportDispatchRecord";
	}
	
	@RequestMapping(value="/record/new", method=RequestMethod.POST)
	public String submitReportDispatchRecordForm(@Valid ReportDispatchRecord record, BindingResult result, Model model) {
		logger.info("Submitted form values for ReportDispatchRecordForm: " + record);
		if(result.hasErrors()) {
			logger.info(result.getAllErrors());
			model.addAttribute("record", record);
			
			return "newReportDispatchRecord";
		}
		
		reportDispatchRecordService.addReportDispatchRecord(record);
		return "redirect:/rds/home";
	}
	
	@RequestMapping(value="/record/update/{id}", method=RequestMethod.GET)
	public String displayUpdateReportDispatchRecordForm(@PathVariable Long id, Model model) {
		model.addAttribute("record", reportDispatchRecordService.getReportDispatchRecord(id));
		
		return "newReportDispatchRecord";
	}
	
	@RequestMapping(value="/record/update/{id}", method=RequestMethod.POST)
	public String submitUpdateReportDispatchRecordForm(@Valid ReportDispatchRecord record, BindingResult result, Model model) {
		logger.info("Submitted form values for ReportDispatchRecordForm: " + record);
		if(result.hasErrors()) {
			model.addAttribute("record", record);
			
			return "newReportDispatchRecord";
		}
		
		reportDispatchRecordService.updateReportDispatchRecord(record);
		return "redirect:/rds/home";
	}
	
	@RequestMapping(value = "/record/delete/{id}", method = RequestMethod.GET)
	public String removeComplaintForm(@PathVariable Long id) {
		ReportDispatchRecord record = reportDispatchRecordService.getReportDispatchRecord(id);
		reportDispatchRecordService.removeReportDispatchRecord(record);
		
		return "redirect:/rds/home";
	}

	/**
	 * @return the service
	 */
	public ReportDispatchRecordService getService() {
		return reportDispatchRecordService;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ReportDispatchRecordService service) {
		this.reportDispatchRecordService = service;
	}
}