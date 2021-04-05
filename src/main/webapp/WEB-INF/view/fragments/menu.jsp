<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<nav class="menu">
    <a href="${pageContext.request.contextPath}/controller?command=mainPage">
        <fmt:message key="label.vacancies"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=cv">
        <fmt:message key="label.cv"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=applications">
        <fmt:message key="label.applications"/>
    </a>
</nav>
</html>