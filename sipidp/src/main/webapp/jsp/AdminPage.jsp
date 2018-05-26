<%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/21/2018
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Admin page</title>
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
        <h2>List of admin functions</h2>
        <br>
    </div>

    <div class=".container-fluid">
        <h3>End user related functionality</h3>

        <div class="row">
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/createUser" role="button">Add User</a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/ListUsers" role="button">List Users</a>
            </div>
        </div>
        <div class="row"><br></div>
    </div>

    <div class=".container-fluid">
        <h3>Client application related functionality</h3>
        <br>
        <div class="row">
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/addClient" role="button">Add client</a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/ListClients" role="button">List
                    Clients</a>
            </div>
        </div>
        <div class="row"><br></div>
    </div>

    <div class=".container-fluid">
        <h3>Identity provider related functionality</h3>
        <br>
        <div class="row">
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/addProvider" role="button">Add Identity
                    Provider</a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary" href="/sip-identity-provider/admin/ListProviders" role="button">List Identity
                    Provider</a>
            </div>
        </div>
        <div class="row"><br></div>
    </div>

</div>

<script src="/sip-identity-provider/jsp//js/bootstrap.min.js"></script>

</body>
</html>
