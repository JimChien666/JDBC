package orm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;//x : extension

import org.apache.commons.dbcp2.BasicDataSource;

public class EmployeeJDBCDAO implements EmployeeDAO {
	
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		//lazy init(要用到才執行，放進constructor也可)
		if (this.dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("scott");
			ds.setPassword("tiger");
			ds.setMaxTotal(50); //設定最多connection上線,超過使用量必須等待
			ds.setMaxIdle(50);   //設定最多idle的connection,超過的connection會被執行connection.close()
			this.dataSource = ds;
		}
		return this.dataSource;
	}

	//取得所有的emp table的資料，轉換成Employee物件，回傳list因為會有多筆
	@Override
	public List<Employee> listEmployees() {
		//select * from emp -> Employee
		
		List<Employee> list = new ArrayList<>();
		try (
				Connection connection = getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from emp");
				) {
			
			
			while (rs.next()) {
				Employee emp = new Employee();
				String name = rs.getString("ename");
				emp.setName(name);
				int empno = rs.getInt("empno");
				emp.setEmpNO(empno);
				emp.setJob(rs.getNString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setCommission(rs.getBigDecimal("commission"));
				emp.setSalary(rs.getBigDecimal("salary"));
				list.add(emp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//更新Employee物件回資料庫
	@Override
	public void updateEmployee(Employee emp) {
		try (Connection connection = getDataSource().getConnection();){
			
			PreparedStatement pstmt = connection.prepareStatement("update emp set ename=?, job=?, hiredate=?,salary=?,commission=? where empno=?");
			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getJob());
			Date hireDate = new Date(emp.getHiredate().getTime());
			pstmt.setDate(3, hireDate);
			pstmt.setBigDecimal(4, emp.getSalary());
			pstmt.setBigDecimal(5, emp.getCommission());
			pstmt.setInt(6, emp.getEmpNO());
			pstmt.executeUpdate();
			pstmt.clearParameters();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
