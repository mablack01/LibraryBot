package com;
import java.io.Serializable;


public class User implements Serializable {
	
	private static String email;
	private static String password;
	private static int period;
	
	public User(String email, String password, int period) {
		this.email = email;
		this.password = password;
		this.period = period;
	}
	
	

}
