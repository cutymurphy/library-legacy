<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Все люди</title>
</head>
<body>

<c:forEach var="person" items="${people}">
    <a href="<c:url value='/people/${person.id}'/>">
        <c:out value="${person.fullName}, ${person.birthYear}"/>
    </a>
    <br/>
</c:forEach>

<hr/>
<a href="${pageContext.request.contextPath}/people/new">Добавить человека</a>
<hr/>
<a href="${pageContext.request.contextPath}/books">Перейти к книгам</a>

</body>
</html>
