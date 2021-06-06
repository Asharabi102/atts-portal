
<%
String error = (String) request.getAttribute("error");
String success = (String) request.getAttribute("success");
%>
<div style="text-align: center;">
	<h1 style="color: dimgray;">Login</h1>
	<strong style="color: green;"><%=success != null ? success : ""%></strong> 
	<strong style="color: red;"><%=error != null ? error : ""%></strong>
	<form action="/portal/login" method="post" modelAttribute="requestDTO">
		<input type="email" id="username" placeholder="Enter Email" name="username">
			<br>
			<input type="password" id="password" placeholder="Password" name="password">
			<br>
		<input type="submit" value="Login" />
	</form>
	<br>
	<br>
	<form action="/portal/register" method="get">
		<input type="submit" value="Don't have an account yet? Register here!"
			style="background-color: darkblue;" />
	</form>

	<form action="/portal/forgot" method="get">
		<input type="submit" value="Forgot you password? Click here!"
			style="background-color: darkblue;" />
	</form>
</div>

