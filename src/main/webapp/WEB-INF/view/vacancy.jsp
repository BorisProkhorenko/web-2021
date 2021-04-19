<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="vacancyId" value="${not empty param.id ? param.id : sessionScope.vacancyId}" scope="session" />

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>


<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>
<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="container"/>
</c:if>

<main class="${mainClass}">
    <c:import url="/controller?command=getVacancy&id=${vacancyId}"/>

        <h1>${vacancy.name}</h1>
        <strong>${vacancy.salary}</strong>
        <h3><fmt:message key="label.description"/>:</h3>
        <p>${vacancy.description}</p>
        <h3><fmt:message key="label.responsibility"/>:</h3>
        <p>${vacancy.responsibility}</p>
        <h3><fmt:message key="label.requirements"/>:</h3>
        <p>${vacancy.requirements}</p>.

<div class="buttons">
    <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=mainPage" method="post">
        <button>
            <fmt:message key="label.back"/>
        </button>
    </form>
<c:if test="${sessionScope.role == 'APPLICANT'}">
    <form class="end-page-button" action="#" method="post">
        <button>
            <fmt:message key="label.apply"/>
        </button>
    </form>
</c:if>

    <c:if test="${sessionScope.role == 'HR'}">
        <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=editVacancy&id=${vacancy.id}"
              method="post">
            <button>
                <fmt:message key="label.edit"/>
            </button>
        </form>

        <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=deleteVacancy&id=${vacancy.id}"
              method="post">
            <button>
                Delete
            </button>
        </form>
    </c:if>
</div>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
<p>"${sessionScope}"</p>
</body>
</html>

