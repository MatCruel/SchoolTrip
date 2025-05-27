package com.example.schooltrip.modelDTO;

public class LoginDataDTO {
	private String username;
	private String psw;
	
	public LoginDataDTO() {}
	
	public LoginDataDTO(String username, String psw) {
		super();
		this.username = username;
		this.psw = psw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	@Override
	public String toString() {
		return "LogData [username=" + username + ", psw=" + psw + "]";
	}
}
