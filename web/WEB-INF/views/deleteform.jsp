<%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-07
  Time: 오후 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String no = request.getParameter("no");
%>
<html lang="ko">
<head>
	<%@include file="../../partials/head.html" %>
	<title>방명록</title>
</head>
<body>
<%@include file="../../partials/nav.html" %>
<div class="starter-template" style="background-size: auto">
	<h3>방명록 삭제 화면</h3><br>
	<form action="gb" method="post">
		<input type="hidden" name="no" value="<%= no %>">
		<input type="hidden" name="a" value="delete">
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input type="password" class="form-control" name="pwd" id="pwd" placeholder="비밀번호 입력">
		</div>
		<button type="submit" class="btn btn-danger">삭제</button>
		<button type="button" class="btn btn-info" onclick="location.href='gb'">취소</button>
	</form>
</div>
</body>
</html>
