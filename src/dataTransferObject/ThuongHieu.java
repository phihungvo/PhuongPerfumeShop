package dataTransferObject;

import java.io.Serializable;

public class ThuongHieu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String tenThuongHieu;
	
	private String hinhAnh;
	
	public ThuongHieu() {

	}
	
	public ThuongHieu(int id, String tenThuongHieu, String hinhAnh) {
		super();
		this.id = id;
		this.tenThuongHieu = tenThuongHieu;
		this.hinhAnh = hinhAnh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenThuongHieu() {
		return tenThuongHieu;
	}

	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	

}
