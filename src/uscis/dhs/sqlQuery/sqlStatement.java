package uscis.dhs.sqlQuery;



public class sqlStatement {

	public String selectSQLByTableName(String table_name, String where_clause){
		String selectTestTableSQL = "Select *"
				                  + "  From "+ table_name +" "
				                  + "  "+ where_clause +" ";
		return selectTestTableSQL;
	}
	
}
