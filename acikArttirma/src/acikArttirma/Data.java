package acikArttirma;

import java.sql.Connection;
import java.sql.DriverManager;

public class Data {
	public static Connection getConnection() {
	    try  {
	    	   Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/proje","root","");
	        return con;
	     }
	    catch(Exception ex) {
	        System.out.println("Database.getConnection() Error -->" + ex.getMessage());
	        return null;
	    }
	}
	 public static void close(Connection con) {
	    try  {
	        con.close();
	    }
	    catch(Exception ex) {
	    }
	}
	}