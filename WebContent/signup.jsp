<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored = "false" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<div class = "main contents">
<c:if test = "${not empty errorMassages}">
	<div class ="errorMassages">
		<ul>
		<c:forEach items = "${errorMassages}" var = "message">
		<li><c:out value = "${message}"/>
		</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMassages" scope = "session"/>
	</c:if>

	<form action = "signup" method = "post"><br/>

	<label for = "loginId">ログインID</label>
	<input name = "loginId" value = "${signupUser.loginId}" />(半角英数字6～20字)<br/>

	<label for = "password">パスワード</label>
	<input name = "password"/>(記号含む半角文字6～255字)<br/>

	<label for = "passwordConfirmation">パスワードの確認</label>
	<input name = "passwordConfirmation"/><br/>

	<label for = "name">名前</label>
	<input name = "name" value = "${signupUser.name }"/>(10字以内)<br/>

	<label for = "branchId">支店</label>
	<select name="branchId" size = "1">
		<c:forEach items = "${branches}" var = "branch">
			<option value="${branch.id}"><c:out value = "${branch.name}"/></option>
		</c:forEach>
	</select><br/>

	<label for = "departmentId">部署</label>
	<select name="departmentId" size = "1">
		<c:forEach items = "${departments}" var = "department">
			<option value="${department.id}"><c:out value = "${department.name}"/></option>
		</c:forEach>
	</select><br/>

	<input type = "submit" value = "登録"/><br/>
	<a href = "admin">戻る</a>

</form>

</div>
</body>
</html>