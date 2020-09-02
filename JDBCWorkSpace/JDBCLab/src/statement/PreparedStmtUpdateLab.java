package statement;

import java.math.BigDecimal;
import java.sql.*;

public class PreparedStmtUpdateLab {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp left join dept on emp.deptno=dept.deptno");
			BigDecimal oneHundred = new BigDecimal(100);
			PreparedStatement pstmt = connection.prepareStatement("update emp set commission = ? where empno = ?");
			while (rs.next()) {
				if (rs.getString("location").equals("台北")) {
					BigDecimal commission = rs.getBigDecimal("commission");
//					BigDecimal newcommission = commission.add(oneHundred);
					commission = commission.add(oneHundred);
					int empno = rs.getInt("empno");
					pstmt.setBigDecimal(1, commission);
					pstmt.setInt(2, empno);
					int updated = pstmt.executeUpdate();
					if (updated > 0) {
						System.out.println("OK");
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
