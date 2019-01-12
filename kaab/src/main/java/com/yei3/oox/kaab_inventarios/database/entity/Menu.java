package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class Menu implements Entity{
	private int id;
	private String menu;
	private String description;
	private int parentID;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "MENU";
	
	private String[] columns = {
			"id",
			"menu",
			"description",
			"parentID",
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
			int.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	public Menu() {
	}

	public Menu(String menu, String description, int parentID, int statusID) {
		this.menu = menu;
		this.description = description;
		this.parentID = parentID;
		this.statusID = statusID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
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
		return "Menu [id=" + id + ", menu=" + menu + ", description=" + description + ", parentID=" + parentID
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
