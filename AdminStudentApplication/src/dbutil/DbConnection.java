package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
public static final String DB_NAME="schoolsystem.db";
public static final String CONNECTION_STRING="jdbc:sqlite:D:\\Users\\User\\Desktop\\Java Course\\AdminStudentApplication\\"+ DB_NAME;

public static Connection getConnection() throws SQLException {
	return DriverManager.getConnection(CONNECTION_STRING);
	
}
}
