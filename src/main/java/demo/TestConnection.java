package demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";

		try {
			Connection con =  DriverManager.getConnection(url, user, user);
			
			System.out.println(con);
			
			System.out.println("Success");

		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("ERROR");
		}

	}

}
