<%--
  Created by IntelliJ IDEA.
  User: romkonst
  Date: 28.05.2020
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<form action="/login" method="POST">
    <tr>
        <td>
            <input type="text" name="name" placeholder="name">
        </td>
        <td>
            <input type="text" name="pass" placeholder="pass">
        </td>
        <button type="submit">Log in</button>
    </tr>
</form>
</body>
</html>
