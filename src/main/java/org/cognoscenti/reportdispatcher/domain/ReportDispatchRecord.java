package org.cognoscenti.reportdispatcher.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a report dispatch record which contains 
 * a list of {@link org.cognoscenti.reportdispatcher.domain.Attachment}S 
 * which will be sent to a list of <tt>emails</tt> 
 * with a given schedule
 * 
 * @author Geng
 * @version 1.0
 */
@Entity
@Table(name="REPORT_DISPATCH")
public class ReportDispatchRecord {
	@Id
	@Column(name="REPORT_DISPATCH_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reportDispatchRecordId;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="reportDispatchRecord")
	private List<Attachment> attachments;
	
	@ElementCollection
	@CollectionTable(name="EMAIL")
	private List<String> emails;
	
	@Column(name="CRON_SCHEDULE")
	private String cronSchedule;
	
	/**
	 * Default constructor
	 */
	public ReportDispatchRecord() { }

	/**
	 * @return the reportDispatchRecordId
	 */
	public int getReportDispatchRecordId() {
		return reportDispatchRecordId;
	}

	/**
	 * @param reportDispatchRecordId the reportDispatchRecordId to set
	 */
	public void setReportDispatchRecordId(int reportDispatchRecordId) {
		this.reportDispatchRecordId = reportDispatchRecordId;
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	/**
	 * @return the cronSchedule
	 */
	public String getCronSchedule() {
		return cronSchedule;
	}

	/**
	 * @param cronSchedule the cronSchedule to set
	 */
	public void setCronSchedule(String cronSchedule) {
		this.cronSchedule = cronSchedule;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportDispatchRecord [attachments=" + attachments + ", emails="
				+ emails + ", cronSchedule=" + cronSchedule + "]";
	}
}