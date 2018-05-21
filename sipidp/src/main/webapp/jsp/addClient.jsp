<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/21/2018
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add client</title>
</head>
<body>
<form method="post" action="<%out.println(request.getContextPath()+"/admin/addClient");%>">
    <label>Fill details to add a client</label><br>
    <label>Client name : <input type="text" name="clientName"> </label><br>
    <label>Redirect URL : <input type="text" name="redirectUrl"></label> <br>
    <input type="submit" name="<%out.print(Constants.getAddUserFiled());%>">
</form>
</body>
</html>
