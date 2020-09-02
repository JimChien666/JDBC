package statement;

import java.math.BigDecimal;
import java.sql.*;

public class CallableStmtInOutParam {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			CallableStatement cstmt = connection.prepareCall("{call sum_salary(?,?)}");
			cstmt.setInt(1, 10);
			cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
			cstmt.execute();
			BigDecimal summary = cstmt.getBigDecimal(2);
			System.out.println(summary);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
