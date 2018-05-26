<%@ page import="Common.Constants" %>
<%@ page import="storage.UserSessions" %>
<%@ page import="Common.Exceptions.FrameworkCheckedException" %>
<%@ page import="storage.EndUsers" %>
<%@ page import="Models.User" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/20/2018
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Consent</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/sip-identity-provider/jsp/css/bootstrap.min.css">

</head>

<body>
<div class="container">
    <div class=".container-fluid">
        <br>
        <h2>Do you accept following information to be shared ?</h2>
        <br>
    </div>


    <form method="post" action="<%out.println(request.getContextPath()+"/Consent?"+ request.getQueryString());%>">

        <%
            String loggedInUser = null;
            try {
                loggedInUser = UserSessions.getLoggedInUser(request.getSession(false).getId());
            } catch (FrameworkCheckedException e) {
                e.printStackTrace();
            }

            User endUser = null;
            try {
                endUser = EndUsers.getUserByUsername(loggedInUser);
            } catch (FrameworkCheckedException e) {
                e.printStackTrace();
            }

            if (endUser != null) {
        %>

        <div class="form-group">
            <div class="col-5">
                <label>Email : <%out.print(endUser.getEmail());%></label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-5">
                <label>Age :  <%out.print(endUser.getAge());%></label>
            </div>
        </div>

        <%
            }
        %>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" value="Accept" class="btn btn-primary"
                       name="<%out.print(Constants.getConsentField());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" value="Decline" class="btn btn-primary"
                       name="<%out.print(Constants.getConsentField());%>">
            </div>
        </div>
    </form>
</div>

<script src="/sip-identity-provider/jsp//js/bootstrap.min.js"></script>

</body>
</html>
