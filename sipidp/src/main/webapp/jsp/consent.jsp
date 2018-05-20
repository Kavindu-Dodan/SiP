<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/20/2018
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consent page</title>
</head>
<body>
<p> Do you accept following information to be shared ?</p>

<form method="post" action="<%out.println(request.getContextPath()+"/Consent?"+ request.getQueryString());%>">
    <label>List of scopes should go here</label><br>
    <input type="submit" value="Accept" name="<%out.print(Constants.getConsentField());%>">
    <input type="submit" value="Decline" name="<%out.print(Constants.getConsentField());%>">
</form>
</body>
</html>
