<%-- 
    Document   : Home
    Created on : Dec 13, 2017, 6:06:50 PM
    Author     : ahmed
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String Mode = (String) application.getAttribute("Mode");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Home</title>
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
                $(".suspend").click(function () {
                    sendajax(this.id, 1);
                    this.style.visibility = "hidden";
                });
                $(".close").click(function () {
                    sendajax(this.id, 2);
                });
            });

            function sendajax(surveyname, type) {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "Home", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                var data;
                if (type === 1) {  //suspend
                    data = "suspend-" + surveyname;
                } else {          //close
                    data = "close-" + surveyname;
                }
                xmlhttp.send("data=" + data);
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        if (type === 1) {
                            document.getElementById(surveyname).style.backgroundColor = "#666";
                        } else {
                            document.getElementById(surveyname).style.visibility = "hidden";
                        }
                    }
                }
            }
        </script>
    </head>
    <body>
        <div id="nav"></div>
        <%
            Vector<Map> surveys = (Vector<Map>) request.getAttribute("surveys");
            String username = (String) request.getAttribute("username");
            for (int i = 0; i < surveys.size(); i++) {
                String suspended = (String) surveys.get(i).get("issuspended");
                String closed = (String) surveys.get(i).get("isclosed");
                String id;
                if (closed.equals("1")) {
                    continue;
                }
                if (suspended.equals("0")) {
                    String clas = "";
                    if (!surveys.get(i).get("username").equals(username)) {
                        clas = "Admin";
                    }
                    out.println("<div id='" + surveys.elementAt(i).get("surveyname") + "' class='survey normal'><a href='ShowSurvey?surveyID="
                            + surveys.elementAt(i).get("surveyid") + "'><h4><b>" + surveys.elementAt(i).get("surveyname")
                            + "</b></h4></a><p class='created'>BY." + surveys.elementAt(i).get("username")
                            + "</p><pre class='" + clas + "'><Button id='" + surveys.elementAt(i).get("surveyname") + "' class='close'>Close</Button>  <Button id='" + surveys.elementAt(i).get("surveyname") + "' class='suspend'> Suspend </Button> </pre></div>"
                    );
                } else {
                    String clas = "";
                    if (!surveys.get(i).get("username").equals(username)) {
                        clas = "Admin";
                    }
                    out.println("<div id='" + surveys.elementAt(i).get("surveyname") + "' class='survey suspended'><a href='ShowSurvey?surveyID="
                            + surveys.elementAt(i).get("surveyid") + "'><h4><b>" + surveys.elementAt(i).get("surveyname")
                            + "</b></h4></a><p class='created'>BY." + surveys.elementAt(i).get("username")
                            + "</p><pre class='" + clas + "'><Button id='" + surveys.elementAt(i).get("surveyname") + "' class='close'>Close</Button></pre></div>"
                    );
                }

            }
        %>
    </body>
</html>