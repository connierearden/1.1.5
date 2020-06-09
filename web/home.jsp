<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eds2809
  Date: 10.02.2020
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
</head>
<body>
<label>Вы успешно авторизовались</label><br><br><br><br>

<label>Имя: </label><label><c:out value="${sessionScope.user.name}"/></label><br>
<label>Возраст: </label><label><c:out value="${sessionScope.user.age}"/></label><br>
<label>Роль: </label><label><c:out value="${sessionScope.user.role}"/></label><br>
<br>
<br>
<br>

<label>ID сессии: </label><label><c:out value="${pageContext.session.id}"/></label><br>


<c:if test="${fn:containsIgnoreCase(sessionScope.user.role, 'admin')}">
    <br>
    <form>
        <input type="button" value="Admin panel" onClick='location.href="${pageContext.request.contextPath}/admin/"'>
    </form>
</c:if>

<form method="get" action="${pageContext.request.contextPath}/exit/">
    <button type="submit">Log out</button>
</form>


</body>
</html>