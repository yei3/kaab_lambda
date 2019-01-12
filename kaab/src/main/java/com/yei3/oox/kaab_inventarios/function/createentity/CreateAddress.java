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
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateAddress implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Createaddress starting");
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
        	Address address = new Address();
        	
        	address.setAddressType((String)body.get("addressType"));
        	address.setRoadType((String) body.get("roadType"));
        	address.setRoadName((String) body.get("roadName"));
        	address.setOutdoorNumber((String) body.get("outdoorNumber"));
        	address.setIndoorNumber((String) body.get("indoorNumber"));
        	address.setColony((String) body.get("colony"));
        	address.setLocation((String) body.get("location"));
        	address.setMunicipality((String) body.get("municipality"));
        	address.setCp((String) body.get("cp"));
        	address.setState((String) body.get("state"));
        	address.setPhoneType((String) body.get("phoneType"));
        	address.setPhoneNumber((String) body.get("phoneNumber"));
        	address.setPhoneType2((String) body.get("phoneType2"));
        	address.setPhoneNumber2((String) body.get("phoneNumber2"));
        	address.setStatusID(toIntExact((long) body.get("statusID")));
        	//TODO add cognito >:v
        	User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
        	if (user != null) {
        		address.setCreationUserID(toIntExact((long)body.get("userId")));
            	address.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
            	Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
            	if (status != null) {
            		h.insertItem(Address.class, address);
                	errorCode.put("errorCode", 0);
                	errorCode.put("message", Error.getErrorByCode(0));
            	}else {
            		errorCode.put("errorCode", -6);
            		errorCode.put("message", Error.getErrorByCode(-6));
            	}
        	}else {
        		errorCode.put("errorCode", -5);
        		errorCode.put("message", Error.getErrorByCode(-5));
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
