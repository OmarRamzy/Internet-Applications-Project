<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <%
            class admin {

                String un, pass;
            }
            if (request.getAttribute("CurrentAdminObjct") != null) {
                admin obj = new admin();
                obj = (admin) request.getAttribute("CurrentAdminObjct");
                System.out.println(obj.un);
                System.out.println(obj.pass);
                System.out.println("yes");
            }
        %>
        <form action="Add_Admin.jsp">
            <input type="submit" value="Add Admin"> 
        </form>
        <form action="suspend_user.jsp">
            <input type="submit" value="suspend user"> 
        </form>
        <form action="change_password_for_user.jsp">
            <input type="submit" value="change passwor for user"> 
        </form>
    </body>
</html>