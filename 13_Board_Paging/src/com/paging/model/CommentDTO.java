package com.paging.model;

public class CommentDTO {
	private int comment_no;
	private int comment_board_no;
	private String comment_writer;
	private String comment_cont;
	private String comment_pwd;
	private String comment_sysdate;
	
	public String getComment_sysdate() {
		return comment_sysdate;
	}
	public void setComment_sysdate(String comment_sysdate) {
		this.comment_sysdate = comment_sysdate;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getComment_board_no() {
		return comment_board_no;
	}
	public void setComment_board_no(int comment_board_no) {
		this.comment_board_no = comment_board_no;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment_cont() {
		return comment_cont;
	}
	public void setComment_cont(String comment_cont) {
		this.comment_cont = comment_cont;
	}
	public String getComment_pwd() {
		return comment_pwd;
	}
	public void setComment_pwd(String comment_pwd) {
		this.comment_pwd = comment_pwd;
	}
	
	
}
