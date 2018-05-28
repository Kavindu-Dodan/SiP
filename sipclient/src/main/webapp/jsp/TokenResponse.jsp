<%@ page import="Common.Constants" %>
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
        <textarea class="form-control" id="token">
            <%out.print(request.getAttribute("tokenResponseJson").toString());%>
        </textarea>
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
                           id="clientID" placeholder="Client ID" name="clientID">
                </div>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <label for="tokenEndpoint">Token endpoint URL</label>
                    <input type="text" class="form-control"
                           id="tokenEndpoint" name="tokenEndpoint" placeholder="Token endpoint">
                </div>
            </div>
            <div class="form-group">
                <div class="col-5">
                    <label for="clientSecret">Client secret</label>
                    <input type="text" class="form-control"
                           id="clientSecret" name="clientSecret" placeholder="Client Secret">
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

</div>

</body>
</html>
