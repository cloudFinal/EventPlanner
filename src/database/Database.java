package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.sql.DataSource;

public class Database
{
    String jdbcUrl ="jdbc:mysql://cloudfinal.cvdk3xvzipiw.us-east-1.rds.amazonaws.com:3306/cloudFinal";
    static Connection conn;
	private Statement stmt;
	//private Query query;
	public Database(){
		try {
			connect();
			test();
			for(String s:test()){
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void connect() throws SQLException{
		try {
			Class.forName ("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userid = "final";
	    String password = "88522712";
	    conn = DriverManager.getConnection (jdbcUrl,userid,password);
    }
	public void close(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	public ArrayList<String> test() throws SQLException{
			stmt=conn.createStatement();
			ArrayList<String> result = new ArrayList<String>();
			ResultSet rset = stmt.executeQuery("show tables");
			while(rset.next()){
				result.add(rset.getString(1));
			}
			return result;
	}
	public boolean register(String username,String password){
		try {
			stmt=conn.createStatement();
			stmt.execute(QueryGenerator.insertQ("users","user_id","String",username,"password","String",password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
}
