<div style="text-align: center;">
<h1 style="color:dimgray;">Reset Password</h1>
	<form action="/portal/resetPassword" method="post"
		modelAttribute="userDTO">
		<%-- <input type="hidden" name="resetPasswordToken" value="<%=request.getAttribute("token")%>" /> --%>
		<br>
		<input type="password" name="password" id="password" placeholder="Enter your new password" required autofocus />
		<br>
		<input type="password" placeholder="Confirm your new password" required oninput="checkPasswordMatch(this);" />
        <br>
		<input type="submit" value="Change Password" />
	</form>
<br><br>
	<form action="/portal/register" method="get">
		<input type="submit" value="Don't have an account yet? Register here!" style="background-color:darkblue;"/>
	</form>

	<form action="/portal/login" method="get">
		<input type="submit" value="Go to Login page" style="background-color:darkblue;"/>
	</form>
</div>

<script>
function checkPasswordMatch(fieldConfirmPassword) {
    if (fieldConfirmPassword.value != $("#password").val()) {
        fieldConfirmPassword.setCustomValidity("Passwords do not match!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}
</script>