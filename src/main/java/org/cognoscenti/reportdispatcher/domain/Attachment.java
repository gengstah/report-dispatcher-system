package org.cognoscenti.reportdispatcher.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents an email attachment
 * 
 * @author Geng
 * @version 1.0
 */
@Entity
@Table(name="ATTACHMENT")
public class Attachment implements Serializable {
	private static final long serialVersionUID = 8048071705137299503L;
	
	@Id
	@Column(name="ATTACHMENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attachmentId;
	
	@Column(name="ATTACHMENT_LOCATION")
	private String attachmentLocation;
	
	@ManyToOne
	@JoinColumn(name="REPORT_DISPATCH_RECORD_ID")
	private ReportDispatchRecord reportDispatchRecord;
	
	/**
	 * Default constructor
	 */
	public Attachment(String attachmentLocation) { setAttachmentLocation(attachmentLocation); }

	/**
	 * @return the attachmentId
	 */
	public int getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the attachmentLocation
	 */
	public String getAttachmentLocation() {
		return attachmentLocation;
	}

	/**
	 * @param attachmentLocation the attachmentLocation to set
	 */
	public void setAttachmentLocation(String attachmentLocation) {
		this.attachmentLocation = attachmentLocation;
	}

	/**
	 * @return the reportDispatchRecord
	 */
	public ReportDispatchRecord getReportDispatchRecord() {
		return reportDispatchRecord;
	}

	/**
	 * @param reportDispatchRecord the reportDispatchRecord to set
	 */
	public void setReportDispatchRecord(ReportDispatchRecord reportDispatchRecord) {
		this.reportDispatchRecord = reportDispatchRecord;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId
				+ ", attachmentLocation=" + attachmentLocation + "]";
	}
}