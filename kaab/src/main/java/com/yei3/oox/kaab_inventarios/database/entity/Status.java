package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class Status implements Entity{
	private int id;
	private String status;
	private String description;
	private int statusGruop;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	
	private String TABLE = "STATUS";
	private String[] columns = {
			"id",
			"status",
			"description",
			"statusGruop",
			"creationDateTime",
			"lastModDateTime",
			"deleteDateTime"
			};
	
	private Class[] types = {
			int.class,
			String.class,
			String.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class
			};
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + ", description=" + description + ", statusGruop="
				+ statusGruop + ", creationDateTime=" + creationDateTime + ", lastModDateTime=" + lastModDateTime
				+ ", deleteDateTime=" + deleteDateTime + "]";
	}
	
	public Status(String status, String description, int statusGruop) {
		this.status = status;
		this.description = description;
		this.statusGruop = statusGruop;
	}

	public Status() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatusGruop() {
		return statusGruop;
	}
	public void setStatusGruop(int statusGruop) {
		this.statusGruop = statusGruop;
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
