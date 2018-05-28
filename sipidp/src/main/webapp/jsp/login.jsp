<%@ page import="Common.Constants" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/sip-identity-provider/jsp/css/bootstrap.min.css">
</head>


<div class="container">
    <div class=".container-fluid">
        <br>
        <h2>Please login to continue</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/Login?"+ request.getQueryString());%>">
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
                <input type="submit" class="btn btn-primary" name="<%out.print(Constants.getAddUserFiled());%>">
            </div>
        </div>
    </form>

    <%
        if (request.getAttribute(Constants.getInvalidCredentialsField()) != null) {
    %>
    <label><%out.print(request.getAttribute(Constants.getInvalidCredentialsField()));%></label>
    <%
        }
    %>
</div>

<script src="/sip-identity-provider/jsp//js/bootstrap.min.js"></script>

</body>
</html>