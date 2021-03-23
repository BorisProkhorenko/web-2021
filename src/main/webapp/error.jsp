<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<main class="container">
    <c:if test="${errorMessage != null}">
        <div style="color:#FF0000">
                ${param.errorMessage}
        </div>
    </c:if>
</main>
</body>
</html>
