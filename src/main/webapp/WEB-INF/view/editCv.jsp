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
    <c:import url="/controller?command=getUser&id=${sessionScope.id}"/>

    <form action="${pageContext.request.contextPath}/controller?command=updateCv" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="name"><fmt:message key="label.name"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${user.name}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="age"><fmt:message key="label.age"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="age" name="age" pattern="[0-9]{1,3}" value="${user.age}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="gender"><fmt:message key="label.gender"/></label>
            </div>
            <div class="col-75">
                <select id="gender" name="gender">
                    <option value="male"><fmt:message key="label.male"/></option>
                    <option value="female"><fmt:message key="label.female"/></option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="contacts"><fmt:message key="label.contacts"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="contacts" name="contacts">${user.contacts}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="education"><fmt:message key="label.education"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="education" name="education">${user.education}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="experience"><fmt:message key="label.experience"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="experience" name="experience">${user.experience}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="skills"><fmt:message key="label.skills"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="skills" name="skills">${user.skills}</textarea>
            </div>
        </div>
        <div class="row"><fmt:message key="label.submit" var="buttonValue"/>
            <input type="submit" value="${buttonValue}">
        </div>
    </form>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
