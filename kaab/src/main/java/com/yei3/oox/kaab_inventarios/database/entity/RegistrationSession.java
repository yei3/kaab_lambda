package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class RegistrationSession implements Entity{
	private int id;
	private Timestamp finalDateTime;
	private int sessionDepartmentID;
	private int sessionLocationId;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "REGISTRATION_SESSIONS";
	
	private String[] columns = {
			"id",
			"finalDateTime",
			"sessionDepartmentID",
			"sessionLocationId",
			"creationDateTime",
			"lastModDateTime",
			"deleteDateTime",
			"creationUserID",
			"lastModUserID",
			"deleteUserID"
			};
	
	private Class[] types = {
			int.class,
			Timestamp.class,
			int.class,
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
		return "RegistrationSession [id=" + id + ", finalDateTime=" + finalDateTime + ", sessionDepartmentID="
				+ sessionDepartmentID + ", sessionLocationId=" + sessionLocationId + ", creationDateTime="
				+ creationDateTime + ", lastModDateTime=" + lastModDateTime + ", deleteDateTime=" + deleteDateTime
				+ ", creationUserID=" + creationUserID + ", lastModUserID=" + lastModUserID + ", deleteUserID="
				+ deleteUserID + "]";
	}

	public RegistrationSession(Timestamp finalDateTime, int sessionDepartmentID, int sessionLocationId) {
		this.finalDateTime = finalDateTime;
		this.sessionDepartmentID = sessionDepartmentID;
		this.sessionLocationId = sessionLocationId;
	}
	
	public RegistrationSession() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getFinalDateTime() {
		return finalDateTime;
	}
	public void setFinalDateTime(Timestamp finalDateTime) {
		this.finalDateTime = finalDateTime;
	}
	public int getSessionDepartmentID() {
		return sessionDepartmentID;
	}
	public void setSessionDepartmentID(int sessionDepartmentID) {
		this.sessionDepartmentID = sessionDepartmentID;
	}
	public int getSessionLocationId() {
		return sessionLocationId;
	}

	public void setSessionLocationId(int sessionLocationId) {
		this.sessionLocationId = sessionLocationId;
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
