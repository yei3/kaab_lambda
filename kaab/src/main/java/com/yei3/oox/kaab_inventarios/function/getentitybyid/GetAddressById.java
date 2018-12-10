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
import com.yei3.oox.kaab_inventarios.database.entity.Address;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAddressById implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getaddressbyid starting");
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
                	Address address = (Address)h.getItemById(Address.class,Integer.parseInt((String) pps.get("id")));
                	
                	 if (address != null) {
                		 JSONObject addressJson = new JSONObject();
                		 
                     	addressJson.put("id", address.getId());
                     	addressJson.put("addressType", address.getAddressType());
                     	addressJson.put("roadType", address.getRoadType());
                     	addressJson.put("roadName", address.getRoadName());
                     	addressJson.put("outdoorNumber", address.getOutdoorNumber());
                     	addressJson.put("indoorNumber", address.getIndoorNumber());
                     	addressJson.put("colony", address.getColony());
                     	addressJson.put("location", address.getLocation());
                     	addressJson.put("municipality", address.getMunicipality());
                     	addressJson.put("cp", address.getCp());
                     	addressJson.put("state", address.getState());
                     	addressJson.put("phoneType", address.getPhoneType());
                     	addressJson.put("phoneNumber", address.getPhoneNumber());
                     	addressJson.put("phoneType2", address.getPhoneType2());
                     	addressJson.put("phoneNumber2", address.getPhoneNumber2());
                     	addressJson.put("statusID", address.getStatusID());
                     	
                     	errorCode.put("errorCode", 0);
                        errorCode.put("message", "Success");
                        
                        responseBody.put("address", addressJson);
                	 }else {
                		 errorCode.put("errorCode", 1);
                         errorCode.put("message", "The address corresponding to id = " + pps.get("id") + " does not exists.");
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
