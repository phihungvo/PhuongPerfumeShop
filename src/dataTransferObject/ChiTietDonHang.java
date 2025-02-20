package dataTransferObject;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int id_donHang;
	
	private int id_sanPham;
	
	private int soLuong;
	
	public ChiTietDonHang() {
		
	}

	public ChiTietDonHang(int id, int id_donHang, int id_sanPham, int soLuong) {
		super();
		this.id = id;
		this.id_donHang = id_donHang;
		this.id_sanPham = id_sanPham;
		this.soLuong = soLuong;
	}
	
	public ChiTietDonHang(int id_donHang, int id_sanPham, int soLuong) {
		super();
		this.id_donHang = id_donHang;
		this.id_sanPham = id_sanPham;
		this.soLuong = soLuong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_donHang() {
		return id_donHang;
	}

	public void setId_donHang(int id_donHang) {
		this.id_donHang = id_donHang;
	}

	public int getId_sanPham() {
		return id_sanPham;
	}

	public void setId_sanPham(int id_sanPham) {
		this.id_sanPham = id_sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	

}
