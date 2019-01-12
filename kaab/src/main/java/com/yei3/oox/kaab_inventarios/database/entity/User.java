package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class User implements Entity{
	private int id;
	private int companyAccountID;
	private String role;
	private String user;
	private String names;
	private String lastname;
	private String middlename;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "USERS";
	
	private String[] columns = {
			"id",
			"companyAccountID",
			"role",
			"user",
			"names",
			"lastname",
			"middlename",
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
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	public User(int companyAccountID, String role, String user, String names, String lastname, String middlename, int statusID) {
		this.companyAccountID = companyAccountID;
		this.role = role;
		this.user = user;
		this.names = names;
		this.lastname = lastname;
		this.middlename = middlename;
		this.statusID = statusID;
	}
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", companyAccountID=" + companyAccountID + ", role=" + role + ", user=" + user
				+ ", names=" + names + ", lastname=" + lastname + ", middlename=" + middlename + ", statusID="
				+ statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime=" + lastModDateTime
				+ ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID + ", lastModUserID="
				+ lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}



	public int getCompanyAccountID() {
		return companyAccountID;
	}



	public void setCompanyAccountID(int companyAccountID) {
		this.companyAccountID = companyAccountID;
	}



	public User() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
