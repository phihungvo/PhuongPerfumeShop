package dataTransferObject;

import java.io.Serializable;

public class NguoiDung implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String email;
	
	private String password;
	
	private String hoTen;
	
	private String diaChi;
	
	private String dtdd;
	
	private int idVaiTro;
	
	public NguoiDung() {
		
	}

	public NguoiDung(int id, String email, String password, String hoTen, String diaChi, String dtdd, int idVaiTro) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.dtdd = dtdd;
		this.idVaiTro = idVaiTro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDtdd() {
		return dtdd;
	}

	public void setDtdd(String dtdd) {
		this.dtdd = dtdd;
	}

	public int getIdVaiTro() {
		return idVaiTro;
	}

	public void setIdVaiTro(int idVaiTro) {
		this.idVaiTro = idVaiTro;
	}
	
	
}
