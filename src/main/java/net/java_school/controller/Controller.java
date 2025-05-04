package net.java_school.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.action.DeleteAction;
import net.java_school.board.action.ListAction;
import net.java_school.board.action.ModifyAction;
import net.java_school.board.action.ModifyFormAction;
import net.java_school.board.action.ReplyAction;
import net.java_school.board.action.ReplyFormAction;
import net.java_school.board.action.ViewAction;
import net.java_school.board.action.WriteAction;
import net.java_school.db.dbpool.OracleConnectionManager;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -904167838225890202L;

	private OracleConnectionManager dbmgr;
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = this.getServletContext();
		dbmgr = (OracleConnectionManager) sc.getAttribute("dbmgr");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(contextPath.length());

		String view = null;
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/board/list.do") && req.getMethod().equals("GET")) {
			action = new ListAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/view.do") && req.getMethod().equals("GET")) {
			action = new ViewAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/write.do") && req.getMethod().equals("GET")) {
			forward = new ActionForward();
			String curPage = req.getParameter("curPage");
			String keyword = req.getParameter("keyword");
			forward.setView("/board/write.jsp?curPage=" + curPage + "&keyword=" + keyword);
		} else if (command.equals("/board/write.do") && req.getMethod().equals("POST")) {
			action = new WriteAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/modify.do") && req.getMethod().equals("GET")) {
			action = new ModifyFormAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/modify.do") && req.getMethod().equals("POST")) {
			action = new ModifyAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/del.do") && req.getMethod().equals("POST")) {
			action = new DeleteAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/reply.do") && req.getMethod().equals("GET")) {
			action = new ReplyFormAction(dbmgr);
			forward = action.execute(req, resp);
		} else if (command.equals("/board/reply.do") && req.getMethod().equals("POST")) {
			action = new ReplyAction(dbmgr);
			forward = action.execute(req, resp);
		}

		view = forward.getView();

		if (forward.isRedirect() == false) {
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(view);
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(view);
		}

	}

}
