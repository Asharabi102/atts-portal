<%
String timeout = (String) request.getAttribute("timeout");
%>
<div style="text-align: center;">
<h1>Welcome</h1>
<h2>This is a static home page</h2>
<h3>Nothing special here</h3>
<p>If no activity, session will expire in <%=timeout%></p>
<br><br>
	<form action="/portal/register" method="get">
		<input type="submit" value="Don't have an account yet? Register here!" style="background-color:darkblue;"/>
	</form>

	<form action="/portal/login" method="get">
		<input type="submit" value="Go to Login page" style="background-color:darkblue;"/>
	</form>
</div>