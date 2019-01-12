package com.yei3.oox.kaab_inventarios.function.getallentity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.yei3.oox.kaab_inventarios.database.entity.Asset;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAllAssets implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getallassets starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	JSONArray list = new JSONArray();
    	
        try {
        	Helper h = new Helper(context);
        	List<Object> assets = (List<Object>)h.getAllEntity(Asset.class);
        	Asset asset;
        	JSONObject assetJson;
        	
			for (int i = 0; i < assets.size(); i++) {
        		asset = (Asset)assets.get(i); 
				assetJson = new JSONObject();
        		 
				assetJson.put("id", asset.getId());
       		 	assetJson.put("projectID", asset.getProjectID());
       		 	assetJson.put("keyField", asset.getKeyField());
       		 	assetJson.put("asset", asset.getAsset());
       		 	assetJson.put("description", asset.getDescription());
       		 	assetJson.put("brand", asset.getBrand());
       		 	assetJson.put("model", asset.getModel());
       		 	assetJson.put("serial", asset.getSerial());
       		 	assetJson.put("acquisitionDate", asset.getAcquisitionDate().toString());
       		 	assetJson.put("acquisitionType", asset.getAcquisitionType());
            	assetJson.put("invoice", asset.getInvoice());
            	assetJson.put("price", asset.getPrice());
            	assetJson.put("tax", asset.getTax());
            	assetJson.put("cost", asset.getCost());
            	assetJson.put("currentValue", asset.getCurrentValue());
            	assetJson.put("accountingDepreciation", asset.getAccountingDepreciation());
            	assetJson.put("fiscalDepreciation", asset.getFiscalDepreciation());
            	assetJson.put("lastDepartmentID", asset.getLastDepartmentID());
            	assetJson.put("currentDepartmentID", asset.getCurrentDepartmentID());
            	assetJson.put("costCenterID", asset.getCostCenterID());
            	assetJson.put("acountingAccountID", asset.getAcountingAccountID());
            	assetJson.put("locationID", asset.getLocationID());
            	assetJson.put("locationDetail", asset.getLocationDetail());
            	assetJson.put("comments", asset.getComments());
            	assetJson.put("personalString01", asset.getPersonalString01());
            	assetJson.put("personalString02", asset.getPersonalString02());
            	assetJson.put("personalString03", asset.getPersonalString03());
            	assetJson.put("personalString04", asset.getPersonalString04());
            	assetJson.put("personalString05", asset.getPersonalString05());
            	assetJson.put("personalInt01", asset.getPersonalInt01());
            	assetJson.put("personalInt02", asset.getPersonalInt02());
            	assetJson.put("personalInt03", asset.getPersonalInt03());
            	assetJson.put("personalFloat01", asset.getPersonalFloat01());
            	assetJson.put("personalFloat02", asset.getPersonalFloat02());
            	assetJson.put("personalFloat03", asset.getPersonalFloat03());
            	assetJson.put("statusID", asset.getStatusID());
             	list.add(assetJson);
        	 }
			
			errorCode.put("errorCode", 0);
			errorCode.put("message", Error.getErrorByCode(0));
	        responseBody.put("assets", list);
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
