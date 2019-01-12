package com.yei3.oox.kaab_inventarios.function.createentity;

import static java.lang.Math.toIntExact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.sql.Timestamp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.yei3.oox.kaab_inventarios.database.entity.AccountingAccount;
import com.yei3.oox.kaab_inventarios.database.entity.Asset;
import com.yei3.oox.kaab_inventarios.database.entity.CostCenter;
import com.yei3.oox.kaab_inventarios.database.entity.Department;
import com.yei3.oox.kaab_inventarios.database.entity.Location;
import com.yei3.oox.kaab_inventarios.database.entity.Project;
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateAsset implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Createasset starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	//context.getIdentity().getIdentityId()
        try {
        	Helper h = new Helper(context);
        	JSONObject event = (JSONObject)parser.parse(reader);
        	JSONObject body = (JSONObject)parser.parse((String) event.get("body"));
        	Asset asset = new Asset();
        	Project project = (Project)h.getItemById(Project.class, toIntExact((long) body.get("projectID")));
        	
        	if (project != null) {
        		asset.setProjectID(toIntExact((long) body.get("projectID")));
	        	asset.setKeyField((String) body.get("keyField"));
	        	asset.setAsset((String) body.get("asset"));
	        	asset.setDescription((String) body.get("description"));
	        	asset.setBrand((String) body.get("brand"));
	        	asset.setModel((String) body.get("model"));
	        	asset.setSerial((String) body.get("serial"));
	        	asset.setAcquisitionDate(Date.valueOf((String) body.get("acquisitionDate")));
	        	asset.setAcquisitionType((String) body.get("acquisitionType"));
	        	asset.setInvoice((String) body.get("invoice"));
	        	asset.setPrice((double) body.get("price"));
	        	asset.setTax((double) body.get("tax"));
	        	asset.setCost((double) body.get("cost"));
	        	asset.setCurrentValue((double) body.get("currentValue"));
	        	asset.setAccountingDepreciation((double) body.get("accountingDepreciation"));
	        	asset.setFiscalDepreciation((double) body.get("fiscalDepreciation"));
	        	
	        	Department department = (Department)h.getItemById(Department.class, toIntExact((long) body.get("lastDepartmentID")));
	        	asset.setLastDepartmentID(toIntExact(department == null ? 0 : (long) body.get("lastDepartmentID")));
	        	department = (Department)h.getItemById(Department.class, toIntExact((long) body.get("currentDepartmentID")));
	        	
	        	if (department != null) {
	        		asset.setCurrentDepartmentID(toIntExact((long)body.get("currentDepartmentID")));
	        		
	        		CostCenter costCenter = (CostCenter)h.getItemById(CostCenter.class, toIntExact((long) body.get("costCenterID")));
	        		if (costCenter != null) {
	        			asset.setCostCenterID(toIntExact((long) body.get("costCenterID")));
	        			AccountingAccount accountingAccount = (AccountingAccount)h.getItemById(AccountingAccount.class, toIntExact((long) body.get("acountingAccountID")));
	        			if (accountingAccount != null) {
	        				asset.setAcountingAccountID(toIntExact((long) body.get("acountingAccountID")));
	        				Location location = (Location)h.getItemById(Location.class, toIntExact((long)body.get("locationID")));
	        				if (location != null) {
	        					asset.setLocationID(toIntExact((long)body.get("locationID")));
					        	asset.setLocationDetail((String) body.get("locationDetail"));
					        	asset.setComments((String) body.get("comments"));
					        	asset.setPersonalString01((String) body.get("personalString01"));
					        	asset.setPersonalString02((String) body.get("personalString02"));
					        	asset.setPersonalString03((String) body.get("personalString03"));
					        	asset.setPersonalString04((String) body.get("personalString04"));
					        	asset.setPersonalString05((String) body.get("personalString05"));
					        	asset.setPersonalInt01(toIntExact((long)body.get("personalInt01")));
					        	asset.setPersonalInt02(toIntExact((long) body.get("personalInt02")));
					        	asset.setPersonalInt03(toIntExact((long) body.get("personalInt03")));
					        	asset.setPersonalFloat01((double) body.get("personalFloat01"));
					        	asset.setPersonalFloat01((double) body.get("personalFloat02"));
					        	asset.setPersonalFloat01((double) body.get("personalFloat03"));
					        	Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
			                	if (status != null) {
			                		asset.setStatusID(toIntExact((long) body.get("statusID")));
						        	//TODO add cognito >:v
			                		User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
			                    	if (user != null) {
			                    		asset.setCreationUserID(toIntExact((long)body.get("userId")));
							        	asset.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
							        	h.insertItem(Asset.class, asset);
							        	errorCode.put("errorCode", 0);
							        	errorCode.put("message", Error.getErrorByCode(0));
			                    	}else {
			                    		errorCode.put("errorCode", -5);
			                    		errorCode.put("message", Error.getErrorByCode(-5));
			                    	}
			                	}else {
			                		errorCode.put("errorCode", -6);
			                		errorCode.put("message", Error.getErrorByCode(-6));
			                	}
	        				}else {
	        					errorCode.put("errorCode", -11);
	        					errorCode.put("message", Error.getErrorByCode(-11));
	        				}
	        			}else {
	        				errorCode.put("errorCode", -10);
	        				errorCode.put("message", Error.getErrorByCode(-10));
	        			}
	        		}else {
	        			errorCode.put("errorCode", -9);
	        			errorCode.put("message", Error.getErrorByCode(-9));
	        		}
	        	}else {
	        		errorCode.put("errorCode", -8);
	        		errorCode.put("message", Error.getErrorByCode(-8));
	        	}
        	}else {
        		errorCode.put("errorCode", -7);
        		errorCode.put("message", Error.getErrorByCode(-7));
        	}
        } catch(Exception ex) {
        	errorCode.put("errorCode", -100);
            errorCode.put("message", ex.getMessage());
            logger.log("Error: " + ex.getMessage());
        }
        responseBody.put("error", errorCode);
    	JSONObject headerJson = new JSONObject();
    	headerJson.put("Access-Control-Allow-Origin", "*");
        responseJson.put("isBase64Encoded", false);
        responseJson.put("statusCode", responseCode);
        responseJson.put("headers", headerJson);
        responseJson.put("body", responseBody.toString()); 
        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toJSONString());  
        writer.close();
    }

}
