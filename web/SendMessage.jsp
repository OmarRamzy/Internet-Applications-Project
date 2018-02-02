<%-- 
    Document   : SendMessage
    Created on : Dec 18, 2017, 3:18:35 PM
    Author     : ahmed
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message</title>
        <link rel="stylesheet" href="css/s.css">
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $.get("navigation.html", function (data) {
                    $("#nav").replaceWith(data);
                    var Mode = "<%= Mode%>";
                    if (Mode === "Admin") {
                        Mode = "User";
                    } else {
                        Mode = "Admin";
                    }
                    $("." + Mode).hide();
                });
            });
            function sendajax() {
                var username = document.getElementById("user_name").value;
                var content = document.getElementById("content").value;
                if (username === "") {
                    alert("Username filed cannot be blank!");
                    return;
                }
                if (content === "") {
                    alert("Message filed cannot be blank!");
                    return;
                }
                var data = username + "-" + content;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "SendMessage", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        alert(xmlhttp.responseText);
                        document.getElementById("user_name").value = '';
                        document.getElementById("content").value = '';
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
            <h1>Send Message</h1>
            <input type="text" id="user_name" placeholder='Username or " All " to Broadcast'>
            <br>
            <textarea rows="8" id="content" placeholder="Message"></textarea>
            <br>
            <input type="button" class ="submit" value="Add" onclick="sendajax()">
        </div>
    </body>
</html>
