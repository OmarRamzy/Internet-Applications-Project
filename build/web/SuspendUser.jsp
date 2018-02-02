<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<html>
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
            var username = document.getElementById("user_name").value;
            if (username === "") {
                alert("Username filed cannot be blank!");
                return;
            }
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "suspend_user", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("user_name=" + username);
            xmlhttp.onreadystatechange = function ()
            {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    alert(xmlhttp.responseText);
                    document.getElementById("user_name").value = '';
                }
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Suspend User</title>
        <link rel="stylesheet" href="css/s.css">
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
            <h1>Suspend User</h1><br>
            <input type="text" id="user_name" placeholder="Username">
            <br>
            <input type="button" class="submit" id="user_name" value="Suspend" onclick="sendajax()">
        </div>
    </body>
</html>