<%-- 
    Document   : SingleUser
    Created on : Dec 16, 2017, 2:11:15 AM
    Author     : OmarRamzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message to single user</title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
        <script src="jquery-3.2.1.min.js"></script>
        
          <script>
            function checkForm(form)
            {
                
                if (form.MessageContent.value === "") {
                    alert("Error: Message cannot be blank!");
                    form.MessageContent.focus();
                    return false;
                }

                
        
        if (form.UserName.value === "") {
                    alert("Error: Username cannot be blank!");
                    form.UserName.focus();
                    return false;
                }

                return true;
            }
            </script>
    </head>
    <body>
        
        <br><br>
        
        <div class="container">
            
            <form onsubmit= "return checkForm(this);" action="MessageSingleUser" method="post">
                <h3>Message <input type="text" name="MessageContent" placeholder="Enter Message Here"></h3>
                <br>
                <h3>User Name of reciever <input type="text" name="UserName" placeholder="Enter User Name" ></h3>
                <br>
                <input type="submit" class="submit" value="Send">

            </form>
        </div>
        
    </body>
</html>
