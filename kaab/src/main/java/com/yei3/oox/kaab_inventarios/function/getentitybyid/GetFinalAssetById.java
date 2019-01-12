package com.yei3.oox.kaab_inventarios.function.getentitybyid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.yei3.oox.kaab_inventarios.database.entity.FinalAsset;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetFinalAssetById implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getfinalassetbyid starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	
        try {
        	JSONObject event = (JSONObject)parser.parse(reader);
        	
        	if (event.get("queryStringParameters") != null) {
                JSONObject pps = (JSONObject)event.get("queryStringParameters");
                
                if ( pps.get("id") != null) {
                	Helper h = new Helper(context);
                	FinalAsset finalAsset = (FinalAsset)h.getItemById(FinalAsset.class,Integer.parseInt((String) pps.get("id")));
                	
                	 if (finalAsset != null) {
                		 JSONObject finalAssetJson = new JSONObject();

                		 finalAssetJson.put("id", finalAsset.getId());
                		 finalAssetJson.put("projectID", finalAsset.getProjectID());
                		 finalAssetJson.put("registrationSessionID", finalAsset.getRegistrationSessionID());
                		 finalAssetJson.put("lastRegistration", finalAsset.getLastRegistration());
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
                     	
                     	errorCode.put("errorCode", 0);
                        errorCode.put("message", "Success");
                        
                        responseBody.put("finalAsset", finalAssetJson);
                	 }else {
                		 errorCode.put("errorCode", 1);
                         errorCode.put("message", "The final asset corresponding to id = " + pps.get("id") + " does not exists.");
                	 }
                    
                }else {
                	errorCode.put("errorCode", -1);
                    errorCode.put("message", "This service requires an id value.");
                }
            }else {
            	errorCode.put("errorCode", -1);
                errorCode.put("message", "This service requires an id value.");
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
