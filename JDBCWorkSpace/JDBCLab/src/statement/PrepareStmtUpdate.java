package statement;

import java.math.BigDecimal;
import java.sql.*;

public class PrepareStmtUpdate {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			PreparedStatement pstmt = connection.prepareStatement("update emp set salary = ? where empno = ?");
			int sal = 10000;
			for (int a = 1101; a<=1103;a++) {
				pstmt.setBigDecimal(1, new BigDecimal(sal));
				pstmt.setInt(2, a);
				int updated = pstmt.executeUpdate();
				if (updated > 0) {
					System.out.println("更新到了");
				}
				pstmt.clearParameters();//要執行
				sal= sal + 1000;
			}
		}
			
//			BigDecimal salary = new BigDecimal("20000.3");
//			BigDecimal newSalary = salary.divide(new BigDecimal("3"), 10, BigDecimal.ROUND_DOWN);
//			System.out.println(newSalary);
		 catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
