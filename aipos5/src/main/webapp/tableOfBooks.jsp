<%--
  Created by IntelliJ IDEA.
  User: Artiom
  Date: 3/21/2017
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Table of books</title>
    <style>
        div { border:1px solid black }
    </style>
</head>
<div>
    <p>
    <form action="${pageContext.request.contextPath}/tableOfBooks" method="post">
        <input type="submit" name="addBook" value="добавить">
        <input type="submit" name="updateBook" value="обновить">
    </form>
    </p>
    <p>
    <div style="display: table;">
        <div style="display: table-row;">
            <div style="height:10px; display: table-cell">Название книги</div>
            <div style="display: table-cell">Имя автора</div>
            <div style="display: table-cell">Количество страниц</div>
        </div>
            <c:forEach items="${books}" var="book">
            <div style="display: table-row;" itemscope itemtype="http://schema.org/Person">
                <div style="display: table-cell" itemprop="bookName"> ${book.getBookName()}</div>
                <div style="display: table-cell" itemprop="authorName"> ${book.getAuthorName()} </div>
                <div style="display: table-cell" itemprop="pageValue"> ${book.getPageValue()}</div>
                <div style="display: table-cell"><a href="/remove?<c:out value="${book.getId()}"/>"> удалить</a></div>
                <div style="display: table-cell"><a href="/edit?<c:out value="${book.getId()}"/>"> изменить</a></div>
            </div>
        </c:forEach>
</div>
    </p>
</div>
</body>
</html>
