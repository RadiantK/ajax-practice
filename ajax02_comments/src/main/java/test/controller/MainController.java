package test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;
import test.dto.Board;

@SuppressWarnings("serial")
@WebServlet("/main")
public class MainController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDao dao = BoardDao.getInstance();
		List<Board> list = dao.selectList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
