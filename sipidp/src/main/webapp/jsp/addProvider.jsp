<%@ page import="Common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: kadolk
  Date: 5/22/2018
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Identity provider</title>
</head>
<body>
<form method="post" action="<%out.println(request.getContextPath()+"/admin/addProvider");%>">
    <label>Fill details to add an identity provider</label><br>
    <label>Provider name : <input type="text" name="providerName"> </label><br>
    <label>Provider URL : <input type="text" name="providerURL"></label> <br>
    <label>Discovery URL : <input type="text" name="discoveryURL"></label> <br>
    <input type="submit" name="<%out.print(Constants.getAddProviderField());%>">
</form>
</body>
</html>
