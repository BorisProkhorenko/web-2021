<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="responseId" value="${not empty param.id ? param.id : sessionScope.responseId}" scope="session" />
<c:set var="vacancyId" value="${not empty param.vacancyId ? param.vacancyId : sessionScope.vacancyId}" scope="session" />

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>


<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<nav class="menu">
    <jsp:include page="fragments/menu.jsp"/>
</nav>

<main class="container">
    <jsp:useBean id="ResponseService" scope="request" class="com.epam.web.service.ResponseService"
                 type="com.epam.web.service.ResponseService"/>

    <jsp:useBean id="VacancyService" scope="request" class="com.epam.web.service.VacancyService"
                 type="com.epam.web.service.VacancyService"/>

    <c:set var="vacancy" value="${VacancyService.getById(vacancyId)}"/>

    <c:set var="response" value="${ResponseService.getById(responseId)}"/>

    <h1>${vacancy.name}</h1>
    <h3><fmt:message key="label.subject"/>:</h3>
    <p>${response.subject}</p>
    <h3><fmt:message key="label.details"/>:</h3>
    <p>${response.details}</p>

    <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=responses">
        <button>
            <fmt:message key="label.back"/>
        </button>
    </form>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
<p>"${sessionScope}"</p>
</body>
</html>
