<!DOCTYPE>
<html>
<head>
</head>

<body>
<% System.out.println("Something went wrong.!")%>
<%
    Throwable t = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

    if (t != null){
        System.out.println(t.getMessage());
    }

%>
</body>
</html>