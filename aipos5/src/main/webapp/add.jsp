<%--
  Created by IntelliJ IDEA.
  User: Vitalij
  Date: 4/13/2017
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/add" method="post">
        <table border="1">
            <tr>
                <th>Book</th>
                <th><input name="bookName" type="text" size="10"></th>
                <th><input name="authorName" type="text" size="10"></th>
                <th><input name="pageValue" type="text" size="10"></th>
            </tr>
        </table>
        <input type="submit" name="ok" value="ok">
        <input type="submit" name="cancel" value="cancel">
</form>
</body>
</html>
