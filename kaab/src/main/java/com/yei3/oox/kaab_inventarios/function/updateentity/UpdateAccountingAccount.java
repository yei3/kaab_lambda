package com.yei3.oox.kaab_inventarios.function.updateentity;

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
import com.yei3.oox.kaab_inventarios.database.entity.AccountingAccount;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateAccountingAccount implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("UpdateaccountingAccount starting");
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
        	AccountingAccount accountingAccount = (AccountingAccount)h.getItemById(AccountingAccount.class, toIntExact((long)body.get("id")));
        	if (accountingAccount != null) {
        		accountingAccount.setCompanyID(toIntExact((long) body.get("companyID")));
            	accountingAccount.setKey((String) body.get("key"));
            	accountingAccount.setName((String) body.get("name"));
            	accountingAccount.setDescription((String) body.get("description"));
            	accountingAccount.setStatusID(toIntExact((long) body.get("statusID")));
            	//TODO add cognito >:v
            	accountingAccount.setLastModUserID(toIntExact((long)body.get("userId")));
            	accountingAccount.setLastModDateTime(new Timestamp(System.currentTimeMillis()));
            	
            	h.updateItem(AccountingAccount.class, accountingAccount);
            	errorCode.put("errorCode", 0);
                errorCode.put("message", "Success");
        	}else {
        		errorCode.put("errorCode", -3);
                errorCode.put("message", "The given id does not exists.");
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
