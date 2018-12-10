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
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAllUsers implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getallaccountingaccount starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	JSONArray list = new JSONArray();
    	
        try {
        	Helper h = new Helper(context);
        	List<Object> users = (List<Object>)h.getAllEntity(User.class);
        	User user;
        	JSONObject userJson;
        	
			for (int i = 0; i < users.size(); i++) {
        		user = (User)users.get(i); 
				userJson = new JSONObject();
        		 
				userJson.put("id", user.getId());
       		 	userJson.put("role", user.getRole());
       		 	userJson.put("user", user.getUser());
       		 	userJson.put("names", user.getNames());
       		 	userJson.put("lastname", user.getLastname());
       		 	userJson.put("middlename", user.getMiddlename());
       		 	userJson.put("statusID", user.getStatusID());
             	list.add(userJson);
        	 }
			
			errorCode.put("errorCode", 0);
	        errorCode.put("message", "Success");
	        responseBody.put("users", list);
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
