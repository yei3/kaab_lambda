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
import com.yei3.oox.kaab_inventarios.database.entity.Department;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateDepartment implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Createdepartment starting");
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONObject responseJson = new JSONObject();
        String responseCode = "200";
        JSONObject errorCode = new JSONObject();
    	JSONObject responseBody = new JSONObject();
    	//context.getIdentity().getIdentityId()
        try {
        	JSONObject event = (JSONObject)parser.parse(reader);
        	JSONObject body = (JSONObject)parser.parse((String) event.get("body"));
        	Department department = new Department();
        	
        	department.setCompanyID(toIntExact((long) body.get("companyID")));
        	department.setDepartmentType((String) body.get("departmentType"));
        	department.setDepartmentID(toIntExact((long) body.get("departmentID")));
        	department.setName((String) body.get("name"));
        	department.setHeadDepartment((String) body.get("headDepartment"));
        	department.setStatusID(toIntExact((long) body.get("statusID")));
        	//TODO add cognito >:v
        	department.setCreationUserID(toIntExact((long)body.get("userId")));
        	department.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        	
        	Helper h = new Helper(context);
        	
        	h.insertItem(Department.class, department);
        	errorCode.put("errorCode", 0);
            errorCode.put("message", "Success");
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
