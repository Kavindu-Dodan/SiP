<%@ page import="Common.Exceptions.FrameworkUncheckedException" %>
<%@ page import="Common.Exceptions.FrameworkCheckedException" %>
<!doctype html>
<html lang="en">
<head>
    <title>Error page</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/sip-identity-provider/jsp/css/bootstrap.min.css">

</head>

<body>
<div class="container">
    <div class=".container-fluid">
        <br>
        <h2>Something went wrong</h2>
        <br>
    </div>

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
    <div class="alert alert-danger" role="alert">
        <div class="col-5">
            <label>Exception type : <%out.println(exceptionType);%></label>
        </div>
        <div class="col-5">
            <label>Error message: <%out.println(message);%></label>
        </div>
    </div>

    <script src="/sip-identity-provider/jsp/js/bootstrap.min.js"></script>

</div>
</body>
</html>