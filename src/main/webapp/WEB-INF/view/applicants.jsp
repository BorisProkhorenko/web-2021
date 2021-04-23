<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="vacancyId" value="${not empty param.id ? param.id : sessionScope.vacancyId}" scope="session"/>

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
            <c:if test="${applicantInProcess.state != 'HIRED' && applicantInProcess.state != 'REJECTED'}">
                <form action="${pageContext.request.contextPath}/controller?command=updateProcess&id=${applicantInProcess.id}"
                      class="table-form" method="post">
                    <tr>
                        <td>${applicant.name}</td>
                        <td>${applicant.age}</td>
                        <td>
                            <select id="state" name="state">
                                <c:if test="${applicantInProcess.state == 'NEW'}">
                                    <option value="New" selected>New</option>
                                    <option value="Preliminary">Preliminary Interview</option>
                                    <option value="Technical">Technical Interview</option>
                                    <option value="Hired">Hired</option>
                                    <option value="Rejected">Rejected</option>

                                </c:if>

                                <c:if test="${applicantInProcess.state == 'PRELIMINARY'}">
                                    <option value="New">New</option>
                                    <option value="Preliminary" selected>Preliminary Interview</option>
                                    <option value="Technical">Technical Interview</option>
                                    <option value="Hired">Hired</option>
                                    <option value="Rejected">Rejected</option>

                                </c:if>

                                <c:if test="${applicantInProcess.state == 'TECHNICAL'}">
                                    <option value="New">New</option>
                                    <option value="Preliminary">Preliminary Interview</option>
                                    <option value="Technical" selected>Technical Interview</option>
                                    <option value="Hired">Hired</option>
                                    <option value="Rejected">Rejected</option>

                                </c:if>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="table-input" name="points"
                                   value="${applicantInProcess.preliminaryPoints}" pattern="[0-9]{1,3}">
                        </td>
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
                        <td>
                            <input type="submit" value="update">
                        </td>
                    </tr>
                </form>
            </c:if>
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
