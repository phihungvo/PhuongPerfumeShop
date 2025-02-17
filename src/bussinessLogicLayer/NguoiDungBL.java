package bussinessLogicLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dataTransferObject.NguoiDung;

public class NguoiDungBL {
	public static NguoiDung timNguoiDung(String email, String password) {
		NguoiDung nd = null;
		String sql = "select * from nguoidung where email='" + email + "'and password = '" + password + "'";
		try (Connection kn = CSDL.getKetNoi()){
			Statement stm = kn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoten"));
				nd.setDiaChi(rs.getString("diachi"));
				nd.setDtdd(rs.getString("dtdd"));
				nd.setIdVaiTro(rs.getInt("id_vaitro"));
				
			}
			return nd;
		}catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		NguoiDung nd = timNguoiDung("bichle@gmail.com", "bichle");
		if(nd != null)
			System.out.println("Tim thay Bich Le");
		else 
			System.out.println("Khong thay Bich Le");
	}
}
