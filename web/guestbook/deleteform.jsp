<%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-05
  Time: 오후 7:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String no = request.getParameter("no");
%>
<html lang="ko">
<head>
	<%@include file="../partials/head.html" %>
	<title>방명록</title>
</head>
<body>
<%@include file="../partials/nav.html" %>
<div class="starter-template" style="background-size: auto">
	<h3>방명록 삭제</h3><br>
	<form action="/guestbook/delete.jsp" method="post">
		<input type="hidden" name="no" value="<%= no %>">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
				<td><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
	<a class="btn btn-primary" href="/guestbook" role="button">메인으로</a>
</div>
</body>
</html>
