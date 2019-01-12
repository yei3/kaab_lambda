package com.yei3.oox.kaab_inventarios.function.updateentity;

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
import com.yei3.oox.kaab_inventarios.database.entity.Asset;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateAsset implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Updateasset starting");
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
        	Asset asset = (Asset)h.getItemById(Asset.class, toIntExact((long)body.get("id")));
        	
        	if (asset != null) {
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
            	asset.setPrice((float) body.get("price"));
            	asset.setTax((float) body.get("tax"));
            	asset.setCost((float) body.get("cost"));
            	asset.setCurrentValue((float) body.get("currentValue"));
            	asset.setAccountingDepreciation((float) body.get("accountingDepreciation"));
            	asset.setFiscalDepreciation((float) body.get("fiscalDepreciation"));
            	asset.setLastDepartmentID(toIntExact((long) body.get("lastDepartmentID")));
            	asset.setCurrentDepartmentID(toIntExact((long)body.get("currentDepartmentID")));
            	asset.setCostCenterID(toIntExact((long) body.get("costCenterID")));
            	asset.setAcountingAccountID(toIntExact((long) body.get("acountingAccountID")));
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
            	asset.setPersonalFloat01((float) body.get("personalFloat01"));
            	asset.setPersonalFloat01((float) body.get("personalFloat02"));
            	asset.setPersonalFloat01((float) body.get("personalFloat03"));
            	asset.setStatusID(toIntExact((long) body.get("statusID")));
            	//TODO add cognito >:v
            	asset.setLastModUserID(toIntExact((long)body.get("userId")));
            	asset.setLastModDateTime(new Timestamp(System.currentTimeMillis()));
            	
            	h.updateItem(Asset.class, asset);
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
