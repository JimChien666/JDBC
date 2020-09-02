package resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ResultUpdateLab {
	public static void main(String[] args) {
		try (
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery("select e.* from emp e");
				) {
				rs.moveToInsertRow();
				rs.updateInt("empno", 1103);
				rs.updateString("ename", "小明");
				rs.updateString("job", "工讀生");
				
				Calendar calendar = new GregorianCalendar(2020,0,1);
				java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
				rs.updateDate("hiredate", date);
				rs.updateLong("salary", 10000);
				rs.updateInt("commission", 0);
				rs.updateInt("deptno", 30);
				rs.insertRow();
		
			
			
			
			}
			
			
		 	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}

}
