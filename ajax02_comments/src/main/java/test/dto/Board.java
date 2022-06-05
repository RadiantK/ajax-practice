package test.dto;

public class Board {
	private int bnum;
	private String writer;
	private String title;
	private String content;
	
	public Board() {}

	public Board(int bnum, String writer, String title, String content) {
		this.bnum = bnum;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
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

}
