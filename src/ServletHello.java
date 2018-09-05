import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletHello extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 헤더는 PrintWriter 위에 작성해야 한다.
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<h1>하이</h1>");
		out.println("Served at: " + request.getContextPath());
	}
}
