package blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class BlobWriter {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott",
				"tiger");

		) {
			
			PreparedStatement pstmt = connection.prepareStatement("insert into files(filename, data) values (?,?)");
			Statement stmt = connection.createStatement();
			stmt.execute("delete from files");//先清空table
			
			File file = new File("C:\\iii\\files_read\\hi.png");
			FileInputStream fis = new FileInputStream(file);
			
			pstmt.setString(1, file.getName());
			pstmt.setBinaryStream(2, fis);
			
			pstmt.execute();
			System.out.println("done");
			
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}

}
