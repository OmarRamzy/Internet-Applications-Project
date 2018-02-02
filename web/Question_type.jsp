<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Type</title>
<style>
input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>
</head>
<body>
<div>
<font size="20">what is the answer type of the next question?</font>

<form action="text_free.jsp">
	<%
	if(request.getAttribute("surveyID")==null){
	%>
		<input type="hidden" value=<%=request.getParameter("surveyID")%> name="surveyID">
		<input type="hidden" value=<%=request.getParameter("Q_num")%> name="Q_num">
	<%
	}
	else{
	%>
		<input type="hidden" value=<%=request.getAttribute("surveyID")%> name="surveyID">
		<input type="hidden" value="1" name="Q_num">
	<%
	}
	%>
 		<input type="submit" value="text free">
</form>
<form action="choice_question.jsp">
	<%
	if(request.getAttribute("surveyID")==null){
	%>
		<input type="hidden" value=<%=request.getParameter("surveyID")%> name="surveyID">
		<input type="hidden" value=<%=request.getParameter("Q_num")%> name="Q_num">
	<%
	}
	else{
	%>
		<input type="hidden" value=<%=request.getAttribute("surveyID")%> name="surveyID">
		<input type="hidden" value="1" name="Q_num">
	<%
	}
	%>
 <input type="submit" value="choice" name="typeOfQuestion">
 <br>
 <input type="submit" value="check box" name="typeOfQuestion">
</form>
</div>
</body>
</html>