<%-- 
    Document   : ChangePassword
    Created on : Dec 14, 2017, 12:33:25 AM
    Author     : ahmed
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Change Password</title>
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
                $("input[type=password]").keyup(function () {
                    if ($('#password').val() === $('#confirm_password').val()) {
                        $("#confirm_password").css({"border": "none"});
                        $("input[type=submit]").attr('disabled', false);
                        $("input[type=submit]").css({"background-color": "#4d90fe"});
                    } else {
                        $("#confirm_password").css({"border": "3px solid #F44336"});
                        $("input[type=submit]").attr('disabled', 'disabled');
                        $("input[type=submit]").css({"background-color": "#9E9E9E"});
                    }
                });
            });
            function sendajax() {
                var first_input;
                var Mode = "<%= Mode%>";
                if (Mode === "Admin") {
                    first_input = "0-" + document.getElementById("user_name").value;
                } else {
                    first_input = "1-" + document.getElementById("current_password").value;
                }
                var new_password = document.getElementById("password").value;
                if (first_input == "" || new_password == "") {
                    alert("Please fill required data");
                    return;
                }
                var data = first_input + "-" + new_password;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "ChangePassword", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        alert(xmlhttp.responseText);
                        document.getElementById("user_name").value = '';
                        document.getElementById("current_password").value = '';
                        document.getElementById("password").value = '';
                        document.getElementById("confirm_password").value = '';
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
                if (c.getName().equals("UserSession") || c.getName().equals("AdminSession")) {
                    Map sessionsManager = (HashMap) request.getServletContext().getAttribute("Manager");
                    if (sessionsManager != null && sessionsManager.get(c.getValue()) != null) {
                        if (c.getName().equals("AdminSession")) {
                            Mode = "Admin";
                        } else {
                            Mode = "User";
                        }
                        request.getServletContext().setAttribute("Mode", Mode);
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
            <h1>Change Password</h1><br>
            <input type="password" class="User" id="current_password" name="currentpassword" placeholder="Current Password">
            <br>
            <input type="text" class="Admin" id="user_name" name="user_name" placeholder="User Name">
            <br class="Admin">
            <input type="password" id="password" class="password" placeholder="New Password">
            <br>
            <input type="password" id="confirm_password" class="password" placeholder="Repeat Password">
            <br>
            <input type="submit" class="submit" value="Change" onclick="sendajax()">
        </div>
    </body>
</html>
