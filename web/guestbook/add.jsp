<%@ page import="guestbook.GuestBookVO" %>
<%@ page import="guestbook.GuestBookDAO" %><%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-05
  Time: 오후 7:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String pwd = request.getParameter("pwd");
	String content = request.getParameter("content");

	GuestBookVO vo = new GuestBookVO();

	vo.setName(name);
	vo.setPwd(pwd);
	vo.setContent(content);

	GuestBookDAO dao = new GuestBookDAO();
	dao.insert(vo);

	response.sendRedirect("/guestbook");
%>
