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
import com.yei3.oox.kaab_inventarios.database.entity.Project;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetProjectById implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getprojectbyid starting");
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
                	Project project = (Project)h.getItemById(Project.class,Integer.parseInt((String) pps.get("id")));
                	
                	 if (project != null) {
                		 JSONObject projectJson = new JSONObject();

             			projectJson.put("id", project.getId());
                		 projectJson.put("companyID", project.getCompanyID());
                		 projectJson.put("contactID", project.getContactID());
                		 projectJson.put("name", project.getName());
                		 projectJson.put("statusID", project.getStatusID());
                     	
                     	errorCode.put("errorCode", 0);
                        errorCode.put("message", "Success");
                        
                        responseBody.put("project", projectJson);
                	 }else {
                		 errorCode.put("errorCode", 1);
                         errorCode.put("message", "The project corresponding to id = " + pps.get("id") + " does not exists.");
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
