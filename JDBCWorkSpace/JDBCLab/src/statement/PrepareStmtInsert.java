package statement;

import java.math.BigDecimal;
import java.sql.*;
import java.util.GregorianCalendar;

public class PrepareStmtInsert {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			
			PreparedStatement pstmt = connection.prepareStatement("insert into emp (empno, ename, salary, job, hiredate, deptno, commission) values (?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, 1200);
			pstmt.setString(2, "Jim");
			pstmt.setBigDecimal(3, new BigDecimal("2000"));
			pstmt.setString(4, "Student");
			GregorianCalendar calendar = new GregorianCalendar(2020,2,10);
			Date date = new Date(calendar.getTimeInMillis());
			pstmt.setDate(5, date);
			pstmt.setInt(6, 30);
			pstmt.setBigDecimal(7, new BigDecimal(0.0));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
