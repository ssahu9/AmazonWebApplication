package com.project.bean;

public class Admin {
	private String mail; // to store the mail address
	private String password;

	public Admin(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}

	public Admin() {

	}
	@Override
	public String toString() {
		return "Admin [mail=" + mail + ", password=" + password + "]";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
