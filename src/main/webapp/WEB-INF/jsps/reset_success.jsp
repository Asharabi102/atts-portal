<%
String error = (String) request.getAttribute("error");
String success = (String) request.getAttribute("success");
%>
<div style="text-align: center;">
<h2 style="color:green;"><%=success!=null?success:""%></h2>
<h2 style="color:red;"><%=error!=null?error:""%> </h2>
	<form action="/portal/login" method="get">
		<input type="submit" value="Go to Login page" style="background-color:darkblue;"/>
	</form>
</div>