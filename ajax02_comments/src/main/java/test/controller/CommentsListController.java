package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.CommentsDao;
import test.dto.Comments;

@SuppressWarnings("serial")
@WebServlet("/comments/list")
public class CommentsListController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		String page_ = request.getParameter("p");
		int page = 1;
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		CommentsDao dao = CommentsDao.getInstance();
		List<Comments> list = dao.selectList(bnum, page);
		int count = dao.getCount(bnum);
		int pageCount = (int) Math.ceil(count / 5.0);
		int startPage = ((page - 1) / 5 * 5) + 1; //시작페이지
		int endPage = startPage + 4;
		
		if(endPage > pageCount) endPage = pageCount;
		
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		for(Comments c : list) {
			pw.print("<list>");
			pw.print("<num>"+c.getNum()+"</num>");
			pw.print("<bnum>"+c.getBnum()+"</bnum>");
			pw.print("<id>"+c.getId()+"</id>");
			pw.print("<comments>"+c.getComments()+"</comments>");
			pw.print("</list>");
		}
		pw.print("<page>"+page+"</page>");
		pw.print("<pageCount>"+pageCount+"</pageCount>");
		pw.print("<startPage>"+startPage+"</startPage>");
		pw.print("<endPage>"+endPage+"</endPage>");
		
		pw.print("</result>");
	}
}