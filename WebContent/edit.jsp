<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集ページ</title>
</head>
<body>
<div class= "main-contents">

<form action = "edit" method = "post"><br/>
	<input type ="hidden" name = "userId" value = "${editUser.id}">

	<label for = "loginId">ログインID</label>
	<input name = "loginId" value = "${editUser.loginId}" />(半角英数字6～20字)<br/>

	<label for = "password">パスワード</label>
	<input name = "password"/>(記号含む半角文字6～255字)<br/>

	<label for = "passwordConfirmation">パスワードの確認</label>
	<input name = "passwordConfirmation"/><br/>

	<label for = "name">名前</label>
	<input name = "name" value = "${editUser.name }"/>(10字以内)<br/>

	<label for = "branchId">支店</label>
	<input name = "branchId" value = "${editUser.branchId}" />(当ユーザーの所属する支店IDを入力してください)<br/>

	<label for = "departmentId">部署</label>
	<input name = "departmentId" value = "${editUser.departmentId }" >(当ユーザーの所属する部署IDを入力してください))<br/>

	<input type = "submit" value = "更新"/>

	<br/>
</form>

<a href = "admin">戻る</a>
</div>
</body>
</html>