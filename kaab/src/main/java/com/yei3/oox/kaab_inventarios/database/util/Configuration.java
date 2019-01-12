package com.yei3.oox.kaab_inventarios.database.util;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
	static LambdaLogger logger;
	public static Connection getConnection(Context context) throws SQLException{
		logger = context.getLogger();
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(
		    		System.getenv("host") + ':' + 
		    		System.getenv("port") + '/' + 
		    		System.getenv("db"), 
		    		System.getenv("username"), 
		    		System.getenv("password"));
			//conn = DriverManager.getConnection("jdbc:mysql://devdb2.ckdd3uynsupw.us-west-2.rds.amazonaws.com:3306/kaab", "root", "password");
	    } catch (SQLException e) {
	      e.printStackTrace();
	      logger.log("Error: " + e.getMessage());
	      throw e;
	    }
		return conn;
	}
	
	public static Object resultSetToObject(ResultSet rs, Class cls) throws Exception{
		Object res = null;
		try {
			Method method = cls.getDeclaredMethod("getColumns");
			String [] columns = (String[])method.invoke(cls.newInstance());
			method = cls.getDeclaredMethod("getTypes");
			Class[] types = (Class[])method.invoke(cls.newInstance());
			if (rs.next()) {
				res = cls.newInstance();
				for (int i = 0; i < columns.length; i++) {
					
					method = cls.getDeclaredMethod("set" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1),types[i]);
					method.invoke(res,types[i] == int.class ? (rs.getObject(columns[i]) == null ? 0 : rs.getObject(columns[i])) : rs.getObject(columns[i]));
				}
			}else {
				logger.log("No hay registros.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
		return res;
	}
	
	public static List<Object> resultSetToList(ResultSet rs, Class cls) throws Exception{
		List<Object> res = new ArrayList<Object>();
		try {
			Method method = cls.getDeclaredMethod("getColumns");
			String [] columns = (String[])method.invoke(cls.newInstance());
			method = cls.getDeclaredMethod("getTypes");
			Class[] types = (Class[])method.invoke(cls.newInstance());
			while (rs.next()) {
				Object obj = cls.newInstance();
				for (int i = 0; i < columns.length; i++) {
					logger.log("Debug, " + "set" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1) + " Type: " + types[i]);
					method = cls.getDeclaredMethod("set" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1),types[i]);
					method.invoke(obj,types[i] == int.class ? (rs.getObject(columns[i]) == null ? 0 : rs.getObject(columns[i])) : rs.getObject(columns[i]));
				}
				res.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
		return res;
	}
}
