<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集ページ</title>
</head>
<body>
<div class= "main-contents">
<h1>ユーザー編集</h1>
<form action = "edit" method = "post"><br/>
	<input type ="hidden" name = "userId" value = "${editUser.id}">

	<label for = "loginId">ログインID</label>
	<input name = "loginId" value = "${editUser.loginId}" />(半角英数字6～20字)<br/>

	<label for = "password">パスワード</label>
	<input name = "password" type = "password"/>(記号含む半角文字6～255字)<br/>

	<label for = "passwordConfirmation">パスワードの確認</label>
	<input name = "passwordConfirmation" type = "password"/><br/>

	<label for = "name">名前</label>
	<input name = "name" value = "${editUser.name }"/>(10字以内)<br/>

	<c:if test = "${editUser.id != loginUser.id }">
	<label for = "branchId">支店</label>
	<select name="branchId" size = "1">
		<c:forEach items = "${branches}" var = "branch">
			<option value="${branch.id}" <c:if test="${editBranch.id == branch.id}">selected</c:if>>
			<c:out value = "${branch.name}"/>
			</option>
		</c:forEach>
	</select><br/>


	<label for = "departmentId">部署</label>
	<select name="departmentId" size = "1">
		<c:forEach items = "${departments}" var = "department">
			<option value="${department.id}" <c:if test="${editDepartment.id == department.id}">selected</c:if>>
			<c:out value = "${department.name}"/></option>
		</c:forEach>
	</select><br/>
	</c:if>
	<c:if test = "${editUser.id == loginUser.id }">
	<input type ="hidden" name = "branchId" value = "${editUser.branchId}">
	<input type ="hidden" name = "departmentId" value = "${editUser.departmentId}">
	</c:if>

	<input type = "submit" value = "更新"/>

	<br/>
</form>

<a href = "admin">戻る</a>
</div>
</body>
</html>