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
    <h3>Status Report of Restaurant</h3>
    ${restaurant}<br>
    <table>
        <c:forEach var="table" items="${restaurant.getTables()}">
            <tr><td align="right">
                <form action="${pageContext.servletContext.contextPath}/restaurant/tables/remove" method="post">
                    <b>Table:</b> ${table}
                    <input type="hidden" name="number" value="${table.number}"/>
                    <input type="submit" value="Remove table" />
                </form>
            </td><td>
                <form action="${pageContext.servletContext.contextPath}/restaurant/tables/unbook" method="post">
                    <input type="hidden" name="number" value="${table.number}"/>
                    <input type="submit" value="Unbook table" />
                </form>
            </td></tr>
        </c:forEach>
    </table>
    <br>
    <c:forEach var="visitor" items="${restaurant.getVisitors()}">
        <form action="${pageContext.servletContext.contextPath}/restaurant/visitors/remove" method="post">
            <table>
                <tr><td>
                    ${visitor} (has booked ${restaurant.getBookedTables(visitor).size()} table(s))
                </td><td>
                    <input type="hidden" name="name" value="${visitor.name}"/>
                    <input type="submit" value="Remove visitor" />
                </td><tr>
            </table>
        </form>
    </c:forEach>

    Tables Available: ${restaurant.getFreeTables().size()} <br>

    <h3>--- End of Status Report ---</h3>

    <h3> Actions </h3>
    <table>
        <tr><th>Add table</th><th>Add visitor</th><th>Book table</th><tr>
        <tr><td>
            <form action="${pageContext.servletContext.contextPath}/restaurant/tables/add" method="post">
                Number: <input type="text" name="number" /> <br>
                Description: <input type="text" name="desc" /> <br>
                <input type="submit" value="Add table" />
            </form>
        </td><td>
            <form action="${pageContext.servletContext.contextPath}/restaurant/visitors/add" method="post">
                Visitor name: <input type="text" name="name" /> <br>
                <input type="submit" value="Add visitor" />
            </form>
        </td><td>
            <form action="${pageContext.servletContext.contextPath}/restaurant/tables/book" method="post">
                Table number: <input type="text" name="number" /> <br>
                Visitor name: <input type="text" name="visitor" /> <br>
                <input type="submit" value="Book table" />
            </form>
        </td><tr>
    </table>
</body>
</html>