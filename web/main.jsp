<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Users from BD</title>
</head>
<body>
<form action="/" method="post">
    <tr>
        <td>
            <input type="number" name="id">
        </td>
        <td>
            <input type="text" name="name">
        </td>
        <td>
            <input type="number" name="age">
        </td>
        <td>
            <input type="text" name="pass">
        </td>
        <button type="submit">Add</button>
    </tr>
</form>

<c:if test="${fn:length(users) > 0}">
        <table border="1" cellspacing="0" cellpadding="2">
            <tr>
                <td>ID</td>
                <td>name</td>
                <td>age</td>
                <td>password</td>
            </tr>
         <c:forEach items="${users}" var="user">

             <tr>
                <form method="post" action="/admin" style="margin: 0;">
                    <td>
                        <input type="text" name="id" value="${user.id}" hidden>
                            ${user.id}
                    </td>
                    <td>
                        <input type="text" name="name" value="${user.name}">
                    </td>

                    <td>
                        <input type="number" name="age" value="${user.age}">
                    </td>

                    <td>
                        <input type="text" name="pass" value="${user.password}">
                    </td>

                    <td>
                        <button type="submit">update</button>
                    </td>
                </form>
                <td>
                    <form method="get" action="/admin" style="margin: 0;">
                        <input type="text" name="id" value="${user.id}" hidden>
                        <button type="submit">delete</button>
                    </form>
                </td>
            </tr>
         </c:forEach>
        </table>
</c:if>



</body>
</html>
