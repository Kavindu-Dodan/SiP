<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/20/2018
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form method="post" action="<%out.println(request.getContextPath()+"/admin/createUser");%>">
    <label>Fill details to add a user</label><br>
    <label>Username : <input type="text" name="username"> </label><br>
    <label>Password : <input type="password" name="password"></label> <br>
    <label>Email : <input type="text" name="email"></label> <br>
    <label>Age : <input type="number" name="age"></label> <br>
    <input type="submit" name="<%out.print(Constants.getAddUserFiled());%>">
</form>
</body>
</html>
