import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.SelectableChannel;
import java.sql.*;

public class HW {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from emp");
//			while(rs.next()) {
//				System.out.println(rs.getInt("empno"));
			
//			PreparedStatement pstmt = connection.prepareStatement("select * from emp where empno = ?");
//			for(int i = 0; i<3; i++) {
//				pstmt.setInt(1, 1101+i);
//				ResultSet rs = pstmt.executeQuery();
//				while(rs.next()) {
//					System.out.println(rs.getInt("empno"));					
//				}
//				pstmt.clearParameters();
//				
//			}
			
//			PreparedStatement pstmt = connection.prepareStatement("update emp set commission = ? where empno = ?");
//			pstmt.setInt(1, 300);
//			pstmt.setInt(2, 1102);
//			pstmt.executeUpdate();
//			System.out.println("hi");
			
//			PreparedStatement pstmt = connection.prepareStatement("insert into files (filename, data) values (?,?)");
//			URL url = new URL("https://memes.tw/meme/bc3b2d8271f43924543571fed081b54e.png");
//			InputStream is = url.openStream();
//			
//			
//			pstmt.setString(1, "hihi");
//			pstmt.setBinaryStream(2, is);
//			pstmt.execute();
//			System.out.println("OK");
			
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from files");
//			while (rs.next()) {
//				InputStream is = rs.getBinaryStream("data");
//				BufferedInputStream bis = new BufferedInputStream(is);
//				FileOutputStream fos = new FileOutputStream("C:\\iii\\test.jpg");
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				byte[] buffer = new byte[1024];
//				
//				int length;
//				while ((length = bis.read(buffer)) != -1) {
//					bos.write(buffer, 0, length);
//				}
				
//			PreparedStatement pstmt = connection.prepareStatement("insert into files(filename, contents) values (?,?)");
//					File file = new File("C:\\Java\\char_UTF8.txt");
//					FileInputStream fis = new FileInputStream(file);
//					InputStreamReader isr = new InputStreamReader(fis, "utf8");
//					pstmt.setString(1, file.getName());
//					pstmt.setCharacterStream(2, isr);
//					pstmt.execute();
				
			
			PreparedStatement pstmt = connection.prepareStatement("update emp set commission = ? where empno=?");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			BigDecimal oneHundred = new BigDecimal(100);
			int count = 0;
			while (rs.next()) {
				int empno = rs.getInt("empno");
				BigDecimal commission = rs.getBigDecimal("commission");
				commission = commission.add(oneHundred);
				pstmt.setBigDecimal(1, commission);
				pstmt.setInt(2, empno);
				pstmt.addBatch();
				pstmt.clearParameters();
				count++;
				if (count %50 ==0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
				}
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
}
