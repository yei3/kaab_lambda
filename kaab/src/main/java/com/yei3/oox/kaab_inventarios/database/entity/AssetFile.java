package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class AssetFile implements Entity{
	private int id;
	private int assetID;
	private int fileID;
	private boolean isFinal;
	private int statusID;
	
	private String TABLE = "ASSET_FILES";
	
	private String[] columns = {
			"id",
			"assetID",
			"fileID",
			"isFinal",
			"statusID",
			};
	
	private Class[] types = {
			int.class,
			int.class,
			int.class,
			boolean.class,
			int.class
			};
	
	public AssetFile() {
	}

	public AssetFile(int assetID, int fileID, boolean isFinal, int statusID) {
		this.assetID = assetID;
		this.fileID = fileID;
		this.isFinal = isFinal;
		this.statusID = statusID;
	}

	@Override
	public String getTable() {
		return this.TABLE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssetID() {
		return assetID;
	}

	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public boolean getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public String[] getColumns() {
		return this.columns;
	}
	public Class[] getTypes() {
		return this.types;
	}
	
}