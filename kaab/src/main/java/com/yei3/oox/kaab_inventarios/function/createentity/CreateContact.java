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
import com.yei3.oox.kaab_inventarios.database.entity.Company;
import com.yei3.oox.kaab_inventarios.database.entity.Contact;
import com.yei3.oox.kaab_inventarios.database.entity.Status;
import com.yei3.oox.kaab_inventarios.database.entity.User;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateContact implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Createcontact starting");
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
        	Contact contact = new Contact();
        	
        	Company company = (Company)h.getItemById(Company.class, toIntExact((long) body.get("companyID")));
        	if (company != null) {
        		contact.setCompanyID(toIntExact((long) body.get("companyID")));
            	contact.setNames((String) body.get("names"));
            	contact.setLastname((String) body.get("lastname"));
            	contact.setMiddlename((String) body.get("middlename"));
            	contact.setPosition((String) body.get("position"));
            	contact.setPhoneType((String) body.get("phoneType"));
            	contact.setPhoneNumber((String) body.get("phoneNumber"));
            	contact.setPhoneType2((String) body.get("phoneType2"));
            	contact.setPhoneNumber2((String) body.get("phoneNumber2"));
            	contact.setEmailType((String) body.get("emailType"));
            	contact.setEmail((String) body.get("email"));
            	contact.setEmailType2((String) body.get("emailType2"));
            	contact.setEmail2((String) body.get("email2"));
            	Status status = (Status)h.getItemById(Status.class, toIntExact((long) body.get("statusID")));
            	if (status != null) {
            		contact.setStatusID(toIntExact((long) body.get("statusID")));
                	//TODO add cognito >:v
            		User user = (User)h.getItemById(User.class, toIntExact((long)body.get("userId")));
                	if (user != null) {
                		contact.setCreationUserID(toIntExact((long)body.get("userId")));
                    	contact.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
                    	
                    	h.insertItem(Contact.class, contact);
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
        		errorCode.put("errorCode", -4);
        		errorCode.put("message", Error.getErrorByCode(-4));
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
