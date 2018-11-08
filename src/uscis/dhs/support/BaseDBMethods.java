package uscis.dhs.support;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import uscis.dhs.database.databaseConnection;

public class BaseDBMethods extends BaseTestMethods{
	static databaseConnection DB_CONN = new databaseConnection();
	private static Logger dblog = Logger.getLogger("DBLOGS");
	
	public void query_select_by_table_name(String qry) throws Exception {
		ResultSet res = DB_CONN.query_database(qry);
		if(res.next()){
			String name  = res.getString(1);
			String email = res.getString(2);
			String phone = res.getString(3);
			dblog.info("name:   " + name );
			dblog.info("email:  " + email );
			dblog.info("phone:  " + phone );
		} 
		
		
	}
	
}
