package test.dto;

import java.sql.Date;

public class Member {
	
	private int num;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date regDate;
	
	public Member() {}

	public Member(int num, String id, String pwd, String name, String email, Date regDate) {
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.regDate = regDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
