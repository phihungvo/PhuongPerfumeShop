package dataTransferObject;

import java.sql.Date;

public class DonHangDTO {
	private int id;
    private Date ngayDatHang;
    private String tenKhachHang;
    private boolean thanhToan;
    private String tenTrangThai;

    public DonHangDTO(int id, Date ngayDatHang, String tenKhachHang, 
    		boolean thanhToan, String tenTrangThai) {
        this.id = id;
        this.ngayDatHang = ngayDatHang;
        this.tenKhachHang = tenKhachHang;
        this.thanhToan = thanhToan;
        this.tenTrangThai = tenTrangThai;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public boolean isThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(boolean thanhToan) {
		this.thanhToan = thanhToan;
	}

	public String getId_trangThai() {
		return tenTrangThai;
	}

	public void setId_trangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}

    // Getters & Setters
   
}
