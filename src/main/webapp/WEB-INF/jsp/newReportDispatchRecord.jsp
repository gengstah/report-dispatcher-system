<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/headers.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Report Dispatch Record</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/${ theme }/jquery.ui.theme.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/${ theme }/jquery-ui.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.custom.min.js" />"></script>
<script type="text/javascript" charset="utf-8">
	var attachmentTextboxCounter = 0;
	var emailTextboxCounter = 0;

	$(document).ready(function() {
		$("input#submitButton").button().click(function() {
			$('form#record').submit();
		});
		
		$("input#deleteButton").button().click(function() {
			$( "#dialog-confirm-delete" ).dialog( "open" );
		});
		
		$( "#dialog-confirm-delete" ).dialog({
			autoOpen: false,
			resizable: false,
			height:200,
			modal: true,
			buttons: {
				"Delete record": function() {
					window.location = '<c:url value="/rds/record/delete/${ record.reportDispatchRecordId }" />';
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		$("input#cancelButton").button().click(function() {
			window.location = '<c:url value="/rds/home" />';
		});
		
		$("input#addMoreAttachment").button().click(function() {
			var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'attachmentTextboxDiv' + ++attachmentTextboxCounter);
			
			newTextBoxDiv.after().html('<input type="text" name="attachments[' + (attachmentTextboxCounter - 1) + ']" width="100" />');
			
			newTextBoxDiv.appendTo("#attachmentsDiv");
		});
		
		$("input#removeAttachment").button().click(function() {
			if(attachmentTextboxCounter>1) {
				$('#attachmentTextboxDiv' + attachmentTextboxCounter--).remove();
			}
		});
		
		$("input#addMoreEmail").button().click(function() {
			var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'emailTextboxDiv' + ++emailTextboxCounter);
			
			newTextBoxDiv.after().html('<input type="text" name="emails[' + (emailTextboxCounter - 1) + ']" width="100" />');
			
			newTextBoxDiv.appendTo("#emailsDiv");
		});
		
		$("input#removeEmail").button().click(function() {
			if(emailTextboxCounter>1) {
				$('#emailTextboxDiv' + emailTextboxCounter--).remove();
			}
		});
	});
</script>
</head>
<body class="standard">
	<div align="center">
		<table border="1" class="altrowstable">
			<tr>
				<td>
					<form:form method="POST" commandName="record">
						<form:hidden path="reportDispatchRecordId" autocomplete="off" maxlength="15" />
						<table class="altrowstable">
							<tbody>
								<tr>
									<td colspan="4" align="left">
										<form:errors path = "*" cssClass="error" />
									</td>
								</tr>
								<tr>
									<td align="left" valign="top">Name*:</td>
									<td align="left" valign="top"><form:input path="name" autocomplete="off" maxlength="50" /></td>
									<td align="left" valign="top">Description:</td>
									<td align="left" valign="top"><form:textarea path="description" cols="50" rows="10" /></td>
								</tr>
								<tr>
									<td align="left" valign="top">Subject*:</td>
									<td align="left" valign="top"><form:input path="subject" autocomplete="off" maxlength="50" /></td>
									<td align="left" valign="top">Message:</td>
									<td align="left" valign="top"><form:textarea path="message" cols="50" rows="10" /></td>
								</tr>
								<tr>
									<td align="left" valign="top">Schedule*:</td>
									<td align="left" colspan="3" valign="top"><form:input path="cronSchedule" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left" valign="top">Attachments:</td>
									<td align="left" valign="top">										
										<div id="attachmentsDiv">
											<c:if test="${ not empty record.attachments }">
												<c:forEach var="attachment" items="${ record.attachments }" varStatus="status">
													<div id="attachmentTextboxDiv${ (status.index + 1) }">
														<input name="attachments[${ status.index }]" value="${ attachment }" type="text" width="100" />
														<script type="text/javascript" charset="utf-8">attachmentTextboxCounter++;</script>
													</div>
												</c:forEach>
											</c:if>
											
											<c:if test="${ empty record.attachments }">
												<div id="attachmentTextboxDiv1">
													<input name="attachments[0]" type="text" width="100" />
													<script type="text/javascript" charset="utf-8">attachmentTextboxCounter++;</script>
												</div>
											</c:if>
											
										</div>
										
										<br />
										<input id="addMoreAttachment" type="button" value="+" />
										<input id="removeAttachment" type="button" value="-" />
									</td>
									<td align="left" valign="top">Emails:</td>
									<td align="left" valign="top">
										<div id="emailsDiv">
											<c:if test="${ not empty record.emails }">
												<c:forEach var="email" items="${ record.emails }" varStatus="status">
													<div id="emailTextboxDiv${ (status.index + 1) }">														
														<input name="emails[${ status.index }]" value="${ email }" type="text" width="100" />
														<script type="text/javascript" charset="utf-8">emailTextboxCounter++;</script>
													</div>
												</c:forEach>
											</c:if>
											
											<c:if test="${ empty record.emails }">
												<div id="emailTextboxDiv1">
													<input name="emails[0]" type="text" width="100" />
													<script type="text/javascript" charset="utf-8">emailTextboxCounter++;</script>
												</div>
											</c:if>
										</div>
										
										<br />
										<input id="addMoreEmail" type="button" value="+" />
										<input id="removeEmail" type="button" value="-" />
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td align="right">
										<input id="submitButton" type="button" value="Submit">
										<c:if test="${ not record.reportDispatchRecordId == null }">
											<input id="deleteButton" type="button" value="Delete">
										</c:if>
										<input id="cancelButton" type="button" value="Cancel">
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>
					<div id="dialog-confirm-delete" title="Delete this record?">
						<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>This record will be permanently deleted and will no longer trigger its job. Are you sure?</p>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>