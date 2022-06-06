package test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.CommentsDao;
import test.dto.Comments;

@SuppressWarnings("serial")
@WebServlet("/comments/insert")
public class InsertCommentsController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String id = request.getParameter("id");
		String comments = request.getParameter("comments");
		
		CommentsDao dao = CommentsDao.getInstance();
		int n = dao.insert(new Comments(0, bnum, id, comments));
		
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n > 0) {
			pw.print("<code>success</code>");
		}else {
			pw.print("<code>fail</code>");
		}
		pw.print("</result>");
	}
}
