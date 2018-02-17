<!DOCTYPE>
<html>
<head>
</head>

<body>
<% out.println("Something went wrong.!")%>
<%
	Throwable t = (Throwable)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

%>
</body>
</html>