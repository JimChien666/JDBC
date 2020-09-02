package metadata;

import java.sql.*;

public class DBMetadata {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			
			DatabaseMetaData dbmd = connection.getMetaData();
			System.out.println(dbmd.getDatabaseProductName());
			System.out.println(dbmd.getDatabaseProductVersion());
			System.out.println(dbmd.getDriverName());
			System.out.println(dbmd.getDriverVersion());
			
			ResultSet rs = dbmd.getTables("xepdb1", "SCOTT", null, new String[] {"TABLE"});
			
			
			while (rs.next()) {
				String name = rs.getNString("table_name");
				System.out.println(name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
