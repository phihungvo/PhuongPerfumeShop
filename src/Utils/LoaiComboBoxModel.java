package Utils;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataTransferObject.Loai;

public class LoaiComboBoxModel extends DefaultComboBoxModel<Loai>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoaiComboBoxModel(List<Loai> lstLoai) {
		for(Loai loai : lstLoai) {
			this.addElement(loai);
		}
	}
}

//
//Bạn gặp vấn đề vì JComboBox chỉ lưu tên loại (String), trong khi bạn cần ID loại (id_loai). 
//Khi người dùng chọn một loại, bạn không thể lấy được id_loai trực tiếp từ JComboBox.
//
//Giải pháp tốt nhất là lưu cả ID và tên loại vào JComboBox. Bạn có thể làm như sau:
//
//Cách giải quyết
//Thay vì lưu String[], bạn lưu danh sách Loai trực tiếp vào JComboBox.
//
//Bước 1: Tạo lớp LoaiComboBoxModel để hiển thị đúng tên loại

//Giải thích: DefaultComboBoxModel<Loai> giúp bạn lưu danh sách Loai thay vì chỉ lưu String.
//Bước 2: Cập nhật ComboBox để hiển thị đúng tên loại

//List<Loai> dsLoai = NguoiDungBL.danhSachLoai();
//LoaiComboBoxModel modelLoai = new LoaiComboBoxModel(dsLoai);
//cbLoai.setModel(modelLoai);

//Giải thích: Thay vì String[], bạn gán trực tiếp danh sách Loai vào JComboBox.


//Bước 3: Khi chọn loại, lấy id_loai thay vì tên
//Khi người dùng chọn một loại trong JComboBox, bạn có thể lấy trực tiếp id_loai từ đối tượng Loai như sau:
//

//Loai loaiDuocChon = (Loai) cbLoai.getSelectedItem();
//int id_loai = loaiDuocChon.getId();

//Giải thích: Vì JComboBox đang lưu đối tượng Loai, bạn có thể lấy id mà không cần tìm lại trong danh sách.
//

















