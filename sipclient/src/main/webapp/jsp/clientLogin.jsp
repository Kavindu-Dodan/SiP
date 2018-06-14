<%@ page import="Common.Constants" %>
<%@ page import="Common.Configurations" %>
<%@ page import="static java.lang.String.format" %>
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
        <h2>Create authorization request</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/clientLogin");%>">
        <div class="form-group">
            <div class="col-5">
                <label for="authorizationEndpoint">Authorization endpoint</label>
                <input type="text" class="form-control"
                       id="authorizationEndpoint" placeholder="Auth endpoint" name="authorizationEndpoint"
                       value="<%out.println(Configurations.getAuthEndpointForIdToken());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientID">Client ID</label>
                <input type="text" class="form-control"
                       id="clientID" placeholder="Client ID" name="clientID"
                       value="<%out.println(Configurations.getClientIdForIdToken());%>">
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
                <label for="scope">Scope</label>
                <input type="text" class="form-control"
                       id="scope" name="scope"
                       value="<%out.println("openid identity_share");%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="responseType">ResponseType</label>
                <input type="text" class="form-control"
                       id="responseType" name="responseType"
                       value="<%out.println("code");%>">
            </div>
        </div>
        <div class="form-group">
            <label for="visualized">Request visualized : </label>
            <textarea class="form-control" id="visualized"><%
                out.print(format(
                        "%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
                        Configurations.getAuthEndpointForIdToken(),
                        Configurations.getClientIdForIdToken(),
                        Constants.getMyContextPath() + Constants.getRedirectEndpoint(),
                        "openid identity_share",
                        "code"));
            %></textarea>
        </div>
        <div class="form-group">
            <div class="col-5">
                <input type="submit" class="btn btn-primary" value="Obtain Auth Code">
            </div>
        </div>
    </form>
</div>

<script src="/sip-client/jsp/js/bootstrap.min.js"></script>

</body>
</html>