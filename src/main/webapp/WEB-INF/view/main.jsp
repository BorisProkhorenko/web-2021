<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageIndex" value="${not empty param.pageIndex ? param.pageIndex : 1}" scope="request" />

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

    <c:set var="vacancies" value="${VacancyService.getVacanciesByPage(pageIndex)}"/>
    <c:set var="numberOfPages" value="${VacancyService.pagesCount}"/>

    <c:forEach items="${vacancies}" var="vacancy">
        <div class="list-item">
            <strong>${vacancy.name}</strong>
            <br/>
            <b>${vacancy.salary}</b>
            <p>${vacancy.requirements}</p>
            <form action="${pageContext.request.contextPath}/controller?command=vacancy&id=${vacancy.id}"
                  method="POST">
                <button>
                    <fmt:message key="label.details"/>
                </button>
            </form>
        </div>
    </c:forEach>

    <c:if test="${numberOfPages>1}">
    <div class="pagination">
        <c:if test="${pageIndex > 1}">
        <a href="${pageContext.request.contextPath}/controller?command=mainPage&pageIndex=${pageIndex - 1}">
            <fmt:message key="label.previous"/>
        </a>
        </c:if>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${i!=pageIndex}">
                    <a href="${pageContext.request.contextPath}/controller?command=mainPage&pageIndex=<c:out value="${i}"/>">
                        <c:out value="${i}"/></a>
                </c:when>
                <c:otherwise>
                    <b><c:out value="${i}"/></b>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${pageIndex < numberOfPages}">
            <a href="${pageContext.request.contextPath}/controller?command=mainPage&pageIndex=${pageIndex + 1}">
                <fmt:message key="label.next"/>
            </a>
        </c:if>
    </div>
    </c:if>
    <p>"${sessionScope}"</p>

</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>

</body>
</html>
