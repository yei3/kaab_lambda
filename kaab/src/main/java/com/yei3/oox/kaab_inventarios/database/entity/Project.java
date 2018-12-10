package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class Project implements Entity{
	private int id;
	private int companyID;
	private int contactID;
	private String name;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "PROJECTS";
	
	private String[] columns = {
			"id",
			"companyID",
			"contactID",
			"name",
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
			int.class,
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
		return "Project [id=" + id + ", companyID=" + companyID + ", contactID=" + contactID + ", name=" + name
				+ ", statusID=" + statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime="
				+ lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID
				+ ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public Project(int companyID, int contactID, String name, int statusID) {
		this.companyID = companyID;
		this.contactID = contactID;
		this.name = name;
		this.statusID = statusID;
	}
	
	public Project() {
		
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
	public int getContactID() {
		return contactID;
	}
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
