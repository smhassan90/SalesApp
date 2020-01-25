package com.greenstar.controller.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FFMDatabase {
	
	private String url = null;
	private String  user = "smpsys";
    private String  password = "smpsysgsit2007";

    /**
     * 
     */
	public FFMDatabase() {
		super();
		// TODO Auto-generated constructor stub
		//this.url = "jdbc:oracle:thin:@103.31.80.214:1522:orcl";			// LIVE-Environment
		
		//this.url = "jdbc:oracle:thin:@192.168.0.42:1521:ORCL";			// TEST-Environment Doosra
				 
		this.url = "jdbc:oracle:thin:@192.168.0.27:1521:ORCL1";			// live new-Environment Doosra
	}
	
	/**
	 * 
	 * @return
	 */
	public Statement getDBConnection(){
		
		try{
			
	        Connection  connection = DriverManager.getConnection(this.url , this.user , this.password);
	        
	        Statement  statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
	        return statement;
			
		}
		catch(Exception ex){
			System.out.println("getDBConnection: " + "");
			ex.printStackTrace();
		}
		
		
		return null;
	}
	
	
	/**
	 * 
	 * @param commit
	 * @return
	 */
	public Connection getDBConnection(boolean commit){
		
		try{
			
	        Connection  connection = DriverManager.getConnection(this.url , this.user , this.password);
	        
	        connection.setAutoCommit(commit);
	        
	        return connection;
			
		}
		catch(Exception ex){
			System.out.println("getDBConnection with commit status: " + "");
			ex.printStackTrace();
		}
		
		
		return null;
	}
	
	
	

}
