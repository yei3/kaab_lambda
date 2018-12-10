package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class LocationAddress implements Entity{
	private int id;
	private int locationID;
	private int addressID;
	private int departmentID;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "LOCATIONS_ADDRESSES";
	
	private String[] columns = {
			"id",
			"locationID",
			"addressID",
			"departmentID",
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
		return "LocationAddress [id=" + id + ", locationID=" + locationID + ", addressID=" + addressID
				+ ", departmentID=" + departmentID + ", statusID=" + statusID + ", creationDateTime=" + creationDateTime
				+ ", lastModDateTime=" + lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID="
				+ creationUserID + ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public LocationAddress(int locationID, int addressID, int departmentID, int statusID) {
		this.locationID = locationID;
		this.addressID = addressID;
		this.departmentID = departmentID;
		this.statusID = statusID;
	}
	
	public LocationAddress() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
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
