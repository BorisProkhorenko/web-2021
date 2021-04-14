<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="vacancyId" value="${not empty param.id ? param.id : sessionScope.vacancyId}" scope="session" />

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>


<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<nav class="menu">
    <jsp:include page="fragments/menu.jsp"/>
</nav>

<main class="container">
    <jsp:useBean id="VacancyService" scope="request" class="com.epam.web.service.VacancyService"
                 type="com.epam.web.service.VacancyService"/>

    <c:set var="applicant" value="${VacancyService.getById(vacancyId)}"/>

        <h1>${applicant.name}</h1>
        <strong>${applicant.salary}</strong>
        <h3><fmt:message key="label.description"/>:</h3>
        <p>${applicant.description}</p>
        <h3><fmt:message key="label.responsibility"/>:</h3>
        <p>${applicant.responsibility}</p>
        <h3><fmt:message key="label.requirements"/>:</h3>
        <p>${applicant.requirements}</p>.

<div class="buttons">
    <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=mainPage" method="post">
        <button>
            <fmt:message key="label.back"/>
        </button>
    </form>
    <form class="end-page-button" action="#" method="post">
        <button>
            <fmt:message key="label.apply"/>
        </button>
    </form>
</div>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
<p>"${sessionScope}"</p>
</body>
</html>

