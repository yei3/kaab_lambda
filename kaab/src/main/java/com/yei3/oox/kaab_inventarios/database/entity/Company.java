package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class Company implements Entity{
	private int id;
	private int companyAccountID;
	private String companyType;
	private String industryType;
	private String name;
	private String fiscalID;
	private int addressID;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "COMPANIES";
	
	private String[] columns = {
			"id",
			"companyAccountID",
			"companyType",
			"industryType",
			"name",
			"fiscalID",
			"addressID",
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
			int.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	
	public Company(int companyAccountID, String companyType, String industryType, String name, String fiscalID, int addressID, int statusID) {
		this.companyAccountID = companyAccountID;
		this.companyType = companyType;
		this.industryType = industryType;
		this.name = name;
		this.fiscalID = fiscalID;
		this.addressID = addressID;
		this.statusID = statusID;
	}
	
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyAccountID=" + companyAccountID + ", companyType=" + companyType
				+ ", industryType=" + industryType + ", name=" + name + ", fiscalID=" + fiscalID + ", addressID="
				+ addressID + ", statusID=" + statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime="
				+ lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID
				+ ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
 

	public int getCompanyAccountID() {
		return companyAccountID;
	}


	public void setCompanyAccountID(int companyAccountID) {
		this.companyAccountID = companyAccountID;
	}


	public void setColumns(String[] columns) {
		this.columns = columns;
	}


	public Company() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFiscalID() {
		return fiscalID;
	}
	public void setFiscalID(String fiscalID) {
		this.fiscalID = fiscalID;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
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
