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
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:useBean id="roarHistory" class="de.ulm.uni.vs.avid.roary.RoarHistory" scope="session"/>
	<c:forEach var="roar" items="${sessionScope.roarHistory.getRoars()}">
		<div class="roar">
			<div class="header">
				<div class="author">${roar.getAuthor()}</div>
				<div class="time">${roar.getCreationTime()}</div>
			</div>
			<div class="body">
				${roar.getText()}
			</div>
		</div>
	</c:forEach>
	
</body>
</html>