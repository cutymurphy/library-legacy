<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Редактирование книги</title>
</head>
<body>

<form action="<c:url value='/books/${book.id}'/>" method="post">
    <input type="hidden" name="_method" value="PATCH"/>

    <label for="title">Введите название: </label>
    <input type="text" name="title" id="title" value="${book.title}"/>
    <br/>

    <label for="author">Введите автора: </label>
    <input type="text" name="author" id="author" value="${book.author}"/>
    <br/>

    <label for="year">Введите год: </label>
    <input type="text" name="year" id="year" value="${book.year}"/>
    <br/>

    <input type="submit" value="Редактировать!"/>
</form>

</body>
</html>
