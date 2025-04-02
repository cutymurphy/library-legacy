<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Редактирование человека</title>
</head>
<body>

<form action="<c:url value='/people/${person.id}'/>" method="post">
    <input type="hidden" name="_method" value="PATCH"/>

    <label for="fullName">Введите ФИО: </label>
    <input type="text" name="fullName" id="fullName" value="${person.fullName}"/>
    <br/>

    <label for="birthYear">Введите год рождения: </label>
    <input type="text" name="birthYear" id="birthYear" value="${person.birthYear}"/>
    <br/>

    <input type="submit" value="Редактировать!"/>
</form>

</body>
</html>
