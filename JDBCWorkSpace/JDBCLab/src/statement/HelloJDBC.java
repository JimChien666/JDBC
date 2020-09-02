package statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC {
	public static void main(String[] args) {
		try (
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");
				Statement stmt = connection.createStatement();
				
				) {
			int updated = stmt.executeUpdate("Update emp set salary = 1000 where empno = 1001;");
			if (updated > 0) {
				System.out.println("更新成功");
			}else {
				System.out.println();
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}

}
