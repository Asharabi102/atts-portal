
<div style="text-align: center;">
<h1 style="color:dimgray;">Register Now</h1>
	<form action="/portal/register" method="post"
		modelAttribute="userDTO">
		<br>
		<input type="text" name="username" placeholder="Enter username" required autofocus />
		<br>
		<input type="email" name="email" placeholder="Enter your e-mail" required autofocus />
		<br>
		<input type="password" placeholder="Enter password" name="password" required autofocus/>
		<br>
		<input type="submit" value="Register" />
	</form>
<br><br>
	<form action="/portal/login" method="get">
		<input type="submit" value="Already registered? Login here!" style="background-color:darkblue;"/>
	</form>
</div>