<%@ page import="Common.Utils" %>
<!doctype html>
<html lang="en">
<head>
    <title>Complete response</title>
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
        <h2>Responses</h2>
        <br>
    </div>

    <div class=".container-fluid">
        <br>
        <h4>Token response</h4>
        <br>
        <textarea class="form-control" id="token">
           <%out.print(Utils.getTokenResponse());%>
        </textarea>
    </div>

    <div class=".container-fluid">
        <br>
        <h4>SIP response</h4>
        <br>
        <textarea class="form-control" id="sip">
            SIP response :  <%out.print(request.getAttribute("sipResponse"));%>
        </textarea>
    </div>

</div>

</body>
</html>
