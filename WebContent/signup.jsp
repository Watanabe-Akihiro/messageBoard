<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored = "false" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<div class = "main-contents">
<h1>新規ユーザー登録</h1>
<c:if test = "${not empty errorMessages}">
	<div class ="errorMessages">
		<ul>
		<c:forEach items = "${errorMessages}" var = "message">
		<li><c:out value = "${message}"/>
		</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
	</c:if>

	<form action = "signup" method = "post"><br/>

	<label for = "loginId">ログインID(半角英数字6～20字)</label><br/>
	<input name = "loginId" value = "${signupUser.loginId}" /><br/>

	<label for = "password">パスワード(記号含む半角文字6～255字)</label><br/>
	<input name = "password" type = "password"/><br/>

	<label for = "passwordConfirmation">パスワードの確認</label><br/>
	<input name = "passwordConfirmation" type = "password"/><br/>

	<label for = "name">名前(10字以内)</label><br/>
	<input name = "name" value = "${signupUser.name }"/><br/>

	<label for = "branchId">支店</label><br/>
	<select name="branchId" size = "1">
		<c:forEach items = "${branches}" var = "branch">
			<option value="${branch.id}" <c:if test = "${signupUser.branchId == branch.id}">selected</c:if>><c:out value = "${branch.name}"/></option>
		</c:forEach>
	</select><br/>

	<label for = "departmentId">部署</label><br/>
	<select name="departmentId" size = "1">
		<c:forEach items = "${departments}" var = "department">
			<option value="${department.id}"<c:if test = "${signupUser.departmentId == department.id}">selected</c:if>><c:out value = "${department.name}"/></option>
		</c:forEach>
	</select><br/>

	<div class ="register"><input type = "submit" value = "登録"/></div><br/>
	<a href = "admin">戻る</a>

</form>

</div>
</body>
</html>