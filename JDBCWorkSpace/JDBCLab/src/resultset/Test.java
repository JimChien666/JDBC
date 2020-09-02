package resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {

		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
