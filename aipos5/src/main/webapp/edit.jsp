<%--
  Created by IntelliJ IDEA.
  User: Artiom
  Date: 3/27/2017
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>rename</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/edit" method="post">
        <table border="1">
            <tr>
                <th>Book</th>
                <th><input name="bookNameEdit" type="text" value="${bookNameEdit}" size="10"></th>
                <th><input name="authorNameEdit" type="text" value="${authorNameEdit}" size="10"></th>
                <th><input name="pageValueEdit" type="text" value="${pageValueEdit}" size="10"></th>
            </tr>
        </table>
        <input type="submit" name="ok" value="ok">
        <input type="submit" name="cancel" value="cancel">
</form>
</body>
</html>