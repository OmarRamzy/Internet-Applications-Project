<%-- 
    Document   : ShowReports
    Created on : Dec 18, 2017, 10:17:08 PM
    Author     : OmarRamzy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="survey_website.ReportInformation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String Mode = (String) application.getAttribute("Mode");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Reports</title>
        <link rel="stylesheet" href="css/s.css">
        <link rel="stylesheet" href="css/survey.css">
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
        </script>
    </head>
    <body>
        <div id="nav"></div>
        <div class="leftalignedcontainer">
            <h1><u> Reports received: </u></h1>

            <%
                ArrayList<ReportInformation> Reports = (ArrayList) request.getAttribute("Reports");

                if (Reports != null) {
                    for (int i = 0; i < Reports.size(); i++) {
                        out.println("<h3>Report Text: " + Reports.get(i).Text + "</h3>");
                        out.println("<h3><a href='ShowSurvey?surveyID=" + Reports.get(i).ID + "'>Show this survey...</a></h3>");
                        out.println("<br><hr><br>");

                    }
                }

            %>
        </div>



    </body>
</html>
