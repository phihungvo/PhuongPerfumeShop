package dataTransferObject;

import java.io.Serializable;
import java.util.Date;

public class DonHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int id_khachhang;
	
	private Date ngayDatHang;
	
	private String tenNguoiNhanHang;
	
	private String dienThoaiNguoiNhan;
	
	private String diaChiGiaoHang;
	
	private String ghiChu;
	
	private boolean thanhToan;
	
	private int id_trangThai;
	
	public DonHang() {
		
	}

	public DonHang(int id, int id_khachhang, Date ngayDatHang, String tenNguoiNhanHang, String dienThoaiNguoiNhan,
			String diaChiGiaoHang, String ghiChu, boolean thanhToan, int id_trangThai) {
		super();
		this.id = id;
		this.id_khachhang = id_khachhang;
		this.ngayDatHang = ngayDatHang;
		this.tenNguoiNhanHang = tenNguoiNhanHang;
		this.dienThoaiNguoiNhan = dienThoaiNguoiNhan;
		this.diaChiGiaoHang = diaChiGiaoHang;
		this.ghiChu = ghiChu;
		this.thanhToan = thanhToan;
		this.id_trangThai = id_trangThai;
	}
	
	public DonHang(int id_khachhang, Date ngayDatHang, String tenNguoiNhanHang, String dienThoaiNguoiNhan,
			String diaChiGiaoHang, String ghiChu, boolean thanhToan, int id_trangThai) {
		super();
		this.id_khachhang = id_khachhang;
		this.ngayDatHang = ngayDatHang;
		this.tenNguoiNhanHang = tenNguoiNhanHang;
		this.dienThoaiNguoiNhan = dienThoaiNguoiNhan;
		this.diaChiGiaoHang = diaChiGiaoHang;
		this.ghiChu = ghiChu;
		this.thanhToan = thanhToan;
		this.id_trangThai = id_trangThai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_khachhang() {
		return id_khachhang;
	}

	public void setId_khachhang(int id_khachhang) {
		this.id_khachhang = id_khachhang;
	}

	public Date getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getTenNguoiNhanHang() {
		return tenNguoiNhanHang;
	}

	public void setTenNguoiNhanHang(String tenNguoiNhanHang) {
		this.tenNguoiNhanHang = tenNguoiNhanHang;
	}

	public String getDienThoaiNguoiNhan() {
		return dienThoaiNguoiNhan;
	}

	public void setDienThoaiNguoiNhan(String dienThoaiNguoiNhan) {
		this.dienThoaiNguoiNhan = dienThoaiNguoiNhan;
	}

	public String getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(String diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public boolean isThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(boolean thanhToan) {
		this.thanhToan = thanhToan;
	}

	public int getId_trangThai() {
		return id_trangThai;
	}

	public void setId_trangThai(int id_trangThai) {
		this.id_trangThai = id_trangThai;
	}

	@Override
	public String toString() {
		return "DonHang [id=" + id + ", id_khachhang=" + id_khachhang + ", ngayDatHang=" + ngayDatHang
				+ ", tenNguoiNhanHang=" + tenNguoiNhanHang + ", dienThoaiNguoiNhan=" + dienThoaiNguoiNhan
				+ ", diaChiGiaoHang=" + diaChiGiaoHang + ", ghiChu=" + ghiChu + ", thanhToan=" + thanhToan
				+ ", id_trangThai=" + id_trangThai + "]";
	}
	
	

}
