<%@ page import="Common.Exceptions.FrameworkUncheckedException" %>
<%@ page import="Common.Exceptions.FrameworkCheckedException" %>
<!DOCTYPE>
<html>
<head>
</head>

<body>


<% out.println("Something went wrong.!"); %> <br>
<%
    final Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

    final String exceptionType = throwable.getClass().getSimpleName();
    final String message;

    if (throwable instanceof FrameworkUncheckedException) {
        message = ((FrameworkUncheckedException) throwable).getCustomMessage();
    } else if (throwable instanceof FrameworkCheckedException) {
        message = ((FrameworkCheckedException) throwable).getCustomMessage();
    } else {
        if (request.getAttribute(RequestDispatcher.ERROR_MESSAGE) != null) {
            message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        } else {
            message = "Unknown error occurred";
        }
    }
%>

<label>Exception type: </label><%out.println(exceptionType);%><br>
<label>Error message: </label><%out.println(message);%>

</body>
</html>