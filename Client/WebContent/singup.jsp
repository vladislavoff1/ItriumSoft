<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
</head>
<body>
<h3>Sign up</h3>

<p>
<a href="index.jsp">Main page</a>
</p>

<form name="form2" method="get" action="SignUp">
<table>
  <tr>
    <td>Email:</td>
    <td><input name="email" type="text" size="25" maxlength="30" value=""/></td>
  </tr>
  <tr>
    <td>Password:</td>
    <td><input name="password" type="password" size="25" maxlength="30" value=""/></td>
  </tr>
  <tr>
    <td>Confirm:</td>
    <td><input name="confirm" type="password" size="25" maxlength="30" value=""/></td>
  </tr>
</table>
<input type="submit" name="signup" value="Sign up" />
</form> 

</body>
</html>