<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function goDeletePostServlet(){
	if(window.confirm('本当に削除しますか？')){
		location.href = "./";
	} else {
		return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
</head>
<body>
<div class = "main-contents">
	<div class = "header">
	<c:if test = "${empty loginUser}">
	<a href = "login">ログイン</a>
	</c:if>
	<c:if test = "${not empty loginUser}">
	<a href = "newPost">新規投稿</a>
	<a href = "admin">ユーザー管理（本社総務部専用）</a>
	</c:if>
	</div>

	<div class = "posts">
		<c:forEach items = "${posts}" var = "post">
			<div class = "post">
				<div class = "name">投稿者：<c:out value = "${post.name}"/></div>
				<div class = "title">件名：<c:out value = "${post.title}"/></div>
				<div class = "text">本文：<c:out value = "${post.text}"/></div>
				<div class = "category">カテゴリー：<c:out value = "${post.category}"/></div>
				<div class = "date">投稿日時：<fmt:formatDate value = "${post.createdAt}" pattern = "yyyy/MM/dd HH:mm:ss"/></div>
				<form action = "deletePost" method = "post" onSubmit = "return goDeletePostServlet()">
				<input type ="hidden" name = "deletedId" value = "${post.id}">
				<input type = "submit" value = "削除" >
				</form>
			</div>
		<div class = "comments-form">
			<form action = "comment" method = "post">

				<input type ="hidden" name = "postId" value = "${post.id}">
				<textarea name = "text" cols ="50" rows = "5" class = "comment-box"></textarea><br/>
				<input type = "submit" value = "コメント">
			</form>
		</div>
		</c:forEach>
	</div>

</div>
</body>
</html>