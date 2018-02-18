<!DOCTYPE>
<html>
<head>
</head>

<body>
<p> This should be the login page</p>

<form method="post" action="<%out.println(request.getContextPath()+"/Login");%>">

    <input type="submit" value="Login">
</form>
</body>
</html>