package com.hj.board.model.dto;

public class Board {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int viewCnt;
	
	public Board() {
		
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getviewCnt() {
		return viewCnt;
	}
	public void setviewCnt(int cnt) {
		this.viewCnt = viewCnt;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}