package statement;

import java.sql.*;

public class PstmtDelete {
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			PreparedStatement pstmt = connection.prepareStatement("delete from emp where empno = ?");
			pstmt.setInt(1, 1012);
			pstmt.executeUpdate();
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
}
