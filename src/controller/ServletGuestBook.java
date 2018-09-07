package controller;

import guestbook.GuestBookDAO;
import guestbook.GuestBookVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gb")
public class ServletGuestBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		if ("deleteform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		} else if ("add".equals(actionName)) {
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String content = request.getParameter("content");

			GuestBookVO vo = new GuestBookVO();
			vo.setName(name);
			vo.setPwd(pwd);
			vo.setContent(content);

			GuestBookDAO dao = new GuestBookDAO();
			dao.insert(vo);

			response.sendRedirect("/gb");
		} else if ("delete".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String inputPwd = request.getParameter("pwd");

			GuestBookDAO vo = new GuestBookDAO();
			String dbPwd = vo.getPwd(no);
			String parseInputPwd = vo.getInputPwd(inputPwd);

			if (dbPwd.equals(parseInputPwd)) {
				vo.delete(no);
			}

			response.sendRedirect("/gb");
		} else {
			GuestBookDAO dao = new GuestBookDAO();
			List<GuestBookVO> list = dao.getList();

			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}
}
