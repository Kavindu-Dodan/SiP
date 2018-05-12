<!DOCTYPE>
<html>
<head>
</head>

<body>
<p> This should be the login page</p>

<form method="post" action="<%out.println(request.getContextPath()+"/Login");%>">
    <% out.println("Username :"); %> <input type="text"><br>
    <% out.println("Password :"); %> <input type="password"><br>

    <input type="submit" value="Login">
</form>
</body>
</html>