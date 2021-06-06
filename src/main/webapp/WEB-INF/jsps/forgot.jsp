
<div style="text-align: center;">
<h1 style="color:dimgray;">Forgot your password?</h1>
	<form action="/portal/resetPassword" method="get"
		modelAttribute="userDTO">
		<!-- <p>We will be sending a reset password link to your email.</p> -->
		
		<br>
		<input type="email" name="email" placeholder="Enter your e-mail" required autofocus />
		<br>
		<input type="submit" value="Send" />
	</form>
<br><br>
	<form action="/portal/register" method="get">
		<input type="submit" value="Don't have an account yet? Register here!" style="background-color:darkblue;"/>
	</form>

	<form action="/portal/login" method="get">
		<input type="submit" value="Go to Login page" style="background-color:darkblue;"/>
	</form>
</div>