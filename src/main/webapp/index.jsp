<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="static/style.css"/>
</head>
<body>

<main class="login">
    <form action="${pageContext.request.contextPath}/controller?command=login"
          method="POST">
        <h1>Login Form</h1>
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="text" name="password" class="form-control" required>
        </div>

        <input type="submit" class="btn" value="Login"/>
    </form>
</main>
</body>
</html>
