package com.yei3.oox.kaab_inventarios.function.updateentity;

import static java.lang.Math.toIntExact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.yei3.oox.kaab_inventarios.database.entity.FinalAsset;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateFinalAsset implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("UpdatefinalAsset starting");
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
        	FinalAsset finalAsset = (FinalAsset)h.getItemById(FinalAsset.class, toIntExact((long)body.get("id")));
        	
        	if (finalAsset != null) {
        		finalAsset.setProjectID(toIntExact((long) body.get("projectID")));
            	finalAsset.setRegistrationSessionID(toIntExact((long) body.get("registrationSessionID")));
            	finalAsset.setLastRegistration((boolean) body.get("lastRegistration"));
            	finalAsset.setKeyField((String) body.get("keyField"));
            	finalAsset.setAsset((String) body.get("asset"));
            	finalAsset.setDescription((String) body.get("description"));
            	finalAsset.setBrand((String) body.get("brand"));
            	finalAsset.setModel((String) body.get("model"));
            	finalAsset.setSerial((String) body.get("serial"));
            	finalAsset.setAcquisitionDate(Timestamp.valueOf((String)body.get("acquisitionDate")));
            	finalAsset.setAcquisitionType((String) body.get("acquisitionType"));
            	finalAsset.setInvoice((String) body.get("invoice"));
            	finalAsset.setPrice((float) body.get("price"));
            	finalAsset.setTax((float) body.get("tax"));
            	finalAsset.setCost((float) body.get("cost"));
            	finalAsset.setCurrentValue((float) body.get("currentValue"));
            	finalAsset.setAccountingDepreciation((float) body.get("accountingDepreciation"));
            	finalAsset.setFiscalDepreciation((float) body.get("fiscalDepreciation"));
            	finalAsset.setLastDepartmentID(toIntExact((long) body.get("lastDepartmentID")));
            	finalAsset.setCurrentDepartmentID(toIntExact((long)body.get("currentDepartmentID")));
            	finalAsset.setCostCenterID(toIntExact((long) body.get("costCenterID")));
            	finalAsset.setAcountingAccountID(toIntExact((long) body.get("acountingAccountID")));
            	finalAsset.setLocationID(toIntExact((long) body.get("locationID")));
            	finalAsset.setLocationDetail((String) body.get("locationDetail"));
            	finalAsset.setComments((String) body.get("comments"));
            	finalAsset.setImage((String) body.get("image"));
            	finalAsset.setPersonalString01((String) body.get("personalString01"));
            	finalAsset.setPersonalString02((String) body.get("personalString02"));
            	finalAsset.setPersonalString03((String) body.get("personalString03"));
            	finalAsset.setPersonalString04((String) body.get("personalString04"));
            	finalAsset.setPersonalString05((String) body.get("personalString05"));
            	finalAsset.setPersonalInt01(toIntExact((long) body.get("personalInt01")));
            	finalAsset.setPersonalInt02(toIntExact((long) body.get("personalInt02")));
            	finalAsset.setPersonalInt03(toIntExact((long) body.get("personalInt03")));
            	finalAsset.setPersonalFloat01((float) body.get("personalFloat01"));
            	finalAsset.setPersonalFloat02((float) body.get("personalFloat02"));
            	finalAsset.setPersonalFloat03((float) body.get("personalFloat03"));
            	finalAsset.setStatusID(toIntExact((long) body.get("statusID")));
            	//TODO add cognito >:v
            	finalAsset.setLastModUserID(toIntExact((long)body.get("userId")));
            	finalAsset.setLastModDateTime(new Timestamp(System.currentTimeMillis()));
            	
            	h.updateItem(FinalAsset.class, finalAsset);
            	errorCode.put("errorCode", 0);
                errorCode.put("message", "Success");
        	}else {
        		errorCode.put("errorCode", -3);
                errorCode.put("message", "The given id does not exists.");
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
