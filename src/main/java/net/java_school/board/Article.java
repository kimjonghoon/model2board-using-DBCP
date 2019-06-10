package net.java_school.board;

import java.util.Date;

import net.java_school.commons.WebContants;

public class Article {
	private int articleNo;
	private String title;
	private String content;
	private Date regdate;
	private int parent;
	private int indent;

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		if (content == null) content = "";
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHtmlContent() {
		if (content == null) {
			return "";
		} else {
			return content.replaceAll(WebContants.lineSeparator.value, "<br />");
		}
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}
}