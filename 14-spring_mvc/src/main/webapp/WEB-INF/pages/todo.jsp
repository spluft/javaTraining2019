<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Index Page</title>
    </head>

    <body>
        <table style="width:100%">
            <tr><th style="width:80%">Notes</th><th style="width:20%">Action</th></tr>

            <c:forEach items="${notes}" var="note">
                <tr>
                    <form action="${pageContext.request.contextPath}/del" method="post">
                        <td style="width:80%">
                            <input id="id" name="id" type="hidden" value="${note.getId()}">
                            <input id="login" name="login" type="hidden" value="${note.getLogin()}">
                            ${note.getMessage()}
                        </td>
                        <td style="width:20%"><input type="submit" value='<tag:message code="todo.delbutton" />'>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>

        <form action="${pageContext.request.contextPath}/del/all" method="post">
            <input type="submit" value='delete all'>
        </form>

        <form action="${pageContext.request.contextPath}/del/first" method="post">
            <input type="submit" value='delete first'>
        </form>

        <form method="post" action="${pageContext.request.contextPath}/add">
            <input type="text" name="mess"/><input type="submit" value="'<tag:message code="todo.addbutton" />'">
        </form>

    </body>

</html>