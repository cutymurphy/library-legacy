<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Книга</title>
</head>
<body>

<p><strong>Название:</strong> <c:out value="${book.title}"/></p>
<p><strong>Автор:</strong> <c:out value="${book.author}"/></p>
<p><strong>Год:</strong> <c:out value="${book.year}"/></p>

<c:choose>
  <c:when test="${owner != null}">
    <p><strong>Книга у:</strong> <c:out value="${owner.fullName}"/></p>
    <form action="<c:url value='/books/${book.id}'/>" method="post">
      <input type="hidden" name="_method" value="RETURN"/>
      <input type="submit" value="Вернуть книгу"/>
    </form>
  </c:when>
  <c:otherwise>
    <p>Книга свободна</p>
    <form action="<c:url value='/books/${book.id}'/>" method="post">
      <input type="hidden" name="_method" value="ASSIGN"/>
      <select name="personId">
        <c:forEach var="person" items="${people}">
          <option value="${person.id}"><c:out value="${person.fullName}"/></option>
        </c:forEach>
      </select>
      <input type="submit" value="Выдать книгу"/>
    </form>
  </c:otherwise>
</c:choose>

<hr/>

<a href="<c:url value='/books/${book.id}/edit'/>">Редактировать книгу</a>

<hr/>

<form action="<c:url value='/books/${book.id}'/>" method="post">
  <input type="hidden" name="_method" value="DELETE"/>
  <input type="submit" value="Удалить книгу"/>
</form>

<hr/>
<a href="${pageContext.request.contextPath}/books">Вернуться к списку книг</a>

</body>
</html>
