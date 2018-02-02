<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Text Free</title>
<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

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
<form action="add_text_free_question" method="post">

<label>enter the question: </label>
<input type="text" name="question">
<br>
<input type="hidden" value=<%=request.getParameter("surveyID")%> name ="surveyID">
<input type="hidden" value=<%=request.getParameter("Q_num")%> name ="Q_num">
<input type="radio" name="is_mondatory" value="0"> optional<br>
<input type="radio" name="is_mondatory" value="1"> mandatory<br>
<input type="submit" value="submit">
</form>

</div>
</body>
</html>