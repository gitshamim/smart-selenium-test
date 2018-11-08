package uscis.dhs.database;

import static org.junit.Assume.assumeNoException;

import java.sql.*;

import org.apache.log4j.Logger;
import org.postgresql.*;

import uscis.dhs.support.BaseAPPMethods;


public class databaseConnection {

	private static Logger dblog = Logger.getLogger("DBLOGS");
    static BaseAPPMethods data = new BaseAPPMethods();
    
    public static String exp_pg_hdr = data.testData().getProperty("home_page_hdr");
	
	public static String db_USER = data.configProp().getProperty("db_user_id");
	public static String db_PASS = data.configProp().getProperty("db_user_pass");
	public static String db_HOST = data.configProp().getProperty("db_host_name");
	public static String db_NAME = data.configProp().getProperty("db_database_name");
	public static String db_PORT = data.configProp().getProperty("db_port");
	public static String JDBC_DRIVER = "org.postgresql.Driver";
	public static String DB_URL = null;
	
	public ResultSet query_database(String qry) throws Exception {
		ResultSet results = connectDatabase(qry);
		return results;
	}
	
	public static String constructDB_URL() throws Exception {
		String constructDB_URL = null;
		if (db_HOST.isEmpty()) {
			dblog.error("Database host name is missing in the config.properties file");
		} else if (db_PORT.isEmpty()) {
			dblog.error("Database port number is missing in the config.properties file");
		} else if (db_NAME.isEmpty()) {
			dblog.error("Database db name is missing in the config.properties file");
		} else {
			constructDB_URL = "jdbc:postgresql://"+db_HOST+":"+db_PORT+"/"+db_NAME+"";			
		}
		return constructDB_URL;
	}	

    public ResultSet connectDatabase(String sql_statement) throws Exception {
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	
    	DB_URL = constructDB_URL();
    
    	try {
    		Class.forName("org.postgresql.Driver");
    		System.out.println("Connecting to database…");
    		conn = DriverManager.getConnection(DB_URL,db_USER,db_PASS);
    		System.out.println("Creating statement…");
    		stmt = conn.createStatement();
    		String sql;
    		sql = sql_statement;
    		rs = stmt.executeQuery(sql);   		
    	} catch(SQLException se) {
    		se.printStackTrace();
    	} catch(Exception e){
    		e.printStackTrace();
    	} finally {
    		try {
    			if (conn!=null) {
    				conn.close();
    			}
    		}catch(SQLException se) {
    			se.printStackTrace();
    		}
    		System.out.println("Database connection closed");
    	}
		return rs;
    }
    	

	
	

}


