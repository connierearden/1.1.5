<%--
  Created by IntelliJ IDEA.
  User: romkonst
  Date: 08.06.2020
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete of user</title>
</head>
<body>
<form method="post" action="/admin/delete/" style="margin: 40px;">
    <input type="text" name="id" placeholder="User ID"><br>
    <button type="submit">Delete</button>
</form>

</body>
</html>
