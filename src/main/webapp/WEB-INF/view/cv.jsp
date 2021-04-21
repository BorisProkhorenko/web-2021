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

<c:set var="mainClass" value="container"/>
<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="applicant-container"/>
</c:if>

<main class="${mainClass}">

    <c:import url="/controller?command=getUser&id=${sessionScope.id}"/>

    <c:set var="male" value="${Gender.MALE}"/>
    <c:set var="female" value="${Gender.FEMALE}"/>

    <div class="general-cv">
        <h1>${user.name}</h1>
        <strong><fmt:message key="label.age"/>: ${user.age}</strong>
        <strong><fmt:message key="label.gender"/>:
            <c:if test="${user.gender == male}">
                <fmt:message key="label.male"/>
            </c:if>
            <c:if test="${user.gender == female}">
                <fmt:message key="label.female"/>
            </c:if>
        </strong>
    </div>
    <div class="photo-contacts-cv">
        <div class="photo">

            <img src="${pageContext.request.contextPath}/controller?command=image&id=${sessionScope.id}" height="400" width="400">
            <jsp:include page="fragments/uploadFile.jsp"/>
        </div>
        <div class="contacts">
            <h3><fmt:message key="label.contacts"/>:</h3>
            <p>${user.contacts}</p>
        </div>
    </div>
    <div>
        <h3><fmt:message key="label.education"/>:</h3>
        <p>${user.education}</p>
        <h3><fmt:message key="label.experience"/>:</h3>
        <p>${user.experience}</p>
        <h3><fmt:message key="label.skills"/>:</h3>
        <p>${user.skills}</p>
    </div>
<div class="single-button">
    <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=editCv" method="post">
        <button>
            <fmt:message key="label.edit"/>
        </button>
    </form>
</div>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>

