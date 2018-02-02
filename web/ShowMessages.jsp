<%-- 
    Document   : ShowMessages
    Created on : Dec 18, 2017, 5:57:35 PM
    Author     : OmarRamzy
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Messages</title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
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
        </script>
    </head>
    <body>
        <div id="nav"></div>
        <div class="leftalignedcont            ainer">
            <h1>Messages For you <%=request.getAttribute("UserName")%>:</h1><br>
            <% ArrayList<String> Messages = (ArrayList) request.getAttribute("Messages");
                for (int i = 0; i < Messages.size(); i++) {
                    out.println("<br>");
                    out.println("<h3><u>Message#" + i + "</u>: " + Messages.get(i) + "</h3>");
                    out.println("<hr>");
                }
            %>

        </div>


    </body>
</html>
