package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.sql.Timestamp;

public class Address implements Entity{
	private int id;
	private String addressType;
	private String roadType;
	private String roadName;
	private String outdoorNumber;
	private String indoorNumber;
	private String colony;
	private String location;
	private String municipality;
	private String cp;
	private String state;
	private String phoneType;
	private String phoneNumber;
	private String phoneType2;
	private String phoneNumber2;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "ADDRESSES";
	
	private String[] columns = {
			"id",
			"addressType",
			"roadType",
			"roadName",
			"outdoorNumber",
			"indoorNumber",
			"colony",
			"location",
			"municipality",
			"cp",
			"state",
			"phoneType",
			"phoneNumber",
			"phoneType2",
			"phoneNumber2",
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
		return "Address [id=" + id + ", addressType=" + addressType + ", roadType=" + roadType + ", roadName="
				+ roadName + ", outdoorNumber=" + outdoorNumber + ", indoorNumber=" + indoorNumber + ", colony="
				+ colony + ", location=" + location + ", municipality=" + municipality + ", cp=" + cp + ", state="
				+ state + ", phoneType=" + phoneType + ", phoneNumber=" + phoneNumber + ", phoneType2=" + phoneType2
				+ ", phoneNumber2=" + phoneNumber2 + ", statusID=" + statusID + ", creationDateTime=" + creationDateTime
				+ ", lastModDateTime=" + lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID="
				+ creationUserID + ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public Address(String addressType, String roadType, String roadName, String outdoorNumber, String indoorNumber,
			String colony, String location, String municipality, String cp, String state, String phoneType,
			String phoneNumber, String phoneType2, String phoneNumber2, int statusID) {
		this.addressType = addressType;
		this.roadType = roadType;
		this.roadName = roadName;
		this.outdoorNumber = outdoorNumber;
		this.indoorNumber = indoorNumber;
		this.colony = colony;
		this.location = location;
		this.municipality = municipality;
		this.cp = cp;
		this.state = state;
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
		this.phoneType2 = phoneType2;
		this.phoneNumber2 = phoneNumber2;
		this.statusID = statusID;
	}
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getOutdoorNumber() {
		return outdoorNumber;
	}
	public void setOutdoorNumber(String outdoorNumber) {
		this.outdoorNumber = outdoorNumber;
	}
	public String getIndoorNumber() {
		return indoorNumber;
	}
	public void setIndoorNumber(String indoorNumber) {
		this.indoorNumber = indoorNumber;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
