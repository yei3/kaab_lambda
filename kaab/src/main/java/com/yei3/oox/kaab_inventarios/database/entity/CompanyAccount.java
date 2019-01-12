package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class CompanyAccount implements Entity{
	private  int id;
	private String companyName;
	private int fileID;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	
private String TABLE = "COMPANY_ACCOUNTS";
	
	private String[] columns = {
			"id",
			"companyName",
			"fileID",
			"statusID",
			"creationDateTime",
			"lastModDateTime",
			"deleteDateTime"
			};
	
	private Class[] types = {
			int.class,
			String.class,
			int.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class
			};
	
	public CompanyAccount() {
	}
	public CompanyAccount(String companyName, int fileID, int statusID) {
		super();
		this.companyName = companyName;
		this.fileID = fileID;
		this.statusID = statusID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
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
	@Override
	public String toString() {
		return "CompanyAccount [id=" + id + ", companyName=" + companyName + ", fileID=" + fileID + ", statusID="
				+ statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime=" + lastModDateTime
				+ ", deleteDateTime=" + deleteDateTime + "]";
	}
	@Override
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
