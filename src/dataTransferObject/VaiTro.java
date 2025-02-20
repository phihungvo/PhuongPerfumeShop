package dataTransferObject;

import java.io.Serializable;

public class VaiTro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String tenVaiTro;
	
	private String moTa;
	
	public VaiTro() {
		
	}
	
	public VaiTro(int id, String tenVaiTro, String moTa) {
		super();
		this.id = id;
		this.tenVaiTro = tenVaiTro;
		this.moTa = moTa;
	}
	
	public VaiTro(String tenVaiTro, String moTa) {
		super();
		this.tenVaiTro = tenVaiTro;
		this.moTa = moTa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenVaiTro() {
		return tenVaiTro;
	}

	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	

}
