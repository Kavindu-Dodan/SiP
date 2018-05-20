<%@ page import="Common.Constants" %>
<!DOCTYPE>
<html>
<head>
    <title>
        Login Page
    </title>
</head>

<body>
<p> Please login to continue.</p>

<form method="post" action="<%out.println(request.getContextPath()+"/Login?"+ request.getQueryString());%>">
    <label>Username : </label><input name="<%out.print(Constants.getUsernameField());%>" type="text"><br>
    <label>Password : </label> <input name="<%out.print(Constants.getPasswordField());%>" type="password"><br>

    <input type="submit" value="Login"><br>

    <%
        if (request.getAttribute(Constants.getInvalidCredentialsField()) != null) {
    %>
            <label><%out.print(request.getAttribute(Constants.getInvalidCredentialsField()));%></label>
    <%
        }
    %>
</form>
</body>
</html>