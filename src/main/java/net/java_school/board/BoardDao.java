package net.java_school.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import net.java_school.db.dbpool.OracleConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDao {
	Logger log = LoggerFactory.getLogger(BoardDao.class);
	
	private OracleConnectionManager dbmgr;

	public BoardDao() {}

	public BoardDao(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}

	private Connection getConnection() throws SQLException {
		return dbmgr.getConnection();
	}

	private void close(ResultSet rs, PreparedStatement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Article> getArticleList(String keyword, int startRecord, int endRecord ) {
		List<Article> list = new ArrayList<Article>();
		String sql = null;

		sql = "SELECT articleno,indent,parent,title,regdate " +
				"FROM (SELECT ROWNUM R, A.* FROM (" +
				"SELECT articleno,level as indent,parent,title,regdate FROM hierarchy_article ";
		if (keyword != null && !keyword.equals("")) {
			sql += " WHERE title LIKE '%" + keyword + "%' OR content LIKE '%" + keyword + "%' ";
		}	
		sql += "START WITH parent = 0 " +
				"CONNECT BY PRIOR articleno = parent " + 
				"ORDER SIBLINGS BY articleno DESC) A) " + 
				"WHERE R BETWEEN ? AND ?";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, startRecord);
			stmt.setInt(2, endRecord);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Article article = new Article();
				article.setArticleNo(rs.getInt("articleno"));
				article.setTitle(rs.getString("title"));
				article.setRegdate(rs.getDate("regdate"));
				article.setIndent(rs.getInt("indent"));
				list.add(article);
			}
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.getArticleList() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(rs, stmt, con);
		}

		return list;
	}

	public int getTotalRecord(String keyword) {
		int totalRecord = 0;
		String sql = null;

		if (keyword == null || keyword.equals("")) {
			sql = "SELECT count(*) FROM hierarchy_article";
		} else {
			sql = "SELECT count(*) FROM hierarchy_article " +
					"WHERE title LIKE ? OR content LIKE ?";
		}

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			if (keyword != null && !keyword.equals("")) {
				keyword = "%" + keyword + "%";
				stmt.setString(1, keyword);
				stmt.setString(2, keyword);
			}
			rs = stmt.executeQuery();
			rs.next();
			totalRecord = rs.getInt(1);
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.getTotalRecord() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(rs, stmt, con);
		}

		return totalRecord;
	}

	public void insert(Article article) {
		String sql = "INSERT INTO hierarchy_article (articleno, title, content, regdate, parent) "
				+ "VALUES (seq_hierarchy_article.nextval, ?, ?, sysdate, 0)";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.insert() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(null, stmt, con);
		}
	}

	public Article selectOne(int no) {
		Article article = null;
		String sql = "SELECT articleno, title, content, regdate FROM hierarchy_article WHERE articleno = ?";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while (rs.next()) {
				article = new Article();
				article.setArticleNo(rs.getInt("articleno"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setRegdate(rs.getDate("regdate"));
			}
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.selectOne() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(rs, stmt, con);
		}

		return article;
	}

	public void update(Article article) {
		String sql = "UPDATE hierarchy_article SET title = ?, content = ? WHERE articleno = ?";        
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getContent());
			stmt.setInt(3, article.getArticleNo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.update() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(null, stmt, con);
		}
	}

	public void delete(int no) {
		String sql1 = "SELECT count(*) FROM hierarchy_article WHERE parent = ?";
		String sql2 = "DELETE FROM hierarchy_article WHERE articleno = ?";

		Connection con = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;

		boolean check = false;// if true, delete

		try {
			con = getConnection();
			stmt1 = con.prepareStatement(sql1);
			stmt1.setInt(1, no);
			rs = stmt1.executeQuery();
			rs.next();
			int num = rs.getInt(1);
			if (num == 0) {
				check = true;
			}
			if (check == true) {
				stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, no);
				stmt2.executeUpdate();
			}
		} catch (SQLException e) {
			log.debug("Error Source : BoardDao.delete() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql2);
		} finally {
			if (stmt2 != null) {
				try {
					stmt2.close();
				} catch (SQLException e) {}
			}
			close(rs, stmt1, con);
		}
	}

	public void reply(Article article) {
		String sql = "INSERT INTO hierarchy_article " + 
				"(articleno, parent, title, content, regdate) " + 
				"VALUES (seq_hierarchy_article.nextval, ?, ?, ?, sysdate)";

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, article.getParent());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.debug("Error Source:BoardDao.reply() : SQLException");
			log.debug("SQLState : {}", e.getSQLState());
			log.debug("Message : {}", e.getMessage());
			log.debug("Oracle Error Code : {}", e.getErrorCode());
			log.debug("sql : {}", sql);
		} finally {
			close(null,stmt,con);	
		}
	}	
}