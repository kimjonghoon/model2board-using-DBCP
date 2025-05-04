package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.BoardService;
import net.java_school.db.dbpool.OracleConnectionManager;

public class DeleteAction implements Action {

	private OracleConnectionManager dbmgr;
	
	public DeleteAction(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		ActionForward forward = new ActionForward();
		
		int no = Integer.parseInt(req.getParameter("no"));
		String curPage = req.getParameter("curPage");
		String keyword = req.getParameter("keyword");
		if (keyword == null) keyword = "";
		
		BoardService service = new BoardService(dbmgr);
		service.deleteArticle(no);
		
		keyword = URLEncoder.encode(keyword, "UTF-8");
		
		forward.setView("/board/list.do?curPage=" + curPage + "&keyword=" + keyword);
		forward.setRedirect(true);
		
		return forward;
	}

}
