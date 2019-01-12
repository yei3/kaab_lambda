package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class File implements Entity{
	private int id;
	private String mime;
	private String url;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "FILES";
	
	private String[] columns = {
			"id",
			"mime",
			"url",
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
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	public File(String mime, String url, int statusID) {
		this.mime = mime;
		this.url = url;
		this.statusID = statusID;
	}
	
	@Override
	public String toString() {
		return "File [id=" + id + ", mime=" + mime + ", url=" + url + ", statusID=" + statusID + ", creationDateTime="
				+ creationDateTime + ", lastModDateTime=" + lastModDateTime + ", deleteDateTime=" + deleteDateTime
				+ ", creationUserID=" + creationUserID + ", lastModUserID=" + lastModUserID + ", deleteUserID="
				+ deleteUserID + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	public String getTable() {
		return this.TABLE;
	}

	public File() {
	}
	public String[] getColumns() {
		return this.columns;
	}
	public Class[] getTypes() {
		return this.types;
	}
}
