<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/20/2018
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Create new user</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/sip-identity-provider/jsp/css/bootstrap.min.css">

    <title>Document</title>
</head>
<body>

<div class="container">
    <div class=".container-fluid">
        <br>
        <h2>Fill details to add a user</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/admin/createUser");%>">
        <div class="form-group">
            <div class="col-5">
                <label for="userName">Username</label>
                <input type="text" class="form-control" name="<%out.print(Constants.getUsernameField());%>"
                       id="userName" placeholder="User name">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="<%out.print(Constants.getPasswordField());%>"
                       id="password" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" id="email" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="age">Age</label>
                <input type="text" class="form-control" name="age" id="age" placeholder="User name">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" class="btn btn-primary" name="<%out.print(Constants.getAddUserFiled());%>">
            </div>
        </div>
    </form>
</div>

<script src="/sip-identity-provider/jsp//js/bootstrap.min.js"></script>

</body>
</html>
