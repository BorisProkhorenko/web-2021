<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>

<c:import url="/controller?command=applicantList"/>

<main class="container">

    <h1>${vacancy.name}</h1>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>State</th>
            <th>Preliminary interview points</th>
            <th>CV</th>
            <th>Responses</th>
            <th>Edit</th>
        </tr>
        <c:forEach items="${applicantList}" var="applicantInProcess">
            <c:set var="applicant" value="${applicantInProcess.user}"/>

            <tr>
                <td>${applicant.name}</td>
                <td>${applicant.age}</td>
                <td>${applicantInProcess.state}</td>
                <td>${applicantInProcess.preliminaryPoints}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=cv&id=${applicantInProcess.id}">
                        To cv
                    </a>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=responses&id=${applicantInProcess.id}">
                        To responses
                    </a>
                </td>
                <td>Edit</td>
            </tr>

        </c:forEach>
    </table>
    <br/>
    <br/>
    <div class="single-button">
        <form class="end-page-button"
              action="${pageContext.request.contextPath}/controller?command=mainPage"
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
