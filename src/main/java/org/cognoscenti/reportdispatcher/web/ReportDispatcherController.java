package org.cognoscenti.reportdispatcher.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;
import org.cognoscenti.reportdispatcher.service.ReportDispatchRecordService;
import org.cognoscenti.reportdispatcher.service.SchedulerService;
import org.cognoscenti.reportdispatcher.validator.ReportDispatchRecordValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportDispatcherController {
	private final Logger logger = Logger.getLogger(getClass());
	private ReportDispatchRecordService reportDispatchRecordService;
	private SchedulerService schedulerService;
	private ReportDispatchRecordValidator reportDispatchRecordValidator;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String displayHomePage(Model model) {
		List<ReportDispatchRecord> reportDispatchRecords = reportDispatchRecordService.listReportDispatchRecord();
		setTriggerStatus(reportDispatchRecords);
		
		model.addAttribute("reportDispatchRecords", reportDispatchRecords);
		
		return "home";
	}

	private void setTriggerStatus(
			List<ReportDispatchRecord> reportDispatchRecords) {
		for(ReportDispatchRecord reportDispatchRecord : reportDispatchRecords) {
			reportDispatchRecord.setActive(schedulerService.isActive(reportDispatchRecord));
		}
	}
	
	@RequestMapping(value="/record/new", method=RequestMethod.GET)
	public String displayReportDispatchRecordForm(Model model) {
		model.addAttribute("record", new ReportDispatchRecord());
		
		return "newReportDispatchRecord";
	}
	
	@RequestMapping(value="/record/new", method=RequestMethod.POST)
	public String submitReportDispatchRecordForm(@ModelAttribute("record") @Valid ReportDispatchRecord record, BindingResult result, Model model) {
		logger.info("Submitted form values for ReportDispatchRecordForm: " + record);
		if(result.hasErrors()) {
			return goBackToForm(record, result, model);
		} else {
			reportDispatchRecordValidator.validate(record, result);
			
			if(result.hasErrors()) {
				return goBackToForm(record, result, model);
			}
		}
		
		reportDispatchRecordService.addReportDispatchRecord(record);
		schedulerService.addSchedule(record);
		
		return "redirect:/rds/home";
	}
	
	@RequestMapping(value="/record/update/{id}", method=RequestMethod.GET)
	public String displayUpdateReportDispatchRecordForm(@PathVariable Long id, Model model) {
		ReportDispatchRecord reportDispatchRecord = reportDispatchRecordService.getReportDispatchRecord(id);
		model.addAttribute("record", reportDispatchRecord);
		
		return "newReportDispatchRecord";
	}
	
	@RequestMapping(value="/record/update/{id}", method=RequestMethod.POST)
	public String submitUpdateReportDispatchRecordForm(@ModelAttribute("record") @Valid ReportDispatchRecord record, BindingResult result, Model model) {
		logger.info("Submitted form values for ReportDispatchRecordForm: " + record);		
		if(result.hasErrors()) {
			return goBackToForm(record, result, model);
		} else {
			reportDispatchRecordValidator.validate(record, result);
			
			if(result.hasErrors()) {
				return goBackToForm(record, result, model);
			}
		}
		
		reportDispatchRecordService.updateReportDispatchRecord(record);
		schedulerService.updateSchedule(record);
		
		return "redirect:/rds/home";
	}

	private String goBackToForm(ReportDispatchRecord record,
			BindingResult result, Model model) {
		logger.info(result.getAllErrors());
		model.addAttribute("record", record);
		
		return "newReportDispatchRecord";
	}
	
	@RequestMapping(value = "/record/delete/{id}", method = RequestMethod.GET)
	public String removeComplaintForm(@PathVariable Long id) {
		reportDispatchRecordService.removeReportDispatchRecord(id);
		schedulerService.removeSchedule(id);
		
		return "redirect:/rds/home";
	}
	
	@RequestMapping(value = "/record/start/{id}", method = RequestMethod.GET)
	public String startScheduler(@PathVariable Long id) {
		ReportDispatchRecord record = reportDispatchRecordService.getReportDispatchRecord(id);
		schedulerService.start(record);
		
		return "redirect:/rds/home";
	}
	
	@RequestMapping(value = "/record/stop/{id}", method = RequestMethod.GET)
	public String stopScheduler(@PathVariable Long id) {
		ReportDispatchRecord record = reportDispatchRecordService.getReportDispatchRecord(id);
		schedulerService.stop(record);
		
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

	/**
	 * @return the schedulerService
	 */
	public SchedulerService getSchedulerService() {
		return schedulerService;
	}

	/**
	 * @param schedulerService the schedulerService to set
	 */
	public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	/**
	 * @return the reportDispatchRecordValidator
	 */
	public ReportDispatchRecordValidator getReportDispatchRecordValidator() {
		return reportDispatchRecordValidator;
	}

	/**
	 * @param reportDispatchRecordValidator the reportDispatchRecordValidator to set
	 */
	public void setReportDispatchRecordValidator(
			ReportDispatchRecordValidator reportDispatchRecordValidator) {
		this.reportDispatchRecordValidator = reportDispatchRecordValidator;
	}
}