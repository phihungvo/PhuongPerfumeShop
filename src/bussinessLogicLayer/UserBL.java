package bussinessLogicLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataTransferObject.NguoiDung;

public class UserBL {
	public static List<NguoiDung> dsNguoiDung(String sql) throws SQLException{
		List<NguoiDung> dsNguoiDung;
		try (Connection conn = CSDL.getKetNoi()){
			dsNguoiDung = new ArrayList<>();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				NguoiDung nguoiDung = new NguoiDung();
				nguoiDung.setId(resultSet.getInt("id"));
				nguoiDung.setEmail(resultSet.getString("email"));
				nguoiDung.setPassword(resultSet.getString("password"));
				nguoiDung.setHoTen(resultSet.getString("hoten"));
				nguoiDung.setDiaChi(resultSet.getString("diachi"));
				nguoiDung.setDtdd(resultSet.getString("dtdd"));
				nguoiDung.setIdVaiTro(resultSet.getInt("id_vaitro"));
				dsNguoiDung.add(nguoiDung);
			}
		}
		return dsNguoiDung;
	}
	
	public static void capNhatNguoiDung(NguoiDung nguoiDung) throws SQLException {
		try (Connection conn = CSDL.getKetNoi()){
			String sql = "UPDATE nguoidung SET diachi = '" + nguoiDung.getDiaChi()
						+ "', dtdd = '" + nguoiDung.getDtdd()
						+ "', id_vaitro = '" + nguoiDung.getIdVaiTro()
						+ "' WHERE id = '" + nguoiDung.getId() + "'";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
		}
	}
}
