<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/22/2018
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Add identity provider</title>
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
        <h2>Fill details to add an identity provider</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/admin/addProvider");%>">
        <div class="form-group">
            <div class="col-5">
                <label for="providerName">Provider name</label>
                <input type="text" class="form-control" name="providerName"
                       id="providerName" placeholder="providerName">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="providerURL">Provider URL</label>
                <input type="text" class="form-control" name="providerURL"
                       id="providerURL" placeholder="Provider URL">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="discoveryURL">Discovery URL</label>
                <input type="text" class="form-control" name="discoveryURL" id="discoveryURL" placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" class="btn btn-primary" name="<%out.print(Constants.getAddProviderField());%>">
            </div>
        </div>
    </form>
</div>

<script src="/sip-identity-provider/jsp//js/bootstrap.min.js"></script>

</body>
</html>
