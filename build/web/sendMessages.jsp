<%-- 
    Document   : sendMessages
    Created on : Dec 15, 2017, 11:40:20 PM
    Author     : OmarRamzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Message Receiver</title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
        <script src="jquery-3.2.1.min.js"></script>
    </head>
    <body>
        
        <div class="container">
        <h1>Choose whether to send the message to specific user or to all users</h1><br><br>
        
        
        <form action="MessageSingleUser.jsp">
                
            <input type="submit" class="submit" value="Send Message to One User">
            
        </form>
        
        <br><br>
        
        <form action="MessageAllUsers.jsp">
 
            <input type="submit" class="submit" value="Send Message to ALL User">
            
        </form>
        
        </div>
        
        
        
    </body>
</html>
