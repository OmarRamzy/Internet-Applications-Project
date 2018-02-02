<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <link rel="stylesheet" href="css/s.css">
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            function sendajax() {
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                if (username === "") {
                    alert("Username filed cannot be blank!");
                    return;
                }
                if (password === "") {
                    alert("Password filed cannot be blank!");
                    return;
                }
                var data = username + "-" + password;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "user_login", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        if (xmlhttp.responseText == "Invalid Username or Password") {
                            alert(xmlhttp.responseText);
                        } else {
                            window.location = "Home";
                        }
                    }
                };
            }
        </script>
    </head>
    <body>
        <%
            boolean check = false;
            Cookie[] AllCookies = request.getCookies();
            for (Cookie c : AllCookies) {
                if (c.getName().equals("UserSession")) {
                    check = true;
                    Map sessionsManager = (HashMap) application.getAttribute("Manager");
                    if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                        request.getServletContext().setAttribute("Mode", "User");
                        request.getRequestDispatcher("Home").forward(request, response);
                    } else {
                        c.setMaxAge(0);
                    }
                }
            }
        %>
        <div class="card">
            <h1>Log in</h1><br>
            <input type="text" name="username" id="username" placeholder="Username">
            <br>
            <input type = "password" name="password" id="password" placeholder="Password">
            <br>
            <input type="submit" class="submit" value="Login" onclick="sendajax()">
            <div class="help">
                <a href="user_sign_up.jsp">Register</a>
            </div>
        </div>
    </body>
</html>