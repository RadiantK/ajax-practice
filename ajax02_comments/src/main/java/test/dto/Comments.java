package test.dto;

public class Comments {

	private int num;
	private int bnum;
	private String id;
	private String comments;
	
	public Comments() {}

	public Comments(int num, int bnum, String id, String comments) {
		super();
		this.num = num;
		this.bnum = bnum;
		this.id = id;
		this.comments = comments;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
