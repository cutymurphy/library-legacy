<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление человека</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/people" method="post">
    <label for="fullName">Введите ФИО: </label>
    <input type="text" name="fullName" id="fullName"/>
    <br/>

    <label for="birthYear">Введите год рождения: </label>
    <input type="text" name="birthYear" id="birthYear"/>
    <br/>

    <input type="submit" value="Добавить!"/>
</form>

</body>
</html>
