<%@ page import="java.util.List" %>
<%@ page import="guestbook.GuestBookVO" %><%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-07
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	List<GuestBookVO> list = (List<GuestBookVO>) request.getAttribute("list");

%>
<html lang="ko">
<head>
	<%@include file="../../partials/head.html" %>
	<title>방명록</title>
</head>
<body>
<%@include file="../../partials/nav.html" %>
<div class="starter-template" style="background-size: auto">
	<h3>방명록 화면</h3><br>
	<form action="gb" method="post">
		<input type="hidden" name="a" value="add">
		<div class="form-group">
			<label for="name">작성자</label>
			<input type="text" class="form-control" name="name" id="name" placeholder="이름 입력">
		</div>
		<div class="form-group">
			<label for="pwd">비밀번호</label>
			<input type="password" class="form-control" name="pwd" id="pwd" placeholder="비밀번호 입력">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" name="content" id="content" rows="10"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">글쓰기</button>
	</form>
	<br>
	<table class="table">
		<thead>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
		</thead>
		<tbody>
	<%
		if (list != null) {
			for (GuestBookVO vo : list) { %>
		<tr>
			<td scope="row"><%= vo.getNo() %>
			</td>
			<td><%= vo.getName() %>
			</td>
			<td><%= vo.getReg_date() %>
			</td>
			<td><a class="btn btn-danger" href="gb?a=deleteform&no=<%= vo.getNo() %>" role="button">삭제</a></td>
		</tr>
	<% }
		}
	%>
		</tbody>
	</table>
</div>
</body>
</html>
