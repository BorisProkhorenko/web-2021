<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="responseId" value="${not empty param.id ? param.id : sessionScope.responseId}" scope="session"/>
<c:set var="vacancyId" value="${not empty param.vacancyId ? param.vacancyId : sessionScope.vacancyId}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>


<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="applicant-container"/>
    <c:import url="/controller?command=getVacancy&id=${vacancyId}"/>

</c:if>

<c:if test="${sessionScope.role == 'HR'}">
    <c:set var="mainClass" value="container"/>
    <c:import url="/controller?command=getProcess&id=${sessionScope.processId}"/>
    <c:set var="vacancy" value="${process.vacancy}"/>

</c:if>

<c:import url="/controller?command=getResponse&id=${responseId}"/>

<main class="${mainClass}">


    <h1>${vacancy.name}</h1>
    <h3><fmt:message key="label.subject"/>:</h3>
    <p>${jobResponse.subject}</p>
    <h3><fmt:message key="label.details"/>:</h3>
    <p>${jobResponse.details}</p>
    <div class="single-button">
        <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=responses"
              method="post">
            <button>
                <fmt:message key="label.back"/>
            </button>
        </form>
    </div>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
