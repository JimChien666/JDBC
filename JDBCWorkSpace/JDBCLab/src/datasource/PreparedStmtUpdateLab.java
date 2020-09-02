package datasource;

import java.math.BigDecimal;
import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;

public class PreparedStmtUpdateLab {

	public static void main(String[] args) {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setMaxTotal(50);//設定POOL中最多有多少connection
		ds.setMaxIdle(50);//最多多少是處於idle狀態
		
		
		try (Connection connection = ds.getConnection();) {
			try {
				
				connection.setAutoCommit(false);
				
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from emp left join dept on emp.deptno=dept.deptno");
				BigDecimal oneHundred = new BigDecimal(100);
				PreparedStatement pstmt = connection.prepareStatement("update emp set commission = ? where empno = ?");
				int count = 0;
				while (rs.next()) {
					
					if (rs.getString("location").equals("台北")) {
						BigDecimal commission = rs.getBigDecimal("commission");
//					BigDecimal newcommission = commission.add(oneHundred);
						commission = commission.add(oneHundred);
						int empno = rs.getInt("empno");
						pstmt.setBigDecimal(1, commission);
						pstmt.setInt(2, empno);
						pstmt.addBatch();//加入批次
						
						//50筆批次送
						
						pstmt.clearParameters();
//					int updated = pstmt.executeUpdate();
//					if (updated > 0) {
//						System.out.println("OK");
//					}
						count++;
						if (count % 50 == 0) {
							
							pstmt.executeBatch();
							pstmt.clearBatch();
							count = 0;
							
						}
					}
					
				}
				
				int[] results = pstmt.executeBatch();
				pstmt.clearBatch();
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
