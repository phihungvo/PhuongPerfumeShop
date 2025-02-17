package bussinessLogicLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CSDL {
	private static Connection ketNoi;
	
	private static final String url = "";
	
	public static Connection getKetNoi() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ketNoi = DriverManager.getConnection(url, "root", "root");
			return ketNoi;
		}catch( ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Connection kn = getKetNoi();
		if (kn != null)
			System.out.println("Ket noi thanh cong");
		else
			System.out.println("That bai");
	}
}
