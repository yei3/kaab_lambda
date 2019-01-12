package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class MenuRol implements Entity{
	private int id;
	private int menuID;
	private String role;
	private String description;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "MENU_ROL";
	
	private String[] columns = {
			"id",
			"menuID",
			"role",
			"description",
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
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	public MenuRol() {
	}

	public MenuRol(int menuID, String role, String description, int statusID) {
		this.menuID = menuID;
		this.role = role;
		this.description = description;
		this.statusID = statusID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "MenuRol [id=" + id + ", menuID=" + menuID + ", role=" + role + ", description=" + description
				+ ", statusID=" + statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime="
				+ lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID
				+ ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
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
