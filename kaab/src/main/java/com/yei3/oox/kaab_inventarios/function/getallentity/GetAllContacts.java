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
import com.yei3.oox.kaab_inventarios.database.entity.Contact;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import com.yei3.oox.kaab_inventarios.util.Error;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAllContacts implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getallcontacts starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	JSONArray list = new JSONArray();
    	
        try {
        	Helper h = new Helper(context);
        	List<Object> contacts = (List<Object>)h.getAllEntity(Contact.class);
        	Contact contact;
        	JSONObject contactJson;
        	
			for (int i = 0; i < contacts.size(); i++) {
        		contact = (Contact)contacts.get(i); 
				contactJson = new JSONObject();
        		 
				contactJson.put("id", contact.getId());
       		 	contactJson.put("companyID", contact.getCompanyID());
       		 	contactJson.put("names", contact.getNames());
       		 	contactJson.put("lastname", contact.getLastname());
       		 	contactJson.put("middlename", contact.getMiddlename());
       		 	contactJson.put("position", contact.getPosition());
       		 	contactJson.put("phoneType", contact.getPhoneType());
       		 	contactJson.put("phoneNumber", contact.getPhoneNumber());
       		 	contactJson.put("phoneType2", contact.getPhoneType2());
       		 	contactJson.put("phoneNumber2", contact.getPhoneNumber2());
       		 	contactJson.put("emailType", contact.getEmailType());
       		 	contactJson.put("email", contact.getEmail());
       		 	contactJson.put("emailType2", contact.getEmailType2());
       		 	contactJson.put("email2", contact.getEmail2());
            	contactJson.put("statusID", contact.getStatusID());
             	list.add(contactJson);
        	 }
			
			errorCode.put("errorCode", 0);
			errorCode.put("message", Error.getErrorByCode(0));
	        responseBody.put("contacts", list);
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
