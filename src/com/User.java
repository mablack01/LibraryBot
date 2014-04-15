package com;
import java.io.Serializable;


public class User implements Serializable {
	
	private static final long serialVersionUID = 5647469103804189021L;
	
	private String email;
	private String password;
	private int period;
	
	public User() {
		this.email = "name@email.com";
		this.password = "password";
		this.period = 1;
	}
	
	public User(String email, String password, int period) {
		this.email = email;
		this.password = password;
		this.period = period;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPeriod(int period) {
		this.period = period;
	}

}
