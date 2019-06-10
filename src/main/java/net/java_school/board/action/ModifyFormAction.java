package net.java_school.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.db.dbpool.OracleConnectionManager;

public class ModifyFormAction implements Action {

	private OracleConnectionManager dbmgr;
	
	public ModifyFormAction(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		BoardService service = new BoardService(dbmgr);
		Article article = service.getArticle(no);
		
		req.setAttribute("article", article);
		
		forward.setView("/board/modify.jsp");
		
		return forward;
	}

}
