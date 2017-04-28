<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class = "main-contents">

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
	<c:if test = "${empty errorMassages}">
		<div class ="validationMessage">
			<p><c:out value = "${validationMessage}"/></p>
		</div>
	<c:remove var = "validationMessage" scope = "session"/>
	</c:if>

<a href = "admin">戻る</a>

</div>


</body>
</html>