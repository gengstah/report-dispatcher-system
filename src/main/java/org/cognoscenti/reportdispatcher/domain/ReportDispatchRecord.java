package org.cognoscenti.reportdispatcher.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private Long reportDispatchRecordId;
	
	@NotNull
	@Size(min=3, max=20)
	@Column(name="NAME")	
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ElementCollection
	@CollectionTable(name="ATTACHMENT")
	private List<String> attachments;
	
	@Size(min=1)
	@ElementCollection
	@CollectionTable(name="EMAIL")
	private List<String> emails;
	
	@NotNull
	@Size(min=10, max=50)
	@Column(name="CRON_SCHEDULE")
	private String cronSchedule;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="MESSAGE")
	private String message;
	
	/**
	 * Default constructor	 
	 */
	public ReportDispatchRecord() { }

	/**
	 * @return the reportDispatchRecordId
	 */
	public Long getReportDispatchRecordId() {
		return reportDispatchRecordId;
	}

	/**
	 * @param reportDispatchRecordId the reportDispatchRecordId to set
	 */
	public void setReportDispatchRecordId(Long reportDispatchRecordId) {
		this.reportDispatchRecordId = reportDispatchRecordId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the attachments
	 */
	public List<String> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<String> attachments) {
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

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportDispatchRecord [reportDispatchRecordId="
				+ reportDispatchRecordId + ", name=" + name + ", description="
				+ description + ", attachments=" + attachments + ", emails="
				+ emails + ", cronSchedule=" + cronSchedule + ", subject="
				+ subject + ", message=" + message + "]";
	}
}