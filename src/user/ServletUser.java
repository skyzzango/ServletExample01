package user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class ServletUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		UserVO vo = new UserVO();
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPwd(request.getParameter("pwd"));

		UserDAO dao = new UserDAO();
		dao.insert(vo);

		response.sendRedirect("userForm.jsp");
	}
}
