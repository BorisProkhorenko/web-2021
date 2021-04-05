<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
    <jsp:useBean id="VacancyService" scope="request" class="com.epam.web.service.VacancyService"
                 type="com.epam.web.service.VacancyService"/>

    <c:set var="vacancies" value="${VacancyService.vacancies}"/>

    <c:forEach items="${vacancies}" var="vacancy">
        <div class="vacancy">
            <strong>${vacancy.name}</strong>
            <br/>
            <b>${vacancy.salary}</b>
            <p>${vacancy.requirements}</p>
            <form action="#"
                  method="POST">
                <button>
                    <fmt:message key="label.details"/>
                </button>
            </form>
        </div>
    </c:forEach>

    <p>"${sessionScope}"</p>

</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>

</body>
</html>
