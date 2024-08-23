package atos.lms.exam.service;


@SuppressWarnings("serial")
public class TestBoardVO extends TestBoardMasterVO implements GeneralModel {
	
	private int boardNum;
	private String writer;
	private String title;
	private String content;
	private String regDate;
	private String atchFileId;
	
	
	
	@Override
	public String toString() {
		return "TestBoardVO [boardNum=" + boardNum + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", atchFileId=" + atchFileId + "]";
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	
	

}
