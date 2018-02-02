
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Admin</title>
        <link rel="stylesheet" href="css/s.css">
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $.get("navigation.html", function (data) {
                    $("#nav").replaceWith(data);
                    var Mode = "<%= Mode%>";
                    if (Mode == "Admin") {
                        Mode = "User";
                    } else {
                        Mode = "Admin";
                    }
                    $("." + Mode).hide();
                });
            });
            function sendajax() {
                var user_name = document.getElementById("user_name").value;
                var password = document.getElementById("password").value;
                if (user_name === "") {
                    alert("Username filed cannot be blank!");
                    return;
                }
                if (password === "") {
                    alert("Password filed cannot be blank!");
                    return;
                }
                var data = user_name + "-" + password;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "Add_admin", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        alert(xmlhttp.responseText);
                        document.getElementById("user_name").value = '';
                        document.getElementById("password").value = '';
                    }
                };
            }
        </script>
    </head>
    <body>
        <%
            Cookie[] AllCookies = request.getCookies();
            boolean check = false;
            for (Cookie c : AllCookies) {
                if (c.getName().equals("AdminSession")) {
                    Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                    if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                        request.getServletContext().setAttribute("Mode", "Admin");
                        check = true;
                    } else {
                        c.setMaxAge(0);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }
            }
            if (!check) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        %>
        <div id="nav"></div>
        <div class="card">
            <h1>Add Admin</h1>
            <input type="text" id="user_name" placeholder="Username">
            <br>
            <input type = "password" id="password" placeholder="Password">
            <br>
            <input type="button" class ="submit" value="Add" onclick="sendajax()">
        </div>
    </body>
</html>
