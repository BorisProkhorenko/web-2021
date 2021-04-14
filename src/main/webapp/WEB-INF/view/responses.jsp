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
    <jsp:useBean id="ResponseService" scope="request" class="com.epam.web.service.ResponseService"
                 type="com.epam.web.service.ResponseService"/>

    <jsp:useBean id="VacancyService" scope="request" class="com.epam.web.service.VacancyService"
                 type="com.epam.web.service.VacancyService"/>

    <c:set var="responses" value="${ResponseService.getResponsesByUserId(sessionScope.id)}"/>

    <c:forEach items="${responses}" var="response">
        <div class="list-item">
            <c:set var="id" value="${response.vacancyId}"/>
            <c:set var="applicant" value="${VacancyService.getById(id)}"/>
            <strong>${applicant.name}</strong>
            <br/>
            <b>
            <c:if test="${sessionScope.lang == 'en'}">
            <fmt:formatDate value="${response.date}" pattern="dd-MM-yyyy HH:mm" />
            </c:if>
            <c:if test="${sessionScope.lang != 'en'}">
                <fmt:formatDate value="${response.date}" pattern="dd.MM.yyyy HH:mm" />
            </c:if>
          </b>
            <p>${response.subject}</p>
            <form action="${pageContext.request.contextPath}/controller?command=responseDetails&id=${response.id}&vacancyId=${id}"
                  method="POST">
                <button>
                    <fmt:message key="label.details"/>
                </button>
            </form>
        </div>
    </c:forEach>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>

</body>
</html>
