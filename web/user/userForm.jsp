<%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-05
  Time: 오후 5:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ko">
<head>
	<%@include file="../partials/head.html" %>
	<title>회원 가입</title>
</head>
<body>
<%@include file="../partials/nav.html" %>
<div class="starter-template" style="background-size: auto">
	<h3>정보 입력</h3><br>
	<form action="/add" method="get" style="width: 15rem">
		<div class="form-group">
			<label for="name">이름</label>
			<input type="text" class="form-control" name="name" id="name" aria-describedby="helpName"
			       placeholder="이름을 입력 하세요.">
			<small id="helpName" class="form-text text-muted">Help text</small>
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input type="text" class="form-control" name="email" id="email" aria-describedby="helpEmail"
			       placeholder="이메일을 입력 하세요.">
			<small id="helpEmail" class="form-text text-muted">Help text</small>
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input type="password"
			       class="form-control" name="pwd" id="pwd" aria-describedby="helpPassword" placeholder="비밀번호를 입력 하세요.">
			<small id="helpPassword" class="form-text text-muted">Help text</small>
		</div>
		<button type="submit" class="btn btn-primary">가입하기</button>
	</form>
</div>
</body>
</html>
