package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;
import test.dto.Board;

@SuppressWarnings("serial")
@WebServlet("/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		
		BoardDao dao = BoardDao.getInstance();
		Board board = dao.selectOne(bnum);
		
		request.setAttribute("b", board);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}
}
