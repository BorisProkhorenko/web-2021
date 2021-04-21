<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>
<main class="container">


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
        <tr>
            <td>Ячейка 1</td>
            <td>Ячейка 2</td>
            <td>Ячейка 3</td>
            <td>Ячейка 4</td>
            <td>Ячейка 5</td>
            <td>Ячейка 6</td>
            <td>Ячейка 7</td>
        </tr>
    </table>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
