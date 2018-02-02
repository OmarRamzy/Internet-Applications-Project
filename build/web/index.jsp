<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome!</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="section" id="section1">
            <h1>Welcome</h1>
        </div>
        <div class="section" id="section2">
            <form action="${pageContext.request.contextPath}/index" method="post"><input type="submit" name="User" value="User" />
                <input type="submit" name="Admin" value="Admin" /></form>
        </div>
    </body>
</html>