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
import com.yei3.oox.kaab_inventarios.database.entity.File;
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateFile implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Createfile starting");
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
        	File file = new File();
        	
        	file.setMime((String)body.get("mime"));
        	file.setUrl((String) body.get("url"));
        	file.setStatusID(toIntExact((long) body.get("statusID")));
        	//TODO add cognito >:v
        	User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
        	if (user != null) {
        		file.setCreationUserID(toIntExact((long)body.get("userId")));
        		file.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
            	Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
            	if (status != null) {
            		h.insertItem(File.class, file);
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
