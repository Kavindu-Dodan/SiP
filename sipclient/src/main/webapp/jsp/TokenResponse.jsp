<%@ page import="Common.Constants" %>
<%@ page import="Common.Configurations" %>
<%@ page import="static java.lang.String.format" %>
<!doctype html>
<html lang="en">
<head>
    <title>Tokens</title>
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
        <h2>Token response</h2>
        <br>
        <label for="response">Response as JSON :</label><textarea class="form-control" id="response">
            <%out.print(request.getAttribute("tokenResponseJson").toString());%></textarea>
    </div>

    <div class=".container-fluid">
        <br>
        <h2>Create SIP request</h2>
        <br>
        <form method="post" action="<%out.println(request.getContextPath()+"/clientSip");%>">
            <div class="form-group">
                <div class="col-5">
                    <label for="sipToken">SIP Token</label>
                    <input type="text" class="form-control"
                           id="sipToken" placeholder="SIP token" name="sipToken">
                </div>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <label for="clientID">Client ID</label>
                    <input type="text" class="form-control"
                           id="clientID" placeholder="Client ID" name="clientID"
                           value="<%out.println(Configurations.getClientIdForIdSip());%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <label for="tokenEndpoint">Token endpoint URL</label>
                    <input type="text" class="form-control"
                           id="tokenEndpoint" name="tokenEndpoint" placeholder="Token endpoint"
                           value="<%out.println(Configurations.getTokenEndpointForIdSip());%>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <label for="clientSecret">Client secret</label>
                    <input type="text" class="form-control"
                           id="clientSecret" name="clientSecret" placeholder="Client Secret"
                           value="<%out.println(Configurations.getClientSecretForIdSip());%>">
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
                    <label for="grant">Grant Type</label>
                    <input type="text" class="form-control"
                           id="grant" name="grant"
                           value="<%out.println("sip_token");%>">
                </div>
            </div>
            <div class="form-group">
                <label for="visualized">Request visualized : </label>
                <textarea class="form-control" id="visualized"><%
                    out.print(format("sip_token=%s&grant_type=%s&client_id=%s&redirect_url=%s&client_secret=%s",
                            "<SIP-TOKEN>",
                            "sip_token",
                            Configurations.getClientIdForIdSip(),
                            Constants.getMyContextPath() + Constants.getRedirectEndpoint(),
                            Configurations.getClientSecretForIdSip()));
                %></textarea>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <input type="submit" class="btn btn-primary" value="Obtain SIP response">
                </div>
            </div>
        </form>
    </div>

</div>

</body>
</html>
