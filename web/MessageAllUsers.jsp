<%-- 
    Document   : MessageAllUsers
    Created on : Dec 16, 2017, 3:17:43 AM
    Author     : OmarRamzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message All Users</title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
        <script src="jquery-3.2.1.min.js"></script>
        
         <script>
            function checkForm(form)
            {
                if (form.MessageContent.value == "") {
                    alert("Error: Message cannot be blank!");
                    form.MessageContent.focus();
                    return false;
                }

                return true;
            }
            </script>
    </head>
    <body>
        
        <br><br>
        
        <div class="container">
            <form onsubmit= "return checkForm(this);" action="MessageAll" method="post">
                <h3>Message <input type="text" name="MessageContent" placeholder="Enter Message Here"></h3>
            <br>
            <input type="submit" class="submit" value="Send">
            </form>
        </div>

   
    </body>
</html>
