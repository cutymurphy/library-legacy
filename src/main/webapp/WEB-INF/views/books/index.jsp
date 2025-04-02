<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Все книги</title>
</head>
<body>

<c:forEach var="book" items="${books}">
    <a href="<c:url value='/books/${book.id}'/>">
        <c:out value="${book.title}, ${book.author}, ${book.year}"/>
    </a>
    <br/>
</c:forEach>

<hr/>
<a href="${pageContext.request.contextPath}/books/new">Добавить книгу</a>
<hr/>
<a href="${pageContext.request.contextPath}/people">Перейти к людям</a>

</body>
</html>
