<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Roar bearbeiten</title>
</head>
<body>
	<!-- Bibliothek und Bean festlegen  -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:useBean id="roarHistoryUpdate" class="de.ulm.uni.vs.avid.roary.RoarHistoryUpdate" scope="session"/>
	
	<form method="get" action="RoarHistoryUpdate">
		<c:choose>
			<c:when test="${empty param.roarID}"> 
				<p>Geben sie ein was sie brüllen möchten:</p>
			</c:when>
			<c:otherwise>
				<p>Sie können den Text nun ändern:</p>
			</c:otherwise>
		</c:choose>
		
		<textarea name="roarText">${ sessionScope.roarHistory.getRoar(param.roarID).getText() }</textarea><br>
		<input name="roarId" value="${param.roarID}" type="hidden"/>
		<input value="Brüll" type="submit"/>
	</form>
</body>
</html>