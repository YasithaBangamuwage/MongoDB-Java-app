/**
 * 
 */
package com.yas.mongodbjavaapp.domain;

/**
 * @author ybangamuwage
 *
 */
public class User {

	/**
	 * user id.
	 */
	private int id;
	/**
	 * user first name.
	 */
	private String firstName;
	/**
	 * user last name.
	 */
	private String lastName;

	/**
	 * user email address.
	 */
	private String email;

	/**
	 * default constructor.
	 */
	public User() {
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param pId
	 *            the id to set
	 */
	public final void setId(final int pId) {
		this.id = pId;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param pFirstName
	 *            the firstName to set
	 */
	public final void setFirstName(final String pFirstName) {
		this.firstName = pFirstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param pLastName
	 *            the lastName to set
	 */
	public final void setLastName(final String pLastName) {
		this.lastName = pLastName;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param pEmail
	 *            the email to set
	 */
	public final void setEmail(final String pEmail) {
		this.email = pEmail;
	}

}
