package com.yei3.oox.kaab_inventarios.database.util;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Helper {
	private Connection connection;
	static LambdaLogger logger;
	public Helper(Context context) throws Exception{
		this.connection = Configuration.getConnection(context);
		logger = context.getLogger();
	}
	
	public Object getItemById(Class cls, int id) throws Exception{
		Statement stmt;
		String query = "SELECT * FROM ";
		Object res = null;
		try {
			stmt = connection.createStatement();
			Method method = cls.getDeclaredMethod("getTable");
			query += (String)method.invoke(cls.newInstance()) + " WHERE id = " + id;
			System.out.println(query);
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			ResultSet resultSet = psStatement.executeQuery();
			System.out.println("Rows leidas: " + resultSet.getFetchSize());
			logger.log("Rows leídas: " + resultSet.getFetchSize());
			res = Configuration.resultSetToObject(resultSet, cls);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
	      return res;
	}
	
	public List<Object> getListByConditions(Class cls, String[] columns, String[] conditions, String[] values) throws Exception{
		List<Object> list = null;
		Statement stmt;
		String query = "SELECT * FROM ";
		Object res = null;
		try {
			stmt = connection.createStatement();
			Method method = cls.getDeclaredMethod("getTable");
			query += (String)method.invoke(cls.newInstance()) + " WHERE ";
			for (int i = 0; i < columns.length; i++) {
				query += columns[i] + " " + conditions[i] + " " + values[i];
				if ((i + 1) < columns.length) {
					query += " AND ";
				}
			}
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			ResultSet resultSet = psStatement.executeQuery();
			logger.log("Rows leídas: " + resultSet.getFetchSize());
			list = Configuration.resultSetToList(resultSet, cls);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public List<Object> getAllEntity(Class cls) throws Exception{
		List<Object> list = null;
		Statement stmt;
		String query = "SELECT * FROM ";
		Object res = null;
		try {
			stmt = connection.createStatement();
			Method method = cls.getDeclaredMethod("getTable");
			query += (String)method.invoke(cls.newInstance());
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			ResultSet resultSet = psStatement.executeQuery();
			logger.log("Rows leídas: " + resultSet.getFetchSize());
			list = Configuration.resultSetToList(resultSet, cls);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
		return list;
	}
	
	public void insertItem(Class cls, Object obj) throws Exception{
		Statement stmt;
		String query = "INSERT INTO ";
		Object res = null;
		try {
			stmt = connection.createStatement();
			Method method = cls.getDeclaredMethod("getTable");
			query += (String)method.invoke(cls.newInstance());
			method = cls.getDeclaredMethod("getColumns");
			String [] columns = (String[])method.invoke(cls.newInstance());
			query += "(";
			for (int i = 0; i < columns.length; i++) {
				query += "`" + columns[i] + "`";
				query += (i + 1) == columns.length ? ") VALUES(" : ", "; 
			}
			for (int i = 0; i < columns.length; i++) {
				method = cls.getDeclaredMethod("get" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1));
				if (method.invoke(obj) != null) {
					switch (method.invoke(obj).toString()) {
					case "false":
						query += "'" + 0 + "'";
						break;
					case "true":
						query += "'" + 1 + "'";
						break;
					default:
						query += ((method.invoke(obj) == null || method.invoke(obj).toString().equals("0")) ? "null" : "'" + method.invoke(obj).toString() + "'");
					}
				}else {
					query += ((method.invoke(obj) == null || method.invoke(obj).toString().equals("0")) ? "null" : "'" + method.invoke(obj).toString() + "'");
				}
				
				query += (i + 1) == columns.length ? ")" : ", "; 
			}
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			int ex = psStatement.executeUpdate();
			logger.log(ex == 1 ? "Insert exitoso" : "Insert fallido");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
	}
	
	public void updateItem(Class cls, Object obj) throws Exception{
		Statement stmt;
		String query = "UPDATE ";
		Object res = null;
		try {
			stmt = connection.createStatement();
			Method method = cls.getDeclaredMethod("getTable");
			query += (String)method.invoke(cls.newInstance());
			method = cls.getDeclaredMethod("getColumns");
			String [] columns = (String[])method.invoke(cls.newInstance());
			query += " SET ";
			for (int i = 0; i < columns.length; i++) {
				method = cls.getDeclaredMethod("get" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1));
				query += columns[i] + " = " + (method.invoke(obj) == null ? "null" : "'" + method.invoke(obj).toString() + "'");
				query += ((i + 1) == columns.length ? "" : ", "); 
			}
			method = cls.getDeclaredMethod("getId");
			query += " WHERE id = " + method.invoke(obj).toString();
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			int ex = psStatement.executeUpdate();
			logger.log(ex == 1 ? "Update exitoso" : "Update fallido");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
			throw e;
		}
	}
}
