<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
<script type="text/javascript">
function goDeletePostServlet(){
	if(window.confirm('本当に削除しますか？')){
		location.href = "deletePost";
	} else {
		return false;
	}
}

function goDeleteCommentServlet(){
	if(window.confirm('本当に削除しますか？')){
		location.href = "deletePost";
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
	<c:if test = "${not empty loginUser}">
	<a href = "newPost">新規投稿</a>
	<c:if test = "${loginUser.branchId == '1' && loginUser.departmentId == '1' }">
		<a href = "admin">ユーザー管理（本社総務部専用）</a>
	</c:if>
	<a href = "logout" class = "logout">ログアウト</a>
	<p class ="loggedIn"><c:out value = "${loginUser.name}"/> がログイン中</p>
	</c:if>
	</div>
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
	<h1>ホーム</h1>
	<form action="./" method = "Get">

		カテゴリ:<select name="category" size = "1">
		<c:if test = "${selectedCategory == null}">
			<option value = "" selected ></option>
			<c:forEach items = "${categories}" var = "category">
				<option value="${category}"><c:out value = "${category}"/></option>
			</c:forEach>
			</c:if>
		<c:if test = "${selectedCategory != null }">
			<option value = ""></option>
			<c:forEach items = "${categories}" var = "category">
				<option value="${category}" <c:if test = "${selectedCategory.equals(category)}">selected</c:if>><c:out value = "${category}"/></option>
			</c:forEach>
			</c:if>
		</select>

		日付:<input type = "date" name = "start" value = "${start}">から
		<input type = "date" name = "end" value = "${end}">まで

		<input type = "submit" value = "絞込み">
	</form>


<div class = "posts">
		<c:forEach items = "${posts}" var = "post">
			<c:if test = "${not empty commentError}">
			<div class = "commentError">
				<ul>
					<c:forEach items = "${commentError}" var = "message">
						<li><c:out value = "${message}"/>
					</c:forEach>
				</ul>
			</div>
			<c:remove var = "commentError" scope = "session"/>
		</c:if>
			<div class = "post">
				<div class = "name">投稿者：<c:out value = "${post.name}"/></div>
				<div class = "title">件名：<c:out value = "${post.title}"/></div>
				<div class = "text">
				<c:forEach var = "newLine"  items="${fn:split(post.text,'
							')}" >
							${newLine}<br/>
							</c:forEach></div>
				<div class = "category">カテゴリー：<c:out value = "${post.category}"/></div>
				<div class = "date">投稿日時：<fmt:formatDate value = "${post.createdAt}" pattern = "yyyy/MM/dd HH:mm:ss"/></div>

				<c:if test = "${(loginUser.departmentId).equals('2') || (loginUser.id).equals(post.userId)}">
					<form action = "deletePost" method = "post" onSubmit = "return goDeletePostServlet()">
						<input type ="hidden" name = "deletedId" value = "${post.id}">
						<input type = "submit" value = "削除" >
					</form>
				</c:if>
				<c:if test = "${(loginUser.departmentId).equals('3') && (post.departmentId).equals('4') && (loginUser.branchId).equals(post.branchId)}">
					<form action = "deletePost" method = "post" onSubmit = "return goDeletePostServlet()">
						<input type ="hidden" name = "deletedId" value = "${post.id}">
						<input type = "submit" value = "削除" >
					</form>
				</c:if>
			</div>

			<div class = "comments">
				<c:forEach items = "${comments}" var = "comment">
					<c:if test="${comment.postId == post.id}">
						<div class = "comment">
							<div class = "name">投稿者：<c:out value = "${comment.name}"/></div>

							<div class = "text">
							<c:forEach var = "newLine"  items="${fn:split(comment.text,'
							')}" >
							${newLine}<br/>
							</c:forEach>
							</div>
							<div class = "date">投稿日時：<fmt:formatDate value = "${comment.createdAt}" pattern = "yyyy/MM/dd HH:mm:ss"/></div>


					<c:if test = "${(loginUser.departmentId).equals('2') || (loginUser.id).equals(comment.userId)}">
						<form action = "deleteComment" method = "post" onSubmit = "return goDeleteCommentServlet()">
							<input type ="hidden" name = "deletedId" value = "${comment.id}">
							<input type = "submit" value = "削除" >
				</form>
				</c:if>
				<c:if test = "${(loginUser.departmentId).equals('3') && (comment.departmentId).equals('4') && (loginUser.branchId).equals(comment.branchId)}">
						<form action = "deleteComment" method = "post" onSubmit = "return goDeleteCommentServlet()">
							<input type ="hidden" name = "deletedId" value = "${comment.id}">
							<input type = "submit" value = "削除" >
				</form>
				</c:if>
				</div>
					</c:if>
				</c:forEach>
			</div>

		<div class = "comments-form">

			<form action = "comment" method = "post">
				<input type ="hidden" name = "postId" value = "${post.id}">
				<c:if test = "${leftComment.postId != post.id}">
				<textarea name = "text" cols ="50" rows = "5" class = "comment-box" id = "comment-box"></textarea><br/>
				</c:if>
				<c:if test = "${leftComment.postId == post.id}">
				<textarea name = "text" cols ="50" rows = "5"  class = "comment-box" id = "comment-box"><c:out value = "${leftComment.text}" /></textarea><br/>
				</c:if>
				<input type = "submit" value = "コメント">(500字以内)
			</form>

		</div>
		</c:forEach>
		<c:remove var = "leftComment" scope = "session"/>
	</div>

</div>
</body>
</html>