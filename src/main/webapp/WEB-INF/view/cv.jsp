<%@ page import="com.epam.web.enums.Gender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<nav class="menu">
    <jsp:include page="fragments/menu.jsp"/>
</nav>

<main class="container">
    <jsp:useBean id="UserService" scope="request" class="com.epam.web.service.UserService"
                 type="com.epam.web.service.UserService"/>

    <c:set var="vacancy" value="${UserService.getById(sessionScope.id)}"/>
    <c:set var="male" value="${Gender.MALE}"/>
    <c:set var="female" value="${Gender.FEMALE}"/>

    <div class="general-cv">
        <h1>${vacancy.name}</h1>
        <strong><fmt:message key="label.age"/>: ${vacancy.age}</strong>
        <strong><fmt:message key="label.gender"/>:
            <c:if test="${vacancy.gender == male}">
                <fmt:message key="label.male"/>
            </c:if>
            <c:if test="${vacancy.gender == female}">
                <fmt:message key="label.female"/>
            </c:if>
        </strong>
    </div>
    <div class="photo-contacts-cv">
        <img src="${pageContext.servletContext.contextPath}/icons/avatar.png" height="400" width="400">
        <div>
            <h3><fmt:message key="label.contacts"/>:</h3>
            <p>${vacancy.contacts}</p>
        </div>
    </div>
    <div>
        <h3><fmt:message key="label.education"/>:</h3>
        <p>${vacancy.education}</p>
        <h3><fmt:message key="label.experience"/>:</h3>
        <p>${vacancy.experience}</p>
        <h3><fmt:message key="label.skills"/>:</h3>
        <p>${vacancy.skills}</p>
    </div>

    <form class="end-page-button" action="#" method="POST">
        <button>
            <fmt:message key="label.edit"/>
        </button>
    </form>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
<p>"${sessionScope}"</p>
</body>
</html>

