<!DOCTYPE>
<html>
<head>
</head>

<body>

<p>
    <% out.println("Something went wrong.!"); %> <br>
    <%
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String exceptionType = "-";
        if (throwable != null) {
            exceptionType = throwable.getClass().getName();
        }

        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    %>

   Exception type : <%out.println(exceptionType);%> <br>
   Error Code : <%out.println(message);%> <br>
</p>
</body>
</html>