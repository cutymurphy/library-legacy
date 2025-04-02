<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Человек</title>
</head>
<body>

<p><strong>Имя:</strong> <c:out value="${person.fullName}"/></p>
<p><strong>Год рождения:</strong> <c:out value="${person.birthYear}"/></p>

<c:if test="${empty books}">
    <p>Человек пока не взял ни одной книги</p>
</c:if>

<c:if test="${not empty books}">
    <hr/>
    <p><strong>Книги:</strong></p>
    <c:forEach var="book" items="${books}">
        <p>
                <c:out value="${book.title}, ${book.author}, ${book.year}"/>
        <form action="<c:url value='/books/${book.id}'/>" method="post" style="display:inline;">
            <input type="hidden" name="_method" value="RETURN"/>
            <input type="submit" value="Вернуть книгу"/>
        </form>
        </p>
    </c:forEach>
</c:if>

<hr/>
<a href="<c:url value='/people/${person.id}/edit'/>">Редактировать</a>
<form action="<c:url value='/people/${person.id}'/>" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="submit" value="Удалить"/>
</form>
<hr/>
<a href="${pageContext.request.contextPath}/people">Вернуться ко всем людям</a>

</body>
</html>
