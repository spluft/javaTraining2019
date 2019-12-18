<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tag"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <tag:theme code="stylesheet" var="themeName" />
        <link href='<tag:url value="${themeName}"/>' rel="stylesheet" />
        <title><tag:message code="label.title" /></title>
    </head>

    <body>
        HomePage
        <form method="post" action="check">
            <table>
                <input type = "text" name="login"/><h4 style="color: red">${valid.get('loginerr')}</h4><br/>
                <input type = "text" name="password"/><h4 style="color: red">${valid.get('passworderr')}</h4><br/>
                 <tr>
                     <td><input type="submit" value="Submit"/></td>
                 </tr>
             </table>
         </form>
    </body>

</html>