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
	public Helper(Context context) {
		this.connection = Configuration.getConnection(context);
		logger = context.getLogger();
	}
	
	public Object getItemById(Class cls, int id){
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
		}
	      return res;
	}
	
	public List<Object> getListByConditions(Class cls, String[] columns, String[] conditions, String[] values){
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
		}
		return list;
	}
	
	public List<Object> getAllEntity(Class cls){
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
		}
		return list;
	}
	
	public void insertItem(Class cls, Object obj) {
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
				query += columns[i];
				query += (i + 1) == columns.length ? ") VALUES(" : ", "; 
			}
			for (int i = 0; i < columns.length; i++) {
				method = cls.getDeclaredMethod("get" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1));
				query += (method.invoke(obj) == null ? "null" : "'" + method.invoke(obj).toString() + "'");
				query += (i + 1) == columns.length ? ")" : ", "; 
			}
			logger.log(query);
			PreparedStatement psStatement = connection.prepareStatement(query);
			int ex = psStatement.executeUpdate();
			logger.log(ex == 1 ? "Insert exitoso" : "Insert fallido");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Error: " + e.getMessage());
		}
	}
	
	public void updateItem(Class cls, Object obj) {
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
		}
	}
}
