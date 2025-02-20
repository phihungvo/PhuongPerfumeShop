package bussinessLogicLayer;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataTransferObject.Loai;
import dataTransferObject.NguoiDung;
import dataTransferObject.SanPham;
import dataTransferObject.ThuongHieu;

public class NguoiDungBL {
//	public static NguoiDung timNguoiDung(String email, String password) {
//		NguoiDung nd = null;
//		String sql = "select * from nguoidung where email='" + email + "'and password = '" + password + "'";
//		try (Connection kn = CSDL.getKetNoi()){
//			Statement stm = kn.createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			if (rs.next()) {
//				nd = new NguoiDung();
//				nd.setId(rs.getInt("id"));
//				nd.setEmail(rs.getString("email"));
//				nd.setPassword(rs.getString("password"));
//				nd.setHoTen(rs.getString("hoten"));
//				nd.setDiaChi(rs.getString("diachi"));
//				nd.setDtdd(rs.getString("dtdd"));
//				nd.setIdVaiTro(rs.getInt("id_vaitro"));
//				
//			}
//			return nd;
//		}catch (Exception e) {
//			return null;
//		}
//	}
	
	public static NguoiDung dangNhapNguoiDung(String email, String password) throws SQLException,
											ClassNotFoundException{
		NguoiDung nd = null;
		
		try(Connection conn = CSDL.getKetNoi()){
			Statement stm = conn.createStatement();
			String sql = "select * from nguoidung where email like '" + email 
					+ "' and password like '" + password + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nd = new NguoiDung();
				nd.setId(rs.getInt("id"));
				nd.setEmail(rs.getString("email"));
				nd.setPassword(rs.getString("password"));
				nd.setHoTen(rs.getString("hoTen"));
				nd.setDiaChi(rs.getString("diaChi"));
				nd.setDtdd(rs.getString("dtdd"));
				nd.setIdVaiTro(rs.getInt("id_vaiTro"));
			}
		}
		
		return nd;
	}
	
	
	public static List<Loai> danhSachLoai() throws SQLException, ClassNotFoundException {
		List<Loai> dsLoai;
		
		try(Connection conn = CSDL.getKetNoi()){
			dsLoai = new ArrayList<>();
			Statement stm = conn.createStatement();
			String sql = "select * from loai";
			ResultSet resultSet = stm.executeQuery(sql);
			
			while(resultSet.next()) {
				Loai loai = new Loai();
				loai.setId(resultSet.getInt("id"));
				loai.setTenLoai(resultSet.getString("tenLoai"));
				dsLoai.add(loai);
			}
		}
		return dsLoai;
	}
	
	
	public static List<ThuongHieu> danhSachThuongHieu() throws SQLException, ClassNotFoundException{
		
		List<ThuongHieu> dsThuongHieu;
		
		try(Connection conn = CSDL.getKetNoi()){
			dsThuongHieu = new ArrayList<>();
			Statement stm = conn.createStatement();
			String sql = "select * from thuonghieu";
			ResultSet resultSet = stm.executeQuery(sql);
			
			while(resultSet.next()) {
				ThuongHieu thuongHieu = new ThuongHieu();
				thuongHieu.setId(resultSet.getInt("id"));
				thuongHieu.setTenThuongHieu(resultSet.getString("tenthuonghieu"));
				thuongHieu.setHinhAnh(resultSet.getString("hinhanh"));
				dsThuongHieu.add(thuongHieu);
			}
		}
		return dsThuongHieu;
	}
	
	public static boolean themSanPham(SanPham sanPham) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
		
		boolean execute = false;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try (Connection conn = CSDL.getKetNoi()){
			String sql = "INSERT INTO sanpham (id, tenSanPham, moTa, hinhAnh, donGia, donGiaKM, soLuong, "
	                + "ngayTao, hienThi, id_loai, id_thuonghieu) "
	                + "VALUES(null,'" 
	                + sanPham.getTenSanPham() + "','" 
	                + sanPham.getMoTa() + "','" 
	                + sanPham.getHinhAnh() + "'," 
	                + sanPham.getDonGia() + "," 
	                + sanPham.getDonGiaKM() + "," 
	                + sanPham.getSoLuong() + ",'" 
	                + df.format(sanPham.getNgayTao()) + "'," 
	                + true + "," 
	                + sanPham.getIdLoai() + "," 
	                + sanPham.getIdThuongHieu() + ")";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			int row = statement.executeUpdate();
			System.out.println("execute 1:" + row);
			
			execute = row > 0 ? true : false;
		}
		
		return execute;
	}
	
	public static boolean themSanPham2(SanPham sanPham) throws SQLException, ClassNotFoundException {
	    boolean execute = false;
	    
	    String sql = "INSERT INTO sanpham (tensanpham, mota, hinhanh, dongia, dongiaKM, soluong, ngaytao, hienthi, id_loai, id_thuonghieu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = CSDL.getKetNoi();
	         PreparedStatement statement = conn.prepareStatement(sql)) {

	        statement.setString(1, sanPham.getTenSanPham());
	        statement.setString(2, sanPham.getMoTa());
	        statement.setString(3, sanPham.getHinhAnh());
	        statement.setDouble(4, sanPham.getDonGia());
	        statement.setDouble(5, sanPham.getDonGiaKM());
	        statement.setInt(6, sanPham.getSoLuong());
	        statement.setDate(7, new java.sql.Date(sanPham.getNgayTao().getTime()));
	        statement.setBoolean(8, sanPham.isHienThi());
	        statement.setInt(9, sanPham.getIdLoai());
	        statement.setInt(10, sanPham.getIdThuongHieu());

	        execute = statement.executeUpdate() > 0;
	    }
	    return execute;
	}

	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		NguoiDung nd = dangNhapNguoiDung("bichle@gmail.com", "bichle");
		
		if(nd != null)
			System.out.println("Tim thay Bich Le");
		else 
			System.out.println("Khong thay Bich Le");
		
		List<Loai> dsLoai = danhSachLoai();
		for (Loai loai : dsLoai) {
			System.out.println("Loai: "+loai.getId() + " " + loai.getTenLoai());
		}
		
		List<ThuongHieu> dsThuongHieu = danhSachThuongHieu();
		for (ThuongHieu thuongHieu : dsThuongHieu) {
			System.out
					.println("Thuong Hieu: "+thuongHieu.getId() + " " + thuongHieu.getTenThuongHieu() + " " + thuongHieu.getHinhAnh());
		}
		
		SanPham sp = new SanPham("Laptop Dell", "Laptop mạnh mẽ", "dell.jpg", 15000000, 14000000, 10, new Date(), true, 1, 2);
		boolean kq = themSanPham2(sp);
		if (kq)
			System.out.println("Them thanh cong");
		else
			System.out.println("Them that bai");
	}
}










