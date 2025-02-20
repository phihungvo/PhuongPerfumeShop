package dataTransferObject;

import java.io.Serializable;

public class TrangThaiDonHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String tenTrangThai;
	
	public TrangThaiDonHang() {
		
	}

	public TrangThaiDonHang(int id, String tenTrangThai) {
		super();
		this.id = id;
		this.tenTrangThai = tenTrangThai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}
	
}
