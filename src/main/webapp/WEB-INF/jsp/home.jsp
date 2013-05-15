<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include/headers.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Home - Report Dispatcher System</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/${ theme }/jquery.ui.theme.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/${ theme }/jquery-ui.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/demo_page.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/demo_table_jui.css" />">

<script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.dataTables.js" />"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("input#addNewRecordButton").button().click(function() {
			window.location = '<c:url value="/rds/record/new" />';
		});
		
		oTable = $('#reportDispatchRecordTable').dataTable( {
			"bPaginate": true,
			"bJQueryUI": true,
	        "sPaginationType": "full_numbers",
	        "bFilter": true
		});
	});
</script>
</head>
<body id="dt_example" class="standard">
	<div id="container">
		<div class="demo_jui">
			<table cellpadding="0" cellspacing="0" border="0" class="display" id="reportDispatchRecordTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Schedule</th>
					</tr>
				</thead>
				<tbody>
				<tr class="gradeX">
					<td>1</td>
					<td>Sample</td>
					<td>Sample Description</td>
					<td>Every Monday @ 1 PM</td>
				</tr>
				<c:forEach var="reportDispatchRecord" items="${ reportDispatchRecords }" varStatus="status">
					<tr onMouseOver="this.className='highlight'" onMouseOut="this.className=''" onclick="window.location = '<c:url value='/rds/record/update/${reportDispatchRecord.reportDispatchRecordId}' />';">
						<td><c:out value="${ reportDispatchRecord.reportDispatchRecordId }" /></td>
						<td><c:out value="${ reportDispatchRecord.name }" /></td>
						<td><c:out value="${ reportDispatchRecord.description }" /></td>
						<td><c:out value="${ reportDispatchRecord.cronSchedule }" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">&nbsp;</td>
					<td align="right"><input id="addNewRecordButton" type="button" value="Add Record" /></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>