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

<body>
<main class="container">
    <jsp:useBean id="UserService" scope="request" class="com.epam.web.service.UserService"
                 type="com.epam.web.service.UserService"/>
    <c:set var="applicant" value="${UserService.getById(sessionScope.id)}"/>

    <form action="${pageContext.request.contextPath}/controller?command=updateCv" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="name">Name</label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${applicant.name}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="age">Age</label>
            </div>
            <div class="col-75">
                <input type="text" id="age" name="age" value="${applicant.age}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="gender">Gender</label>
            </div>
            <div class="col-75">
                <select id="gender" name="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="contacts">Contacts</label>
            </div>
            <div class="col-75 big-size">
                <textarea id="contacts" name="contacts">${applicant.contacts}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="education">Education</label>
            </div>
            <div class="col-75 big-size">
                <textarea id="education" name="education">${applicant.education}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="experience">Experience</label>
            </div>
            <div class="col-75 big-size">
                <textarea id="experience" name="experience">${applicant.experience}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="skills">Skills</label>
            </div>
            <div class="col-75 big-size">
                <textarea id="skills" name="skills">${applicant.skills}</textarea>
            </div>
        </div>
        <div class="row">
            <input type="submit" value="Submit">
        </div>
    </form>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
