package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DbConnection;

public class LoginModel {
private Connection connection;

public LoginModel() {
	try {
		this.connection=DbConnection.getConnection();
	}catch(SQLException e) {
		e.getMessage();
	}
	if(this.connection==null) {
		System.out.println("error");
	}
}
public boolean isDatabaseConnected() {
	return this.connection!=null;

}
public boolean isLogin(String name,String password,String division) {
	PreparedStatement pr = null;
	ResultSet res = null;
	String sqlQuery="SELECT * FROM login WHERE username=? AND password=? AND division=?";
	try {
	pr=this.connection.prepareStatement(sqlQuery);
	pr.setString(1, name);
	pr.setString(2, password);
	pr.setString(3, division);
	res=pr.executeQuery();
	if(res.next()) {
		return true;
	}
	return false;
	}catch(SQLException e) {
		System.out.println("hhh" + e.getMessage());
		return false;
	}finally {
		try {
			pr.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
}
