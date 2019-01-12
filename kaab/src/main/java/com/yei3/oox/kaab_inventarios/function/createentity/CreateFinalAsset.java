package com.yei3.oox.kaab_inventarios.function.createentity;

import static java.lang.Math.toIntExact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.sql.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.yei3.oox.kaab_inventarios.database.entity.AccountingAccount;
import com.yei3.oox.kaab_inventarios.database.entity.CostCenter;
import com.yei3.oox.kaab_inventarios.database.entity.Department;
import com.yei3.oox.kaab_inventarios.database.entity.FinalAsset;
import com.yei3.oox.kaab_inventarios.database.entity.Location;
import com.yei3.oox.kaab_inventarios.database.entity.Project;
import com.yei3.oox.kaab_inventarios.database.entity.RegistrationSession;
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateFinalAsset implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("CreatefinalAsset starting");
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
        	FinalAsset finalAsset = new FinalAsset();
        	
        	Project project = (Project)h.getItemById(Project.class, toIntExact((long) body.get("projectID")));
        	if (project != null) {
        		finalAsset.setProjectID(toIntExact((long) body.get("projectID")));
        		RegistrationSession registrationSession = (RegistrationSession)h.getItemById(RegistrationSession.class, toIntExact((long) body.get("registrationSessionID")));
        		if (registrationSession != null) {
        			finalAsset.setRegistrationSessionID(toIntExact((long) body.get("registrationSessionID")));
                	finalAsset.setLastRegistration((boolean) body.get("lastRegistration"));
                	finalAsset.setKeyField((String) body.get("keyField"));
                	finalAsset.setAsset((String) body.get("asset"));
                	finalAsset.setDescription((String) body.get("description"));
                	finalAsset.setBrand((String) body.get("brand"));
                	finalAsset.setModel((String) body.get("model"));
                	finalAsset.setSerial((String) body.get("serial"));
                	finalAsset.setAcquisitionDate(Date.valueOf((String)body.get("acquisitionDate")));
                	finalAsset.setAcquisitionType((String) body.get("acquisitionType"));
                	finalAsset.setInvoice((String) body.get("invoice"));
                	finalAsset.setPrice((double) body.get("price"));
                	finalAsset.setTax((double) body.get("tax"));
                	finalAsset.setCost((double) body.get("cost"));
                	finalAsset.setCurrentValue((double) body.get("currentValue"));
                	finalAsset.setAccountingDepreciation((double) body.get("accountingDepreciation"));
                	finalAsset.setFiscalDepreciation((double) body.get("fiscalDepreciation"));
                	Department department = (Department)h.getItemById(Department.class, toIntExact((long) body.get("lastDepartmentID")));
                	finalAsset.setLastDepartmentID(toIntExact(department == null ? 0 : (long) body.get("lastDepartmentID")));
                	department = (Department)h.getItemById(Department.class, toIntExact((long) body.get("currentDepartmentID")));
                	if (department != null) {
                		finalAsset.setCurrentDepartmentID(toIntExact((long)body.get("currentDepartmentID")));
                		CostCenter costCenter = (CostCenter)h.getItemById(CostCenter.class, toIntExact((long) body.get("costCenterID")));
                		if (costCenter != null) {
                			finalAsset.setCostCenterID(toIntExact((long) body.get("costCenterID")));
                			AccountingAccount accountingAccount = (AccountingAccount)h.getItemById(AccountingAccount.class, toIntExact((long) body.get("acountingAccountID")));
    	        			if (accountingAccount != null) {
    	        				finalAsset.setAcountingAccountID(toIntExact((long) body.get("acountingAccountID")));
    	        				Location location = (Location)h.getItemById(Location.class, toIntExact((long)body.get("locationID")));
    	        				if (location != null) {
    	        					finalAsset.setLocationID(toIntExact((long) body.get("locationID")));
                                	finalAsset.setLocationDetail((String) body.get("locationDetail"));
                                	finalAsset.setComments((String) body.get("comments"));
                                	finalAsset.setPersonalString01((String) body.get("personalString01"));
                                	finalAsset.setPersonalString02((String) body.get("personalString02"));
                                	finalAsset.setPersonalString03((String) body.get("personalString03"));
                                	finalAsset.setPersonalString04((String) body.get("personalString04"));
                                	finalAsset.setPersonalString05((String) body.get("personalString05"));
                                	finalAsset.setPersonalInt01(toIntExact((long) body.get("personalInt01")));
                                	finalAsset.setPersonalInt02(toIntExact((long) body.get("personalInt02")));
                                	finalAsset.setPersonalInt03(toIntExact((long) body.get("personalInt03")));
                                	finalAsset.setPersonalFloat01((double) body.get("personalFloat01"));
                                	finalAsset.setPersonalFloat02((double) body.get("personalFloat02"));
                                	finalAsset.setPersonalFloat03((double) body.get("personalFloat03"));
                                	Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
    			                	if (status != null) {
    			                		finalAsset.setStatusID(toIntExact((long) body.get("statusID")));
                                    	//TODO add cognito >:v
    			                		User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
    			                    	if (user != null) {
    			                    		finalAsset.setCreationUserID(toIntExact((long)body.get("userId")));
                                        	finalAsset.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
                                        	
                                        	h.insertItem(FinalAsset.class, finalAsset);
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
                	}else{
                		errorCode.put("errorCode", -8);
                        errorCode.put("message", Error.getErrorByCode(-8));
                	}
        		}else {
        			errorCode.put("errorCode", -14);
                    errorCode.put("message", Error.getErrorByCode(-14));
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
