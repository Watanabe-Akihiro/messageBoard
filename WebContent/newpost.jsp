<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>

<script type="text/javascript">

</script>
</head>
<body>
<div class = "main-contents">
<h1>新規投稿</h1>
<c:if test = "${not empty errorMessages}">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${errorMessages}" var = "message">
				<li><c:out value = "${message}"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>

	<div class = "form-area">
		<form action = "newPost" method = "post">

		<label for = "title">件名(50字以内):</label>
		<input name = "title" value = "${leftTitle}"><br/>

		<label for = "text">本文(1000字以内):</label>
		<textarea name = "text"  cols ="50" rows = "10" class = "post-box"><c:out value = "${leftText}"/></textarea><br/>

		<label for = "newCategory">カテゴリを追加:</label>
		<input name = "newCategory">（10字以内）

		<label for = "category">カテゴリを選ぶ:</label>
		<select name="category" size = "1">
				<option value = ""></option>
				<c:forEach items = "${selectCategories}" var = "category">
				<option value="${category}"><c:out value = "${category}"/></option>
			</c:forEach>
		</select><br/>


		<input type = "submit" value = "投稿">

		</form>
	</div>
<a href = "./">戻る</a>
</div>
</body>
</html>