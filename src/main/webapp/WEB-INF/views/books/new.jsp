<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Создание книги</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/books" method="post">
  <label for="title">Введите название книги: </label>
  <input type="text" name="title" id="title"/>
  <br/>

  <label for="author">Введите автора: </label>
  <input type="text" name="author" id="author"/>
  <br/>

  <label for="year">Введите год: </label>
  <input type="text" name="year" id="year"/>
  <br/>

  <input type="submit" value="Создать!"/>
</form>

</body>
</html>
