package test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.MemberDao;
import test.dto.Member;

@SuppressWarnings("serial")
@WebServlet("/check_id")
public class CheckIdController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		
		MemberDao dao = MemberDao.getInstance();
		Member member = dao.selectOne(id);
		
		// xml로 응답받기
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(member != null) {
			pw.print("<code>duplication</code>");
		}else {
			pw.print("<code>success</code>");
		}
		pw.print("</result>");
	}
}
