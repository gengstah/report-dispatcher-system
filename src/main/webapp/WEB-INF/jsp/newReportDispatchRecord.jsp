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
	$(document).ready(function() {
		$("input#submitButton").button().click(function() {
			$('form#record').submit();
		});
		
		$("input#resetButton").button().click(function() {
			$('form#record').reset();
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
									<td align="left">Name:</td>
									<td align="left"><form:input path="name" autocomplete="off" maxlength="50" /></td>
									<td align="left">Description:</td>
									<td align="left"><form:input path="description" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left">Subject:</td>
									<td align="left"><form:input path="subject" autocomplete="off" maxlength="50" /></td>
									<td align="left">Message:</td>
									<td align="left"><form:input path="message" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left">Schedule:</td>
									<td align="left" colspan="3"><form:input path="cronSchedule" autocomplete="off" maxlength="50" /></td>
								</tr>
								<tr>
									<td align="left">Attachments:</td>
									<td align="left">
										<%-- <form:input id="attachment1" path="attachments[0]" autocomplete="off" maxlength="50" />
										<form:input id="attachment2" path="attachments[1]" autocomplete="off" maxlength="50" />
										<form:input id="attachment3" path="attachments[2]" autocomplete="off" maxlength="50" />
										<form:input id="attachment4" path="attachments[3]" autocomplete="off" maxlength="50" />
										<form:input id="attachment5" path="attachments[4]" autocomplete="off" maxlength="50" /> --%>
										<c:if test="${ not empty attachments }">
											<c:forEach var="attachment" items="${ attachments }" varStatus="status">
												<form:input id="attachment${ status.count }" path="attachments[${ status.index }]" autocomplete="off" maxlength="50" />
											</c:forEach>
										</c:if>
										
										<c:if test="${ empty attachments }">
											<form:input id="attachment1" path="attachments[0]" autocomplete="off" maxlength="50" />
										</c:if>
									</td>
									<td align="left">Emails:</td>
									<td align="left">
										<%-- <form:input id="email1" path="emails[0]" autocomplete="off" maxlength="50" />
										<form:input id="email2" path="emails[1]" autocomplete="off" maxlength="50" />
										<form:input id="email3" path="emails[2]" autocomplete="off" maxlength="50" />
										<form:input id="email4" path="emails[3]" autocomplete="off" maxlength="50" />
										<form:input id="email5" path="emails[4]" autocomplete="off" maxlength="50" /> --%>
										<c:if test="${ not empty emails }">
											<c:forEach var="email" items="${ emails }" varStatus="status">
												<form:input id="email${ status.count }" path="emails[${ status.index }]" autocomplete="off" maxlength="50" />
											</c:forEach>
										</c:if>
										
										<c:if test="${ empty emails }">
											<form:input id="email1" path="emails[0]" autocomplete="off" maxlength="50" />
										</c:if>
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