<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>
<main class="container">
    <c:import url="/controller?command=getVacancy&id=${sessionScope.vacancyId}"/>

    <form action="${pageContext.request.contextPath}/controller?command=updateVacancy" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="name"><fmt:message key="label.name"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${vacancy.name}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="salary"><fmt:message key="label.salary"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="salary" name="salary" value="${vacancy.salary}">
            </div>
        </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="description"><fmt:message key="label.description"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="description" name="description">${vacancy.description}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="responsibility"><fmt:message key="label.responsibility"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="responsibility" name="responsibility">${vacancy.responsibility}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="requirements"><fmt:message key="label.requirements"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="requirements" name="requirements">${vacancy.requirements}</textarea>
            </div>
            <div class="row"><fmt:message key="label.submit" var="buttonValue"/>
                <input type="submit" value="${buttonValue}">
            </div>
        </div>
    </form>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>

</html>