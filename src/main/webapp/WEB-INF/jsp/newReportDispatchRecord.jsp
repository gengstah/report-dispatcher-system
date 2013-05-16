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
		
		$("input#resetButton").button().click(function() {
			$('form#record').reset();
		});
		
		$("input#addMoreAttachment").button().click(function() {
			var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'attachmentTextboxDiv' + attachmentTextboxCounter);
			
			newTextBoxDiv.after().html('<input type="text" name="attachments[' + (attachmentTextboxCounter - 1) + ']" autocomplete="off" maxlength="50" /><input type="button" id="removeAttachmentButton' + attachmentTextboxCounter + '" value="X" />');
			
			newTextBoxDiv.appendTo("#attachmentsDiv");
			
			$('input#removeAttachmentButton' + attachmentTextboxCounter).click(function() {
				if(attachmentTextboxCounter>2) {
					$('#attachmentTextboxDiv' + attachmentTextboxCounter--).remove();
				}
			});
			
			attachmentTextboxCounter++;
		});
		
		$("input#addMoreEmail").button().click(function() {
			var newTextBoxDiv = $(document.createElement('div'))
				.attr("id", 'emailTextboxDiv' + emailTextboxCounter);
			
			newTextBoxDiv.after().html('<input type="text" name="emails[' + (emailTextboxCounter - 1) + ']" autocomplete="off" maxlength="50" /><input type="button" id="removeEmailButton' + emailTextboxCounter + '" value="X" />');
			
			newTextBoxDiv.appendTo("#emailsDiv");
			
			$('input#removeEmailButton' + emailTextboxCounter).click(function() {
				if(emailTextboxCounter>2) {
					$('#emailTextboxDiv' + emailTextboxCounter--).remove();
				}
			});
			
			emailTextboxCounter++;
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
										<form:errors path = "*" />
									</td>
								</tr>
								<tr>
									<td align="left">Name*:</td>
									<td align="left"><form:input path="name" autocomplete="off" maxlength="50" /></td>
									<td align="left">Description:</td>
									<td align="left"><form:input path="description" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left">Subject*:</td>
									<td align="left"><form:input path="subject" autocomplete="off" maxlength="50" /></td>
									<td align="left">Message:</td>
									<td align="left"><form:input path="message" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left">Schedule*:</td>
									<td align="left" colspan="3"><form:input path="cronSchedule" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left" valign="top">Attachments:</td>
									<td align="left">										
										<div id="attachmentsDiv">
											<div id="attachmentTextboxDiv1">
												<c:if test="${ not empty attachments }">
													<c:forEach var="attachment" items="${ attachments }" varStatus="status">
														<form:input path="attachments[${ status.index }]" autocomplete="off" maxlength="50" />
														<script type="text/javascript" charset="utf-8">attachmentTextboxCounter++;</script>
													</c:forEach>
												</c:if>
												
												<c:if test="${ empty attachments }">
													<form:input path="attachments[0]" autocomplete="off" maxlength="50" />
													<script type="text/javascript" charset="utf-8">attachmentTextboxCounter++;</script>
												</c:if>
											</div>
										</div>
										
										<br />
										<input id="addMoreAttachment" type="button" value="Add attachment" />
									</td>
									<td align="left" valign="top">Emails:</td>
									<td align="left">
										<div id="emailsDiv">
											<div id="emailTextboxDiv1">
												<c:if test="${ not empty emails }">
													<c:forEach var="email" items="${ emails }" varStatus="status">
														<form:input id="email${ status.count }" path="emails[${ status.index }]" autocomplete="off" maxlength="50" />
														<script type="text/javascript" charset="utf-8">emailTextboxCounter++;</script>
													</c:forEach>
												</c:if>
												
												<c:if test="${ empty emails }">
													<form:input path="emails[0]" autocomplete="off" maxlength="50" />
													<script type="text/javascript" charset="utf-8">emailTextboxCounter++;</script>
												</c:if>
											</div>
										</div>
										
										<br />
										<input id="addMoreEmail" type="button" value="Add email" />
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td align="right">
										<input id="submitButton" type="button" value="Submit">
										<input id="resetButton" type="reset" value="Reset">
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>