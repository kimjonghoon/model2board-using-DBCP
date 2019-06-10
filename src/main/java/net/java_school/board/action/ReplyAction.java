package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.db.dbpool.OracleConnectionManager;

public class ReplyAction implements Action {

	private OracleConnectionManager dbmgr;
	
	public ReplyAction(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		int no = Integer.parseInt(req.getParameter("no"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		String keyword = req.getParameter("keyword");
		keyword = URLEncoder.encode(keyword, "UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Article article = new Article();
		article.setParent(no);
		article.setTitle(title);
		article.setContent(content);

		BoardService service = new BoardService(dbmgr);
		service.replyArticle(article);
		
		forward.setView("list.do?curPage=" + curPage + "&keyword=" + keyword);
		forward.setRedirect(true);
		
		return forward;
	}

}
