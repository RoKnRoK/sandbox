<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HelloWorld page</title>
</head>
<body>
    <h3>List of Tables</h3>
    <c:if test="${empty param.visitorName && not empty param.status}">
        Show tables with status = <b>${param.status}</b><br>
    </c:if>
    <c:if test="${not empty param.visitorName}">
            Show tables for person <b>${param.visitorName}</b><br>
    </c:if>
    <c:forEach var="table" items="${Tables}">
        <b>Table:</b> ${table} <br>
    </c:forEach>

</body>
</html>