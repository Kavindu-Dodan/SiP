<%@ page import="Common.Constants" %>
<%@ page import="Common.Configurations" %>
<!doctype html>
<html lang="en">
<head>
    <title>Client Configurations</title>
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
        <h2>Identity provider configurations</h2>
        <br>
    </div>

    <form method="post" action="<%out.println(request.getContextPath()+"/configReceiver");%>">
        <h5>Identity provider for OpenID Connect flow</h5>

        <div class="form-group">
            <div class="col-5">
                <label for="authorizationEndpointForOIDC">Authorization endpoint</label>
                <input type="text" class="form-control"
                       id="authorizationEndpointForOIDC" placeholder="Auth endpoint"
                       name="authorizationEndpointForOIDC"
                       value="<%out.println(Configurations.getAuthEndpointForIdToken());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientIDForOIDC">Client ID</label>
                <input type="text" class="form-control"
                       id="clientIDForOIDC" placeholder="Client ID" name="clientIDForOIDC"
                       value="<%out.println(Configurations.getClientIdForIdToken());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientSecretForOIDC">Client Secret</label>
                <input type="text" class="form-control"
                       id="clientSecretForOIDC" placeholder="Client Secret" name="clientSecretForOIDC"
                       value="<%out.println(Configurations.getClientSecretForIdToken());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="tokenEndpointForOIDC">Token endpoint</label>
                <input type="text" class="form-control"
                       id="tokenEndpointForOIDC" placeholder="Token endpoint"
                       name="tokenEndpointForOIDC"
                       value="<%out.println(Configurations.getTokenEndpointForIdToken());%>">
            </div>
        </div>

        <h5>Identity provider for SiP flow</h5>

        <div class="form-group">
            <div class="col-5">
                <label for="authorizationEndpointForSIP">Authorization endpoint</label>
                <input type="text" class="form-control"
                       id="authorizationEndpointForSIP" placeholder="Auth endpoint"
                       name="authorizationEndpointForSIP"
                       value="<%out.println(Configurations.getAuthEndpointForIdSip());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientIDForSiP">Client ID</label>
                <input type="text" class="form-control"
                       id="clientIDForSiP" placeholder="Client ID" name="clientIDForSiP"
                       value="<%out.println(Configurations.getClientIdForIdSip());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="clientSecretForSiP">Client Secret</label>
                <input type="text" class="form-control"
                       id="clientSecretForSiP" placeholder="Client Secret" name="clientSecretForSiP"
                       value="<%out.println(Configurations.getClientSecretForIdSip());%>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-5">
                <label for="tokenEndpointForSiP">Token endpoint</label>
                <input type="text" class="form-control"
                       id="tokenEndpointForSiP" placeholder="Token endpoint"
                       name="tokenEndpointForSiP" value="<%out.println(Configurations.getTokenEndpointForIdSip());%>">
            </div>
        </div>


        <div class="form-group">
            <div class="col-5">
                <input type="submit" class="btn btn-primary" value="Save Configurations & Proceed to token obtaining">
            </div>
        </div>
    </form>
</div>

<script src="/sip-client/jsp/js/bootstrap.min.js"></script>

</body>
</html>