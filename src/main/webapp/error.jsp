<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<main>
    <h2>Error</h2>
    <c:if test="${errorMessage != null}">
        <div style="color:#FF0000">
                ${errorMessage}
        </div>
    </c:if>
</main>
</body>
</html>
