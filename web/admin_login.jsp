<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Admin Login</title>
        <link rel="stylesheet" href="css/s.css">
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            function sendajax() {
                var admin_username = document.getElementById("admin_username").value;
                var password = document.getElementById("password").value;
                if (admin_username === "") {
                    alert("Username filed cannot be blank!");
                    return;
                }
                if (password === "") {
                    alert("Password filed cannot be blank!");
                    return;
                }
                var data = admin_username + "-" + password;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "admin_login", true);
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
                if (c.getName().equals("AdminSession")) {
                    check = true;
                    Map sessionsManager = (HashMap) application.getAttribute("Manager");
                    if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                        HttpSession user = (HttpSession) sessionsManager.get(c.getValue());
                        request.getServletContext().setAttribute("Mode", "Admin");
                        request.getRequestDispatcher("Home").forward(request, response);
                    } else {
                        c.setMaxAge(0);
                    }
                }
            }
        %>
        <div class="card">
            <h1>Log in</h1><br>
            <input type="text" name="user_name" id="admin_username" placeholder="Username">
            <br>
            <input type = "password" name="password" id="password" placeholder="Password">
            <br>
            <input type="submit" class="submit" value="Login" onclick="sendajax()">
        </div>
    </body>
</html>