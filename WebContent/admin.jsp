<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
<script type="text/javascript">
	function disp1(){

		if(window.confirm('停止しますか？')){
			location.href = "admin";
		} else {
			return false;
		}
	}
	function disp2(){

		if(window.confirm('復活しますか？')){
			location.href = "admin";
		} else {
			return false;
		}
	}

	function goDeleteServlet(){
		if(window.confirm('本当に削除しますか？')){
			location.href = "admin";
		} else {
			return false;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理ページ</title>
</head>
<body>
<div class = "main-contents">
<div class = "header">
<a href = "signup">新規ユーザー登録</a>
<a href = "./">戻る</a>
</div>
<h1>ユーザー管理</h1>
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
	<c:if test = "${empty errorMessages}">
		<div class ="validationMessage">
			<p><c:out value = "${validationMessage}"/></p>
		</div>
	<c:remove var = "validationMessage" scope = "session"/>
	</c:if>

<table border = "2" class = "table">

	<tr>
		<th>ログインID</th>
		<th>ユーザー名</th>
		<th>支店</th>
		<th>部署</th>
		<th>編集</th>
		<th>停止</th>
		<th>削除</th>
	</tr>

<c:forEach items = "${allUsers}" var = "user">
	<tr>
		<td><c:out value = "${user.loginId}"/></td>
		<td><c:out value = "${user.name}"/></td>
		<td><c:out value = "${user.branchId}"/></td>
		<td><c:out value = "${user.departmentId}"/></td>
		<td>
			<form action = "edit" method = "get">
			<input type ="hidden" name = "userId" value = "${user.id}">
			<input type="submit" value = "編集" onclick = "location.href = 'edit'">
			</form>
		</td>

		<td>

			<form action = "admin" method = "post">
				<input type ="hidden" name = "userId" value = "${user.id}">
				<c:if test = "${user.id != loginUser.id }">
				<c:if test = "${user.isActivated == 0}">
						<input type = "hidden" name = "isActivated" value = "1">
						<input type = "submit" value = "停止"   onclick = "return disp1()">
				</c:if>
				<c:if test = "${user.isActivated == 1}">
					<input type = "hidden" name = "isActivated" value = "0">
					<input type = "submit"  value = "復活"   onclick = "return disp2()">
				</c:if>
				</c:if>
			</form>


		</td>
		<td>
		<form action = "delete" method = "post" onSubmit = "return goDeleteServlet()">
				<input type ="hidden" name = "deletedId" value = "${user.id}">
				<c:if test = "${user.id != loginUser.id }">
				<input type = "submit" value = "削除" >
				</c:if>
		</form>
		</td>
	</tr>
</c:forEach>
</table>


</div>
</body>
</html>