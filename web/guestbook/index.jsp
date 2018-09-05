<%@ page import="java.util.List" %>
<%@ page import="guestbook.GuestBookVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="guestbook.GuestBookDAO" %><%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-05
  Time: 오후 7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	List<GuestBookVO> list = new ArrayList<>();
	GuestBookDAO dao = new GuestBookDAO();
	list = dao.getList();
%>
<html lang="ko">
<head>
	<%@include file="../partials/head.html" %>
	<title>방명록</title>
</head>
<body>
<%@include file="../partials/nav.html" %>
<div class="starter-template" style="background-size: auto">
	<h3>방명록 작성</h3><br>
	<form action="/guestbook/add.jsp" method="post">
		<table class="table" border="1">
			<tr>
				<td>이름</td>
				<td>
					<label>
						<input type="text" name="name">
					</label>
				</td>
				<td>비밀번호</td>
				<td>
					<label>
						<input type="password" name="pwd">
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<label>
						<textarea name="content" cols="60" rows="5"></textarea>
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<button type="submit" class="btn btn-primary">확인</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<table class="table" border="1">
		<thead>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>삭제</th>
		</tr>
		</thead>
		<tbody>
		<%
			if (list != null) {
				for (GuestBookVO vo : list) {
		%>
		<tr>
			<td scope="row"><%= vo.getNo() %>
			</td>
			<td><%= vo.getName() %>
			</td>
			<td><%= vo.getRegDate() %>
			</td>
			<td>
				<a class="btn btn-danger" href="/guestbook/deleteform.jsp?no=<%= vo.getNo() %>" role="button">삭제</a>
			</td>
		</tr>
		<%
				}
			}
		%>
		</tbody>
	</table>
</div>
</body>
</html>
