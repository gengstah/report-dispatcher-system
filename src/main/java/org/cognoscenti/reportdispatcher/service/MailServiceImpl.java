package org.cognoscenti.reportdispatcher.service;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.cognoscenti.reportdispatcher.domain.ReportDispatchRecord;

/**
 * Default implementation of {@link org.cognoscenti.reportdispatcher.service.MailService}
 * 
 * @author Geng
 * @version 1.0
 * @see org.cognoscenti.reportdispatcher.service.MailService
 */
public class MailServiceImpl implements MailService {
	private final Logger logger = Logger.getLogger(getClass());
	private String username;
	private String password;
	private Properties props;
	
	public MailServiceImpl() { }
	
	@Override
	public void sendMail(ReportDispatchRecord reportDispatchRecord) {
		logger.info("Creating mail session");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		try {
			logger.info("Creating mail message");
			Message message = new MimeMessage(session);
			
			logger.info("Setting the from email");
			message.setFrom(new InternetAddress("gerardpdelasarmas@gmail.com"));
			
			logger.info("Getting the list of emails");
			List<String> emails = reportDispatchRecord.getEmails();
			
			for(String email : emails) {
				logger.info("Adding " + email + " as recipient");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}
			
			logger.info("Setting the subject for the mail");
			message.setSubject(reportDispatchRecord.getSubject());
			
			logger.info("Setting the body for the mail");			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(reportDispatchRecord.getMessage());
			
			Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    
		    logger.info("Getting mail attachments");
		    List<String> attachments = reportDispatchRecord.getAttachments();
		    		    
		    for(String attachment : attachments) {
		    	logger.info("Replacing any backslashes");
		    	attachment = replaceBackslashes(attachment);
		    	logger.info("Adding " + attachment + " as an attachment");
		    	messageBodyPart = new MimeBodyPart();
			    DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(getFilename(attachment));
				multipart.addBodyPart(messageBodyPart);
		    }
		    
			message.setContent(multipart);
			logger.info("Done creating mail message");
			
			logger.info("Sending the mail...");
			Transport.send(message);
			logger.info("Done sending the mail");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String replaceBackslashes(String fullPath) {
		return fullPath.replace('\\', '/');
	}
	
	private String getFilename(String fullPath) {
		String[] filenameArray = fullPath.split("/");
		return filenameArray[filenameArray.length-1];
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MailServiceImpl [username=" + username + ", password="
				+ password + ", props=" + props + "]";
	}
}