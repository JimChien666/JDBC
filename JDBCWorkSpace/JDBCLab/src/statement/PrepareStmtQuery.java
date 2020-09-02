package statement;

import java.sql.*;

public class PrepareStmtQuery {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			PreparedStatement pstmt = connection.prepareStatement("select * from emp where ename = ?");//compile sql
			pstmt.setString(1, "小明");//設定第一個問號為1001
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getNString("ename"));
			}
			pstmt.clearParameters();//重要
			pstmt.setString(1, "老來旺");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getNString("ename"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
