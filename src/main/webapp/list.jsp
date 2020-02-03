<%--
  Created by IntelliJ IDEA.
  User: olena
  Date: 03-Feb-20
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
</head>
<body>
<ul class="list-group">
 <%
     for (int i = 0 ; i<20; i++) {
         out.println("<li class=\"list - group - item\">Dapibus ac facilisis in</li> ");
     }

 %>

</ul>
</body>
</html>
