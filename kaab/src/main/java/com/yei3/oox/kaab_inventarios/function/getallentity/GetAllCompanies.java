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
import com.yei3.oox.kaab_inventarios.database.entity.Company;
import com.yei3.oox.kaab_inventarios.database.util.Helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAllCompanies implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getallcompanies starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	JSONArray list = new JSONArray();
    	
        try {
        	Helper h = new Helper(context);
        	List<Object> companies = (List<Object>)h.getAllEntity(Company.class);
        	Company company;
        	JSONObject companyJson;
        	
			for (int i = 0; i < companies.size(); i++) {
        		company = (Company)companies.get(i); 
				companyJson = new JSONObject();
        		 
				companyJson.put("id", company.getId());
       		 	companyJson.put("companyType", company.getCompanyType());
       		 	companyJson.put("industryType", company.getIndustryType());
       		 	companyJson.put("name", company.getName());
       		 	companyJson.put("fiscalID", company.getFiscalID());
       		 	companyJson.put("addressID", company.getAddressID());
            	companyJson.put("statusID", company.getStatusID());
             	list.add(companyJson);
        	 }
			
			errorCode.put("errorCode", 0);
	        errorCode.put("message", "Success");
	        responseBody.put("companies", list);
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
