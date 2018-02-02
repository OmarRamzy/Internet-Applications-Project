<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sign Up</title>
        <link rel="stylesheet" href="css/s.css">
        <script src="jquery-3.2.1.min.js"></script>
        <script>
            function sendajax() {
                var username = document.getElementById("username").value;
                var password = document.getElementById("password").value;
                var firstname = document.getElementById("firstname").value;
                var lastname = document.getElementById("lastname").value;
                var email = document.getElementById("email").value;
                if (username === "" || password === "" || firstname === "" || lastname === "" || email === "") {
                    alert("Please fill required data");
                    return;
                }
                var data = username + "-" + password + "-" + firstname + "-" + lastname + "-" + email;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "user_sign_up", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        if (xmlhttp.responseText == "Username is already exists") {
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
        <div class="card">
            <h1>Sign Up</h1><br>
            <input type="text" name="user_name" id="username" placeholder="Username">
            <br>
            <input type="password" name="password" id="password" placeholder="Password">
            <br>
            <input type="text" name ="FirstName" id="firstname" placeholder="First Name">
            <br>
            <input type="text" name="LastName" id="lastname" placeholder="Last Name">
            <br>
            <input type="email" name="EmailAddress" id="email" placeholder="Email">
            <br>
            <input type="submit" class="submit" value="Sign up" onclick="sendajax()">
            <div class="help">
                <a href="user_login.jsp">Have an account?</a>
            </div>
        </div>
    </body>
</html>