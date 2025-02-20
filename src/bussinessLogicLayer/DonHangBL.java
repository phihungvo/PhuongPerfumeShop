package bussinessLogicLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataTransferObject.ChiTietDonHang;
import dataTransferObject.DonHang;
import dataTransferObject.DonHangDTO;
import dataTransferObject.TrangThaiDonHang;

public class DonHangBL {
	
	public static List<DonHang> dsDonHangTheoSQL(String sql) throws SQLException, ClassNotFoundException{
		List<DonHang> danhSachDonHang;
		
		try (Connection conn = CSDL.getKetNoi()){
			danhSachDonHang = new ArrayList<>();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				DonHang donHang = new DonHang();
				donHang.setId(resultSet.getInt("id"));
				donHang.setId_khachhang(resultSet.getInt("id_khachhang"));
				donHang.setNgayDatHang(resultSet.getDate("ngaydathang"));
				donHang.setTenNguoiNhanHang(resultSet.getString("tennguoinhanhang"));
				donHang.setDiaChiGiaoHang(resultSet.getString("diachigiaohang"));
				donHang.setDienThoaiNguoiNhan(resultSet.getString("dienthoainguoinhan"));
				donHang.setGhiChu(resultSet.getString("ghichu"));
				donHang.setThanhToan(resultSet.getBoolean("thanhtoan"));
				donHang.setId_trangThai(resultSet.getInt("id_trangthai"));
				danhSachDonHang.add(donHang);
			}
		}
		return danhSachDonHang;
	}
	
	public static List<DonHangDTO> dsDonHangTheoSQL2(String sql, List<Object> params) throws SQLException, ClassNotFoundException {
	    List<DonHangDTO> danhSachDonHang = new ArrayList<>();

	    try (Connection conn = CSDL.getKetNoi();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	    	
	    	// Set giá trị cho tham số
	    	for (int i = 0; i < params.size(); i++) {
	    		ps.setObject(i + 1, params.get(i));
	    	}

	        ResultSet resultSet = ps.executeQuery();

	        while (resultSet.next()) {
	            DonHangDTO donHang = new DonHangDTO(
	                resultSet.getInt("id"),
	                resultSet.getDate("ngaydathang"),
	                resultSet.getString("hoten"), // Lấy tên khách hàng
	                resultSet.getBoolean("thanhtoan"),
	                resultSet.getString("tentrangthai")
	            );
	            danhSachDonHang.add(donHang);
	        }
	    }
	    return danhSachDonHang;
	}

	
	
	public static DonHang layDonHangTheoId(int id) throws SQLException, ClassNotFoundException{
		DonHang donHang = null;
		
		try(Connection conn = CSDL.getKetNoi()){
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM donhang INNER JOIN nguoidung ON "
					+ "nguoidung.id = donhang.id_khachhang "
					+ "WHERE donhang.id = '" + id + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				donHang = new DonHang();
				donHang.setId(resultSet.getInt("id"));
				donHang.setId_khachhang(resultSet.getInt("id_khachhang"));
				donHang.setNgayDatHang(resultSet.getDate("ngaydathang"));
				donHang.setTenNguoiNhanHang(resultSet.getString("tennguoinhanhang"));
				donHang.setDiaChiGiaoHang(resultSet.getString("diachigiaohang"));
				donHang.setGhiChu(resultSet.getString("ghichu"));
				donHang.setDienThoaiNguoiNhan(resultSet.getString("dienthoainguoinhan"));
				donHang.setThanhToan(resultSet.getBoolean("thanhtoan"));
				donHang.setId_trangThai(resultSet.getInt("id_trangthai"));
			}
		}
		
		return donHang;
	}
	
	
	public static List<ChiTietDonHang> dsCTDHTheoSQL(String sql) throws SQLException, ClassNotFoundException {
		List<ChiTietDonHang> dsCTDH;
		
		try(Connection conn = CSDL.getKetNoi()){
			dsCTDH = new ArrayList<>();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				ChiTietDonHang ctdh = new ChiTietDonHang();
				ctdh.setId(resultSet.getInt("id"));
				ctdh.setId_donHang(resultSet.getInt("id_donhang"));
				ctdh.setId_sanPham(resultSet.getInt("id_sanpham"));
				ctdh.setSoLuong(resultSet.getInt("soluong"));
				dsCTDH.add(ctdh);
			}
		}
		
		return dsCTDH;
	}
	
//	public static void capNhatDonHang(DonHang donHang) throws SQLException, ClassNotFoundException{
//		try (Connection conn = CSDL.getKetNoi()){
//			String sql = "UPDATE donhang SET "
//					+ "tennguoinhanhang = '" + donHang.getTenNguoiNhanHang()+ "',"
//					+ "dienthoainguoinhan = '" + donHang.getDienThoaiNguoiNhan() + "',"
//					+ "diachigiaohang = '" + donHang.getDiaChiGiaoHang() + "',"
//					+ "trangthai = '" + donHang.getId_trangThai() + "',"
//					+ "thanhtoan = '" + donHang.isThanhToan()
//					+ "'WHERE id = '" + donHang.getId()
//					+ "'";
//						
//			
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.execute();
//		}
//	}
	
	public static void capNhatDonHang(DonHang donHang) throws SQLException, ClassNotFoundException {
	    String sql = "UPDATE donhang SET "
	            + "tennguoinhanhang = ?, "
	            + "dienthoainguoinhan = ?, "
	            + "diachigiaohang = ?, "
	            + "trangthai = ?, "
	            + "thanhtoan = ? "
	            + "WHERE id = ?";

	    try (Connection conn = CSDL.getKetNoi();
	         PreparedStatement statement = conn.prepareStatement(sql)) {

	        // Gán giá trị cho các tham số (dùng setString, setInt, setBoolean,...)
	        statement.setString(1, donHang.getTenNguoiNhanHang());
	        statement.setString(2, donHang.getDienThoaiNguoiNhan());
	        statement.setString(3, donHang.getDiaChiGiaoHang());
	        statement.setInt(4, donHang.getId_trangThai());
	        statement.setBoolean(5, donHang.isThanhToan());
	        statement.setInt(6, donHang.getId());  // Giả sử ID là số nguyên

	        // Thực thi câu lệnh SQL
	        statement.executeUpdate();
	    }
	}
	
	
	public static int xoaChiTietDonHang(int id) throws SQLException, ClassNotFoundException{
		try (Connection conn = CSDL.getKetNoi()){
			String sql = "DELETE FROM chitietdonhang WHERE id = '" + id + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			return statement.executeUpdate();
		}
	}
	
	public static List<TrangThaiDonHang> danhhSachTTDH() throws SQLException, ClassNotFoundException{
		List<TrangThaiDonHang> dsTTDH;
		
		try (Connection conn = CSDL.getKetNoi()){
			dsTTDH = new ArrayList<>();
			String sql = "Select * from trangthaidonhang";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				TrangThaiDonHang ttdh = new TrangThaiDonHang();
				ttdh.setId(resultSet.getInt("id"));
				ttdh.setTenTrangThai(resultSet.getString("tentrangthai"));
				dsTTDH.add(ttdh);
			}
		}
		return dsTTDH;
	}

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Test 2");
		DonHang donHang = layDonHangTheoId(13);
		System.out.println(donHang.toString());
		
		System.out.println("3....Danh sach chi tiet don hhang");
		
		System.out.println("4....Cap nhat don hhang");
		
		System.out.println("5....Xoa chhi tiet don hhang");
		int success = xoaChiTietDonHang(27);
		System.out.println("Xoa " + success);
		if (success > 0)
			System.out.println("Xoa thanh cong");
		else if (success == 0)
			System.out.println("Xoa that bai");
		
		
		List<TrangThaiDonHang> dsdh2 = danhhSachTTDH();
		for(TrangThaiDonHang ttdh : dsdh2) {
			System.out.println("Id "+ttdh.getId() + " ten tt: "+ttdh.getTenTrangThai());
		}
	}

}
































