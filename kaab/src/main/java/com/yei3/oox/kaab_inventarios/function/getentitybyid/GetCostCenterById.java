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
import com.yei3.oox.kaab_inventarios.database.entity.Address;
import com.yei3.oox.kaab_inventarios.database.entity.CostCenter;
import com.yei3.oox.kaab_inventarios.database.util.Helper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetCostCenterById implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        logger.log("Getcostcenterbyid starting");
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
                	CostCenter costCenter = (CostCenter)h.getItemById(CostCenter.class,Integer.parseInt((String) pps.get("id")));
                	
                	 if (costCenter != null) {
                		 JSONObject costCenterJson = new JSONObject();

                		 costCenterJson.put("id", costCenter.getId());
                		 costCenterJson.put("companyID", costCenter.getCompanyID());
                		 costCenterJson.put("name", costCenter.getName());
                		 costCenterJson.put("description", costCenter.getDescription());
                		 costCenterJson.put("statusID", costCenter.getStatusID());
                     	
                     	errorCode.put("errorCode", 0);
                        errorCode.put("message", "Success");
                        
                        responseBody.put("costCenter", costCenterJson);
                	 }else {
                		 errorCode.put("errorCode", 1);
                         errorCode.put("message", "The cost center corresponding to id = " + pps.get("id") + " does not exists.");
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
