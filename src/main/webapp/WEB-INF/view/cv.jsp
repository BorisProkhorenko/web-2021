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

    <c:set var="applicant" value="${UserService.getById(sessionScope.id)}" scope="request"/>
    <c:set var="male" value="${Gender.MALE}"/>
    <c:set var="female" value="${Gender.FEMALE}"/>

    <div class="general-cv">
        <h1>${applicant.name}</h1>
        <strong><fmt:message key="label.age"/>: ${applicant.age}</strong>
        <strong><fmt:message key="label.gender"/>:
            <c:if test="${applicant.gender == Male}">
                <fmt:message key="label.male"/>
            </c:if>
            <c:if test="${applicant.gender == Female}">
                <fmt:message key="label.female"/>
            </c:if>
        </strong>
    </div>
    <div class="photo-contacts-cv">
        <img src="${applicant.photo}" height="400" width="400">
        <div>
            <h3><fmt:message key="label.contacts"/>:</h3>
            <p>${applicant.contacts}</p>
        </div>
    </div>
    <jsp:include page="fragments/uploadFile.jsp"/>
    <div>
        <h3><fmt:message key="label.education"/>:</h3>
        <p>${applicant.education}</p>
        <h3><fmt:message key="label.experience"/>:</h3>
        <p>${applicant.experience}</p>
        <h3><fmt:message key="label.skills"/>:</h3>
        <p>${applicant.skills}</p>
    </div>

    <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=editCv" method="post">
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

