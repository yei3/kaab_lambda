package com.yei3.oox.kaab_inventarios.function.getentitiesbyfilters;

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
import com.yei3.oox.kaab_inventarios.database.entity.FinalAsset;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetFinalAssetsByFilters implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getfinalassetsbyfilters starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	JSONArray list = new JSONArray();
        try {
        	JSONObject event = (JSONObject)parser.parse(reader);
        	
        	if (event.get("multiValueQueryStringParameters") != null) {
                JSONObject pps = (JSONObject)event.get("multiValueQueryStringParameters");
                
                if ( pps.get("column") != null && pps.get("condition") != null && pps.get("value") != null) {
                	JSONArray columns = (JSONArray) pps.get("column");
                	JSONArray conditions = (JSONArray) pps.get("condition");
                	JSONArray values = (JSONArray) pps.get("value");
                	
                	if (columns.size() == conditions.size() && columns.size() == values.size()) {
                		String [] columnsString = Utils.getStringArrayByJsonArray(columns);
                		String [] conditionsString = Utils.getStringArrayByJsonArray(conditions);
                		String [] valuesString = Utils.getStringArrayByJsonArray(values);
                		Helper h = new Helper(context);
                    	List<Object> finalAssets = (List<Object>)h.getListByConditions(FinalAsset.class, columnsString, conditionsString, valuesString);
                    	FinalAsset finalAsset;
                    	JSONObject finalAssetJson;
                    	
            			for (int i = 0; i < finalAssets.size(); i++) {
                    		finalAsset = (FinalAsset)finalAssets.get(i); 
            				finalAssetJson = new JSONObject();
                    		 
            				finalAssetJson.put("id", finalAsset.getId());
                   		 	finalAssetJson.put("projectID", finalAsset.getProjectID());
                   		 	finalAssetJson.put("registrationSessionID", finalAsset.getRegistrationSessionID());
                   		 	finalAssetJson.put("lastRegistration", finalAsset.isLastRegistration());
                   		 	finalAssetJson.put("keyField", finalAsset.getKeyField());
                   		 	finalAssetJson.put("asset", finalAsset.getAsset());
                   		 	finalAssetJson.put("description", finalAsset.getDescription());
                   		 	finalAssetJson.put("brand", finalAsset.getBrand());
                   		 	finalAssetJson.put("model", finalAsset.getModel());
                   		 	finalAssetJson.put("serial", finalAsset.getSerial());
                        	finalAssetJson.put("acquisitionDate", finalAsset.getAcquisitionDate());
                        	finalAssetJson.put("acquisitionType", finalAsset.getAcquisitionType());
                        	finalAssetJson.put("invoice", finalAsset.getInvoice());
                        	finalAssetJson.put("price", finalAsset.getPrice());
                        	finalAssetJson.put("tax", finalAsset.getTax());
                        	finalAssetJson.put("cost", finalAsset.getCost());
                        	finalAssetJson.put("currentValue", finalAsset.getCurrentValue());
                        	finalAssetJson.put("accountingDepreciation", finalAsset.getAccountingDepreciation());
                        	finalAssetJson.put("fiscalDepreciation", finalAsset.getFiscalDepreciation());
                        	finalAssetJson.put("lastDepartmentID", finalAsset.getLastDepartmentID());
                        	finalAssetJson.put("currentDepartmentID", finalAsset.getCurrentDepartmentID());
                        	finalAssetJson.put("costCenterID", finalAsset.getCostCenterID());
                        	finalAssetJson.put("acountingAccountID", finalAsset.getAcountingAccountID());
                        	finalAssetJson.put("locationID", finalAsset.getLocationID());
                        	finalAssetJson.put("locationDetail", finalAsset.getLocationDetail());
                        	finalAssetJson.put("comments", finalAsset.getComments());
                        	finalAssetJson.put("image", finalAsset.getImage());
                        	finalAssetJson.put("personalString01", finalAsset.getPersonalString01());
                        	finalAssetJson.put("personalString02", finalAsset.getPersonalString02());
                        	finalAssetJson.put("personalString03", finalAsset.getPersonalString03());
                        	finalAssetJson.put("personalString04", finalAsset.getPersonalString04());
                        	finalAssetJson.put("personalString05", finalAsset.getPersonalString05());
                        	finalAssetJson.put("personalInt01", finalAsset.getPersonalInt01());
                        	finalAssetJson.put("personalInt02", finalAsset.getPersonalInt02());
                        	finalAssetJson.put("personalInt03", finalAsset.getPersonalInt03());
                        	finalAssetJson.put("personalFloat01", finalAsset.getPersonalFloat01());
                        	finalAssetJson.put("personalFloat02", finalAsset.getPersonalFloat02());
                        	finalAssetJson.put("personalFloat03", finalAsset.getPersonalFloat03());
                        	finalAssetJson.put("statusID", finalAsset.getStatusID());
                         	list.add(finalAssetJson);
                    	 }
                	}else {
                    	errorCode.put("errorCode", -2);
                        errorCode.put("message", "This service requires matching columns and values.");
                    }
                }else {
                	errorCode.put("errorCode", -2);
                    errorCode.put("message", "This service requires matching columns and values.");
                }
        	}else {
            	errorCode.put("errorCode", -2);
                errorCode.put("message", "This service requires matching columns and values.");
            }
			
			errorCode.put("errorCode", 0);
	        errorCode.put("message", "Success");
	        responseBody.put("finalAssets", list);
        } catch(Exception ex) {
        	errorCode.put("errorCode", -100);
            errorCode.put("message", ex.getMessage());
            logger.log("Error: " + ex.getMessage());
        }
        
        responseBody.put("error", errorCode);
    	JSONObject headerJson = new JSONObject();
        headerJson.put("x-custom-header", "my custom header value");
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
