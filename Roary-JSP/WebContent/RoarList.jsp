<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Roar List</title>
	<link href="roar.css" rel="stylesheet" type="text/css" />
</head>
<body>	
	<!-- Bibliothek und Bean festlegen  -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:useBean id="roarHistory" class="de.ulm.uni.vs.avid.roary.RoarHistory" scope="session"/>
	
	<!-- Usernamen in der Session speichern, wenn er als Parameter übergeben wurde -->
	<c:if test="${!empty param.username}">
		<% request.getSession().setAttribute("username", request.getParameter("username")); %>
    </c:if>
    <!-- Wenn der Parameter leer ist und das Attribut im SessionScope auch, gehe zurück -->
	<c:if test="${empty param.username and empty sessionScope.username}">
		<% response.sendRedirect("index.jsp"); %>
    </c:if>
    
    <h1>Willkommen auf der RoarListe (${sessionScope.username})</h1>
    <a href="RoaryEdit.jsp">Neuen Roar erstellen</a>
    <hr>
    <!-- Alle Roars der Session anzeigen -->	
	<c:forEach var="roar" items="${sessionScope.roarHistory.getRoars()}">
		<div class="roar">
			<div class="header">
				<div class="author">${roar.getAuthor()}</div>
				<div class="time">${roar.getCreationTime()}</div>
			</div>
			<div class="body">
				${roar.getText()}
			</div>
			<div class="footer">
				<a href="RoaryEdit.jsp?roarID=${roar.getId()}">Edit</a>
			</div>
		</div>
	</c:forEach>
	
</body>
</html>