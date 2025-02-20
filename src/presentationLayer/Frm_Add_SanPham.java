package presentationLayer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.LoaiComboBoxModel;
import bussinessLogicLayer.NguoiDungBL;
import dataTransferObject.Loai;
import dataTransferObject.SanPham;
import dataTransferObject.ThuongHieu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Frm_Add_SanPham extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenSanPham;
	private JTextField txtMoTa;
	private JTextField txtHinh;
	private JTextField txtDonGia;
	private JTextField txtDonGiaKM;
	private JTextField txtSoLuong;
	private JComboBox cbThuongHieu;
	private JComboBox cbLoai;
	private JLabel lblHinh;
	private String url_hinh = "";
	private Map<String, Integer> loaiMap = new HashMap<>();
	private Map<String, Integer> thuongHieuMap = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Add_SanPham frame = new Frm_Add_SanPham();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Frm_Add_SanPham() throws ClassNotFoundException, SQLException {
		setTitle("Thêm sản phẩm mới");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sản phẩm");
		lblNewLabel.setBounds(28, 11, 86, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblMT = new JLabel("Mô tả");
		lblMT.setBounds(28, 50, 86, 19);
		contentPane.add(lblMT);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hình");
		lblNewLabel_1_1.setBounds(28, 211, 86, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Đơn giá");
		lblNewLabel_1_2.setBounds(28, 253, 86, 19);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Đơn giá KM");
		lblNewLabel_1_2_1.setBounds(28, 292, 86, 19);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Số lượng");
		lblNewLabel_1_2_2.setBounds(28, 328, 86, 19);
		contentPane.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Loại");
		lblNewLabel_1_2_3.setBounds(28, 366, 86, 19);
		contentPane.add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_3_1 = new JLabel("Thương hiệu");
		lblNewLabel_1_2_3_1.setBounds(28, 406, 86, 19);
		contentPane.add(lblNewLabel_1_2_3_1);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(139, 9, 276, 20);
		contentPane.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(139, 48, 276, 148);
		contentPane.add(txtMoTa);
		
		txtHinh = new JTextField();
		txtHinh.setColumns(10);
		txtHinh.setBounds(139, 209, 231, 22);
		contentPane.add(txtHinh);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(139, 251, 114, 20);
		contentPane.add(txtDonGia);
		
		txtDonGiaKM = new JTextField();
		txtDonGiaKM.setColumns(10);
		txtDonGiaKM.setBounds(139, 290, 114, 20);
		contentPane.add(txtDonGiaKM);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(139, 326, 114, 20);
		contentPane.add(txtSoLuong);
		
		cbLoai = new JComboBox();
		cbLoai.setBounds(139, 363, 114, 22);
		contentPane.add(cbLoai);
		
		List<Loai> dsLoai = NguoiDungBL.danhSachLoai();
		
		String[] cboxLoai = new String[dsLoai.size()];
		
		for (int i = 0; i < dsLoai.size(); i++) {
			Loai loai = dsLoai.get(i);
			loaiMap.put(loai.getTenLoai(), loai.getId());
			cboxLoai[i] = loai.getTenLoai();
		}
		
		cbLoai.setModel(new DefaultComboBoxModel<>(cboxLoai));
		
		JButton btnThem = new JButton("Thêm mới");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SanPham sanPhamMoi;
					String tenSanPham, moTa, hinh, loai, thuongHieu;
					double donGia, donGiaKM;
					int soLuong, id_loai, id_thuong_hieu;
					
					tenSanPham = txtTenSanPham.getText();
					moTa = txtMoTa.getText();
					hinh = txtHinh.getText();
					donGia = Double.parseDouble(txtDonGia.getText());
					donGiaKM = Double.parseDouble(txtDonGiaKM.getText());
					soLuong = Integer.parseInt(txtSoLuong.getText());
					
					//Loai selectedLoai = (Loai) cbLoai.getSelectedItem();
					String tenLoai_Selected = (String) cbLoai.getSelectedItem();
					id_loai = loaiMap.get(tenLoai_Selected);
					
					String thuongHieu_Selected = (String) cbThuongHieu.getSelectedItem();
					id_thuong_hieu = thuongHieuMap.get(thuongHieu_Selected);
					
					
					if (tenSanPham == null || moTa == null || hinh == null || donGia <= 0 || soLuong < 0 ||
							id_loai <= 0 || id_thuong_hieu <= 0) {
						        throw new IllegalArgumentException("Dữ liệu không hợp lệ!");
					}
					
					sanPhamMoi = new SanPham(tenSanPham, moTa, hinh, donGia, donGiaKM, soLuong,
							new Date(), true, id_loai, id_thuong_hieu);
					
					boolean success = NguoiDungBL.themSanPham(sanPhamMoi);
					System.out.println("success "+ success);
					
					if (success) {
						JOptionPane.showMessageDialog(rootPane, "Thêm sản phẩm mới thành công!");
					}else {
						JOptionPane.showMessageDialog(rootPane, "Thêm sản phẩm thất bại!");
					}
					
				} catch (ClassNotFoundException | UnsupportedEncodingException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setBounds(139, 451, 114, 28);
		contentPane.add(btnThem);
		
		cbThuongHieu = new JComboBox();
		cbThuongHieu.setBounds(139, 403, 114, 22);
		contentPane.add(cbThuongHieu);
		
		List<ThuongHieu> dsThuongHieu = NguoiDungBL.danhSachThuongHieu();
		String[] cboxThuongHieu = new String[dsThuongHieu.size()];
		for(int i = 0; i < dsThuongHieu.size(); i++) {
			ThuongHieu thuongHieu = dsThuongHieu.get(i);
			thuongHieuMap.put(thuongHieu.getTenThuongHieu(), thuongHieu.getId());
			cboxThuongHieu[i] = thuongHieu.getTenThuongHieu();
		}
		
//		thuongHieuMap.forEach((k, v) -> {
//			System.out.println("Key: " + k + " - " + "value: " + v);
//		});
		
		cbThuongHieu.setModel(new DefaultComboBoxModel<>(cboxThuongHieu));
		
		JButton btnChonHinh = new JButton("...");
		btnChonHinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("C:\\Users\\vophi\\eclipse-workspace\\PhuongPerfumeM2\\src\\images");
				fileChooser.setDialogTitle("Hãy chọn 1 tập tin hình ảnh");
				fileChooser.setFileFilter(new FileNameExtensionFilter("Các tập tin *.jpg", "jpg"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("Các tập tin *.png", "png"));
				int chon = fileChooser.showOpenDialog(rootPane);
				
				if (chon == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					url_hinh = file.getAbsolutePath();
					txtHinh.setText(url_hinh);
				}
				
				ImageIcon icon = new ImageIcon(url_hinh);	
				icon.setImage(icon.getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_DEFAULT));
				lblHinh.setIcon(icon);
			}
		});
		btnChonHinh.setBounds(375, 209, 39, 21);
		contentPane.add(btnChonHinh);
		
		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnTiepTuc.setBounds(278, 451, 114, 28);
		contentPane.add(btnTiepTuc);
		
		lblHinh = new JLabel("");
		lblHinh.setBounds(270, 255, 145, 170);
		contentPane.add(lblHinh);
	}
}








