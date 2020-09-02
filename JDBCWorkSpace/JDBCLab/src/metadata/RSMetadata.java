package metadata;

import java.sql.*;

import oracle.security.o5logon.d;

public class RSMetadata {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			
			ResultSetMetaData rsMD = rs.getMetaData();
			for(int i = 1; i<= rsMD.getColumnCount(); i++) {
				String columnName = rsMD.getColumnName(i);
				System.out.println(columnName);
				System.out.println(rsMD.getColumnTypeName(i));
				System.out.println(rsMD.getColumnDisplaySize(i));
				
				int nullable = rsMD.isNullable(i);
				if (nullable == ResultSetMetaData.columnNullable) {
					System.out.println("可以是空直");
				}else if (nullable == ResultSetMetaData.columnNoNulls) {
					System.out.println("可以是空直");
				}else if(nullable == ResultSetMetaData.columnNullableUnknown) {
					System.out.println("UNKNOWN");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
