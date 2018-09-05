<%@ page import="guestbook.GuestBookDAO" %><%--
  Created by IntelliJ IDEA.
  User: skyzz
  Date: 2018-09-05
  Time: 오후 7:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
	request.setCharacterEncoding("UTF-8");

	int no = Integer.parseInt(request.getParameter("no"));
	String inputPwd = request.getParameter("pwd");

	GuestBookDAO vo = new GuestBookDAO();
	String dbPwd = vo.getPwd(no);
	String parseInputPwd = vo.getInputPwd(inputPwd);

	if (dbPwd.equals(parseInputPwd)) {
		vo.delete(no);
	}
	
	response.sendRedirect("/guestbook");
%>