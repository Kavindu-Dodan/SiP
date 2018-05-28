<%@ page import="Common.Constants" %>
<!doctype html>
<html lang="en">
<head>
    <title>Client Login</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/sip-client/jsp/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class=".container-fluid">
        <br>
        <h2>Please login to continue</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/clientLogin");%>">
        <div class="form-group">
            <div class="col-5">
                <label for="authorizationEndpoint">Authorization endpoint</label>
                <input type="text" class="form-control"
                       id="authorizationEndpoint" placeholder="Auth endpoint" name="authorizationEndpoint">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientID">Client ID</label>
                <input type="text" class="form-control"
                       id="clientID" placeholder="Client ID" name="clientID">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="redirectURL">Redirect URL</label>
                <input type="text" class="form-control"
                       id="redirectURL" name="redirectURL"
                       value="<%out.println(Constants.getMyContextPath() + Constants.getRedirectEndpoint());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" class="btn btn-primary" name="">
            </div>
        </div>
    </form>
</div>

<script src="/sip-client/jsp/js/bootstrap.min.js"></script>

</body>
</html>