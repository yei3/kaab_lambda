package com.yei3.oox.kaab_inventarios.function.createentity;

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
import com.yei3.oox.kaab_inventarios.database.entity.Address;
import com.yei3.oox.kaab_inventarios.database.entity.Department;
import com.yei3.oox.kaab_inventarios.database.entity.Location;
import com.yei3.oox.kaab_inventarios.database.entity.LocationAddress;
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateLocationAddress implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("CreatelocationAddress starting");
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
        	LocationAddress locationAddress = new LocationAddress();
        	
        	Location location = (Location)h.getItemById(Location.class, toIntExact((long) body.get("locationID")));
        	if (location != null) {
        		locationAddress.setLocationID(toIntExact((long) body.get("locationID")));
        		Address address = (Address)h.getItemById(Address.class, toIntExact((long)  body.get("addressID")));
        		if (address != null) {
        			locationAddress.setAddressID(toIntExact((long)  body.get("addressID")));
        			Department department = (Department)h.getItemById(Department.class, toIntExact((long)  body.get("departmentID")));
        			if (department != null) {
        				locationAddress.setDepartmentID(toIntExact((long)  body.get("departmentID")));
        				Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
        	        	if (status != null) {
        	        		locationAddress.setStatusID(toIntExact((long) body.get("statusID")));
                        	//TODO add cognito >:v
        	        		User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
        	            	if (user != null) {
        	            		locationAddress.setCreationUserID(toIntExact((long)body.get("userId")));
                            	locationAddress.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
                            	
                            	h.insertItem(LocationAddress.class, locationAddress);
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
        				errorCode.put("errorCode", -15);
                        errorCode.put("message", Error.getErrorByCode(-15));
        			}
        		}else {
        			errorCode.put("errorCode", -13);
                    errorCode.put("message", Error.getErrorByCode(-13));
        		}
        	}else {
        		errorCode.put("errorCode", -11);
                errorCode.put("message", Error.getErrorByCode(-11));
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
