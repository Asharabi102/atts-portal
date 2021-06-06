<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
input[type=text] {
  width: 380px;
  padding: 12px 20px;
  margin: 8px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

input[type=email] {
  width: 380px;
  padding: 12px 20px;
  margin: 8px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

input[type=password] {
  width: 380px;
  padding: 12px 20px;
  margin: 8px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

label {
  width: 420px;
}

input[type=submit] {
  width: 420px;
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



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><tiles:insertAttribute name="title" /></title>

</head>
<body>

	<%-- <tiles:insertAttribute name="header" /> --%>
	<br />
	<tiles:insertAttribute name="body" />
	<br />
	<%-- <tiles:insertAttribute name="footer" /> --%>

</body>
</html>