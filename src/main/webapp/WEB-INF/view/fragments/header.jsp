<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link rel="stylesheet" href="static/style.css"/>
</head>

<header class="header">
<div class="header-section">
    <div class="header-item headerLogo">
        <em>HR</em>
    </div>
</div>
    <div class="header-section">

        <div class="header-item headerButton">
            <a href="#">Language</a>
        </div>
        <div class="header-item headerButton">
            <a href="${pageContext.request.contextPath}/controller?command=logout">Log out</a>
        </div>
    </div>

</header>

