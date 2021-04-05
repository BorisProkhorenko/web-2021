
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

    <jsp:include page="fragments/header.jsp" />


<nav class="menu">
    <jsp:include page="fragments/menu.jsp" />
</nav>

<main class="container">
    <h2>Here will be applicants CV</h2>
</main>
<p>"${sessionScope}"</p>
</body>
</html>

