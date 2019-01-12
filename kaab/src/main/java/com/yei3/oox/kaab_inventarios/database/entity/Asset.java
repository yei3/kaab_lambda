package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;
import java.sql.Date;

public class Asset implements Entity{
	private int id;
	private int projectID;
	private String keyField;
	private String asset;
	private String description;
	private String brand;
	private String model;
	private String serial;
	private Date acquisitionDate;
	private String acquisitionType;
	private String invoice;
	private double price;
	private double tax;
	private double cost;
	private double currentValue;
	private double accountingDepreciation;
	private double fiscalDepreciation;
	private int lastDepartmentID;
	private int currentDepartmentID;
	private int costCenterID;
	private int acountingAccountID;
	private int locationID;
	private String locationDetail;
	private String comments;
	private String personalString01;
	private String personalString02;
	private String personalString03;
	private String personalString04;
	private String personalString05;
	private int personalInt01;
	private int personalInt02;
	private int personalInt03;
	private double personalFloat01;
	private double personalFloat02;
	private double personalFloat03;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private  String TABLE = "ASSETS";
	
	private String[] columns = {
			"id",
			"projectID",
			"keyField",
			"asset",
			"description",
			"brand",
			"model",
			"serial",
			"acquisitionDate",
			"acquisitionType",
			"invoice",
			"price",
			"tax",
			"cost",
			"currentValue",
			"accountingDepreciation",
			"fiscalDepreciation",
			"lastDepartmentID",
			"currentDepartmentID",
			"costCenterID",
			"acountingAccountID",
			"locationID",
			"locationDetail",
			"comments",
			"personalString01",
			"personalString02",
			"personalString03",
			"personalString04",
			"personalString05",
			"personalInt01",
			"personalInt02",
			"personalInt03",
			"personalFloat01",
			"personalFloat02",
			"personalFloat03",
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
			String.class,
			String.class,
			Date.class,
			String.class,
			String.class,
			double.class,
			double.class,
			double.class,
			double.class,
			double.class,
			double.class,
			int.class,
			int.class,
			int.class,
			int.class,
			int.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			int.class,
			int.class,
			int.class,
			double.class,
			double.class,
			double.class,
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
		return "Asset [id=" + id + ", projectID=" + projectID + ", keyField=" + keyField + ", asset=" + asset
				+ ", description=" + description + ", brand=" + brand + ", model=" + model + ", serial=" + serial
				+ ", acquisitionDate=" + acquisitionDate + ", acquisitionType=" + acquisitionType + ", invoice="
				+ invoice + ", price=" + price + ", tax=" + tax + ", cost=" + cost + ", currentValue=" + currentValue
				+ ", accountingDepreciation=" + accountingDepreciation + ", fiscalDepreciation=" + fiscalDepreciation
				+ ", lastDepartmentID=" + lastDepartmentID + ", currentDepartmentID=" + currentDepartmentID
				+ ", costCenterID=" + costCenterID + ", acountingAccountID=" + acountingAccountID + ", locationID="
				+ locationID + ", locationDetail=" + locationDetail + ", comments=" + comments + ", personalString01=" 
				+ personalString01 + ", personalString02=" + personalString02
				+ ", personalString03=" + personalString03 + ", personalString04=" + personalString04
				+ ", personalString05=" + personalString05 + ", personalInt01=" + personalInt01 + ", personalInt02="
				+ personalInt02 + ", personalInt03=" + personalInt03 + ", personalFloat01=" + personalFloat01
				+ ", personalFloat02=" + personalFloat02 + ", personalFloat03=" + personalFloat03 + ", statusID="
				+ statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime=" + lastModDateTime
				+ ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID + ", lastModUserID="
				+ lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public Asset(int projectID, String keyField, String asset, String description, String brand, String model,
			String serial, Date acquisitionDate, String acquisitionType, String invoice, double price, double tax,
			double cost, double currentValue, double accountingDepreciation, double fiscalDepreciation,
			int lastDepartmentID, int currentDepartmentID, int costCenterID, int acountingAccountID, int locationID,
			String locationDetail, String comments, String personalString01, String personalString02,
			String personalString03, String personalString04, String personalString05, int personalInt01,
			int personalInt02, int personalInt03, double personalFloat01, double personalFloat02, double personalFloat03,
			int statusID) {
		this.projectID = projectID;
		this.keyField = keyField;
		this.asset = asset;
		this.description = description;
		this.brand = brand;
		this.model = model;
		this.serial = serial;
		this.acquisitionDate = acquisitionDate;
		this.acquisitionType = acquisitionType;
		this.invoice = invoice;
		this.price = price;
		this.tax = tax;
		this.cost = cost;
		this.currentValue = currentValue;
		this.accountingDepreciation = accountingDepreciation;
		this.fiscalDepreciation = fiscalDepreciation;
		this.lastDepartmentID = lastDepartmentID;
		this.currentDepartmentID = currentDepartmentID;
		this.costCenterID = costCenterID;
		this.acountingAccountID = acountingAccountID;
		this.locationID = locationID;
		this.locationDetail = locationDetail;
		this.comments = comments;
		this.personalString01 = personalString01;
		this.personalString02 = personalString02;
		this.personalString03 = personalString03;
		this.personalString04 = personalString04;
		this.personalString05 = personalString05;
		this.personalInt01 = personalInt01;
		this.personalInt02 = personalInt02;
		this.personalInt03 = personalInt03;
		this.personalFloat01 = personalFloat01;
		this.personalFloat02 = personalFloat02;
		this.personalFloat03 = personalFloat03;
		this.statusID = statusID;
	}
	
	public Asset() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getKeyField() {
		return keyField;
	}
	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public Date getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	public String getAcquisitionType() {
		return acquisitionType;
	}
	public void setAcquisitionType(String acquisitionType) {
		this.acquisitionType = acquisitionType;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
	public double getAccountingDepreciation() {
		return accountingDepreciation;
	}
	public void setAccountingDepreciation(double accountingDepreciation) {
		this.accountingDepreciation = accountingDepreciation;
	}
	public double getFiscalDepreciation() {
		return fiscalDepreciation;
	}
	public void setFiscalDepreciation(double fiscalDepreciation) {
		this.fiscalDepreciation = fiscalDepreciation;
	}
	public int getLastDepartmentID() {
		return lastDepartmentID;
	}
	public void setLastDepartmentID(int lastDepartmentID) {
		this.lastDepartmentID = lastDepartmentID;
	}
	public int getCurrentDepartmentID() {
		return currentDepartmentID;
	}
	public void setCurrentDepartmentID(int currentDepartmentID) {
		this.currentDepartmentID = currentDepartmentID;
	}
	public int getCostCenterID() {
		return costCenterID;
	}
	public void setCostCenterID(int costCenterID) {
		this.costCenterID = costCenterID;
	}
	public int getAcountingAccountID() {
		return acountingAccountID;
	}
	public void setAcountingAccountID(int acountingAccountID) {
		this.acountingAccountID = acountingAccountID;
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public String getLocationDetail() {
		return locationDetail;
	}
	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPersonalString01() {
		return personalString01;
	}
	public void setPersonalString01(String personalString01) {
		this.personalString01 = personalString01;
	}
	public String getPersonalString02() {
		return personalString02;
	}
	public void setPersonalString02(String personalString02) {
		this.personalString02 = personalString02;
	}
	public String getPersonalString03() {
		return personalString03;
	}
	public void setPersonalString03(String personalString03) {
		this.personalString03 = personalString03;
	}
	public String getPersonalString04() {
		return personalString04;
	}
	public void setPersonalString04(String personalString04) {
		this.personalString04 = personalString04;
	}
	public String getPersonalString05() {
		return personalString05;
	}
	public void setPersonalString05(String personalString05) {
		this.personalString05 = personalString05;
	}
	public int getPersonalInt01() {
		return personalInt01;
	}
	public void setPersonalInt01(int personalInt01) {
		this.personalInt01 = personalInt01;
	}
	public int getPersonalInt02() {
		return personalInt02;
	}
	public void setPersonalInt02(int personalInt02) {
		this.personalInt02 = personalInt02;
	}
	public int getPersonalInt03() {
		return personalInt03;
	}
	public void setPersonalInt03(int personalInt03) {
		this.personalInt03 = personalInt03;
	}
	public double getPersonalFloat01() {
		return personalFloat01;
	}
	public void setPersonalFloat01(double personalFloat01) {
		this.personalFloat01 = personalFloat01;
	}
	public double getPersonalFloat02() {
		return personalFloat02;
	}
	public void setPersonalFloat02(double personalFloat02) {
		this.personalFloat02 = personalFloat02;
	}
	public double getPersonalFloat03() {
		return personalFloat03;
	}
	public void setPersonalFloat03(double personalFloat03) {
		this.personalFloat03 = personalFloat03;
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
