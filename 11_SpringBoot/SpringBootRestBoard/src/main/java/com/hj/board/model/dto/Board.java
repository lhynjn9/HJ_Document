package com.hj.board.model.dto;

public class Board {
	private int id;
	private String writer;
	private String title;
	private String content;
	// resultmap에서 맞춰줘야 함
	private int viewCnt;
	private String regDate;
	private String filename;
	private String fileUri;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileUri() {
		return fileUri;
	}
	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}
	
	@Override
	public String toString() {
		return "Board [id=" + id + ", writer=" + writer + ", title=" + title + ", content=" + content + ", viewCnt="
				+ viewCnt + ", regDate=" + regDate + ", filename=" + filename + ", fileUri=" + fileUri + "]";
	}
	
}
