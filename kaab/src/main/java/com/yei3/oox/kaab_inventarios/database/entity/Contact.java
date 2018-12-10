package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class Contact implements Entity{
	private int id;
	private int companyID;
	private String names;
	private String lastname;
	private String middlename;
	private String position;
	private String phoneType;
	private String phoneNumber;
	private String phoneType2;
	private String phoneNumber2;
	private String emailType;
	private String email;
	private String emailType2;
	private String email2;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "CONTACTS";
	
	private String[] columns = {
			"id",
			"companyID",
			"names",
			"lastname",
			"middlename",
			"position",
			"phoneType",
			"phoneNumber",
			"phoneType2",
			"phoneNumber2",
			"emailType",
			"email",
			"emailType2",
			"email2",
			"statusID",
			"creationDateTime",
			"lastModDateTime",
			"deleteDateTime",
			"creationUserID",
			"lastModUserID",
			"deleteUserID"
			};
	
	private Class[] types = {
			int.class,
			int.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", companyID=" + companyID + ", names=" + names + ", lastname=" + lastname
				+ ", middlename=" + middlename + ", position=" + position + ", phoneType=" + phoneType
				+ ", phoneNumber=" + phoneNumber + ", phoneType2=" + phoneType2 + ", phoneNumber2=" + phoneNumber2
				+ ", emailType=" + emailType + ", email=" + email + ", emailType2=" + emailType2 + ", email2=" + email2
				+ ", statusID=" + statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime="
				+ lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID
				+ ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public Contact(int companyID, String names, String lastname, String middlename, String position, String phoneType,
			String phoneNumber, String phoneType2, String phoneNumber2, String emailType, String email,
			String emailType2, String email2, int statusID) {
		this.companyID = companyID;
		this.names = names;
		this.lastname = lastname;
		this.middlename = middlename;
		this.position = position;
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
		this.phoneType2 = phoneType2;
		this.phoneNumber2 = phoneNumber2;
		this.emailType = emailType;
		this.email = email;
		this.emailType2 = emailType2;
		this.email2 = email2;
		this.statusID = statusID;
	}
	
	public Contact() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType2() {
		return phoneType2;
	}
	public void setPhoneType2(String phoneType2) {
		this.phoneType2 = phoneType2;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailType2() {
		return emailType2;
	}
	public void setEmailType2(String emailType2) {
		this.emailType2 = emailType2;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public Timestamp getLastModDateTime() {
		return lastModDateTime;
	}
	public void setLastModDateTime(Timestamp lastModDateTime) {
		this.lastModDateTime = lastModDateTime;
	}
	public Timestamp getDeleteDateTime() {
		return deleteDateTime;
	}
	public void setDeleteDateTime(Timestamp deleteDateTime) {
		this.deleteDateTime = deleteDateTime;
	}
	public int getCreationUserID() {
		return creationUserID;
	}
	public void setCreationUserID(int creationUserID) {
		this.creationUserID = creationUserID;
	}
	public int getLastModUserID() {
		return lastModUserID;
	}
	public void setLastModUserID(int lastModUserID) {
		this.lastModUserID = lastModUserID;
	}
	public int getDeleteUserID() {
		return deleteUserID;
	}
	public void setDeleteUserID(int deleteUserID) {
		this.deleteUserID = deleteUserID;
	}
	public String getTable() {
		return this.TABLE;
	}
	public String[] getColumns() {
		return this.columns;
	}
	public Class[] getTypes() {
		return this.types;
	}
}
