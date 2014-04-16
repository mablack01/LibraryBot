package com;

import java.io.Serializable;

/**
 * User class, handles the user settings
 * @author Miles Black
 * April 15, 2014
 */

public class User implements Serializable {
	
	private static final long serialVersionUID = 5647469103804189021L;
	
	/**
	 * Fields
	 */
	private String email;
	private String password;
	private int period;
	
	/**
	 * Default Constructor
	 */
	public User() {

	}
	
	/**
	 * User Constructor
	 * @param email Assigns the given email to the user.
	 * @param password Assigns the given password to the user.
	 * @param period Indictates the period that the user is signing up for.
	 */
	public User(String email, String password, int period) {
		this.email = email;
		this.password = password;
		this.period = period;
	}
	
	/**
	 * Gets the user's email
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the user's password
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the user's period
	 * @return user's period
	 */
	public int getPeriod() {
		return period;
	}
	
	/**
	 * Sets the user's email
	 * @param email The email that is assigned to the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Sets the user's password
	 * @param password The password that is assigned to the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the user's period
	 * @param period The period that is assigned to the user
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

}
