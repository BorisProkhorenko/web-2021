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

<main class="applicant-container">


    <c:import url="/controller?command=responseList"/>

    <c:forEach items="${responseList}" var="response">
        <div class="list-item">
            <c:set var="id" value="${response.vacancyId}"/>
            <c:import url="/controller?command=getVacancy&id=${id}"/>
            <strong>${vacancy.name}</strong>
            <br/>
            <b>
                <c:if test="${sessionScope.lang == 'en'}">
                    <fmt:formatDate value="${response.date}" pattern="dd-MM-yyyy HH:mm"/>
                </c:if>
                <c:if test="${sessionScope.lang != 'en'}">
                    <fmt:formatDate value="${response.date}" pattern="dd.MM.yyyy HH:mm"/>
                </c:if>
            </b>
            <p>${response.subject}</p>
            <div class="single-button">
                <form action="${pageContext.request.contextPath}/controller?command=responseDetails&id=${response.id}&vacancyId=${id}"
                      method="POST">
                    <button>
                        <fmt:message key="label.details"/>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
