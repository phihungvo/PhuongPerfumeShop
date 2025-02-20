package presentationLayer;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import bussinessLogicLayer.DonHangBL;
import dataTransferObject.DonHang;
import dataTransferObject.DonHangDTO;
import dataTransferObject.TrangThaiDonHang;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JRadioButton;

@SuppressWarnings("unused")
public class Frm_QL_DonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenKhachHang;
	private JTable table;
	private JDateChooser dateChooser;
	private JComboBox<String> cbTrangThaiDonHang;
	private JComboBox cbTimKiem;
	private JPanel pnTiemKiemDonHang;
	private JTextField txtMaDH;
	private JTextField txtTenKH;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtDienThoai;
	private JTable table_1;
	private JTextField txtNgayDatHang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_QL_DonHang frame = new Frm_QL_DonHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Hàm này dùng để chuyển danh sách trạng thái đơn hàng được Query thanhf một mảng String tên của trạng thái đơn hàng để gắn vào combobox 
	private String[] returnArrTenTrangThai(List<TrangThaiDonHang> ttdhList) {
		List<TrangThaiDonHang> dsTTDH;
		String[] arrTrangThai = new String[ttdhList.size()];
		try {
			dsTTDH = DonHangBL.danhhSachTTDH();
										
			for (int i = 0; i < ttdhList.size(); i++) {
				arrTrangThai[i] = ttdhList.get(i).getTenTrangThai();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return arrTrangThai;
	}
	
	private void hienThiDonHang() {
	    String tenKh = txtTenKhachHang.getText();
	    Date selectedDate = dateChooser.getDate();
	    String trangThaiDH = cbTrangThaiDonHang.getSelectedItem().toString();
	    
	    StringBuilder sql = new StringBuilder("SELECT dh.id, dh.ngaydathang, dh.thanhtoan, nd.hoten, tt.tentrangthai "
	    		   + "FROM DONHANG dh "
	               + "INNER JOIN nguoidung nd ON dh.id_khachhang = nd.id "
	               + "INNER JOIN trangthaidonhang tt ON dh.id_trangthai = tt.id "
	               + "WHERE 1=1 ");
	    
	    List<Object> params = new ArrayList<>();
	    
	    // Kiểm tra điều kiện tìm kiếm được chọn
	    int searchOption = cbTimKiem.getSelectedIndex();
	    
	    switch(searchOption) {
	        case 0: // Tìm theo tên khách hàng
	            if (!tenKh.isEmpty()) {
	                sql.append("AND nd.hoten LIKE ? ");
	                params.add("%" + tenKh + "%");
	            }
	            break;
	            
	        case 1: // Tìm theo ngày
	            if (selectedDate != null) {
	                sql.append("AND DATE(dh.ngaydathang) = ? ");
	                params.add(new java.sql.Date(selectedDate.getTime()));
	            }
	            break;
	            
	        case 2: // Tìm theo trạng thái
	            if (trangThaiDH != null && !trangThaiDH.isEmpty()) {
	                sql.append("AND tt.tentrangthai = ? ");
	                params.add(trangThaiDH);
	            }
	            break;
	    }

	    try {
	        List<DonHangDTO> dsDonHang = DonHangBL.dsDonHangTheoSQL2(sql.toString(), params);

	        String[] columnNames = {"Mã ĐH", "Tên Khách Hàng", "Ngày Đặt", "Trạng Thái", "Thanh Toán"};
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa dữ liệu cũ

	        for (DonHangDTO dh : dsDonHang) {
	            Object[] rowData = {
	                dh.getId(), 
	                dh.getTenKhachHang(), 
	                dh.getNgayDatHang(),
	                dh.getId_trangThai(), 
	                dh.isThanhToan()
	            };
	            model.addRow(rowData);
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Frm_QL_DonHang() throws ClassNotFoundException, SQLException {
		setTitle("Quản lý đơn hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 11, 711, 490);
		contentPane.add(tabbedPane);
		
		pnTiemKiemDonHang = new JPanel();
		pnTiemKiemDonHang.setName("");
		pnTiemKiemDonHang.setToolTipText("");
		tabbedPane.addTab("Tìm kiếm đơn hàng", null, pnTiemKiemDonHang, null);
		pnTiemKiemDonHang.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm theo");
		lblNewLabel.setBounds(10, 45, 125, 14);
		pnTiemKiemDonHang.add(lblNewLabel);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setBounds(189, 83, 323, 20);
		pnTiemKiemDonHang.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng");
		lblTnKhchHng.setBounds(10, 86, 125, 14);
		pnTiemKiemDonHang.add(lblTnKhchHng);
		
		JLabel lblNgytHng = new JLabel("Ngày đặt hàng");
		lblNgytHng.setBounds(10, 128, 125, 14);
		pnTiemKiemDonHang.add(lblNgytHng);
		
		JLabel lblTrngThin = new JLabel("Trạng thái đơn hàng");
		lblTrngThin.setBounds(10, 167, 125, 14);
		pnTiemKiemDonHang.add(lblTrngThin);
		
		cbTimKiem = new JComboBox();
		cbTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  
				int selectedItem = cbTimKiem.getSelectedIndex();

		        dateChooser.setEnabled(false);
		        txtTenKhachHang.setEnabled(false);
		        cbTrangThaiDonHang.setEnabled(false);
				
				switch (selectedItem) {
					case 0:
		                txtTenKhachHang.setEnabled(true);
		                txtTenKhachHang.requestFocus();
		                break;
		            case 1:
		                dateChooser.setEnabled(true);
		                break;
		            case 2:
		                cbTrangThaiDonHang.setEnabled(true);
		                break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + selectedItem);
				}
			}
		});
		cbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tên khách hàng", "Ngày đặt hàng", "Trạng thái đơn hàng"}));
		cbTimKiem.setBounds(189, 42, 323, 20);
		pnTiemKiemDonHang.add(cbTimKiem);

		// Tạo JDateChooser
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(189, 122, 323, 20); 

		pnTiemKiemDonHang.add(dateChooser);
		
		cbTrangThaiDonHang = new JComboBox();
		
		List<TrangThaiDonHang> dsTTDH = DonHangBL.danhhSachTTDH();
		String[] arrTrangThai = returnArrTenTrangThai(dsTTDH);
		cbTrangThaiDonHang.setModel(new DefaultComboBoxModel<String>(arrTrangThai));
		
		cbTrangThaiDonHang.setBounds(189, 163, 323, 22);
		pnTiemKiemDonHang.add(cbTrangThaiDonHang);
		
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setSize(new Dimension(6, 6));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        hienThiDonHang();
		    }
		});

		btnNewButton.setBounds(545, 42, 151, 143);
		pnTiemKiemDonHang.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 686, 210);
		pnTiemKiemDonHang.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"Mã ĐH", "Tên Khách Hàng", "Ngày Đặt", "Trạng Thái", "Thanh Toán"}
		));
		
		JButton btnNewButton_1 = new JButton("Xem chi tiết đơn hàng đang chọn");
		btnNewButton_1.setBounds(10, 428, 383, 23);
		pnTiemKiemDonHang.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					
					// Nếu không có dòng nào được chọn
			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn một đơn hàng trước khi xem chi tiết đơn hàng!", 
			                                          "Thông báo", JOptionPane.WARNING_MESSAGE);
			            return; // Dừng sự kiện tại đây
			        }			  
			        tabbedPane.setSelectedIndex(1);		 // Chuyển sang tab hiển thị chi tiết đơn hàng	        
			        // Nếu có dòng được chọn, lấy giá trị ở cột đầu tiên
			        int idDonHang = (int) table.getValueAt(selectedRow, 0);			        			  
					DonHang donHang = DonHangBL.layDonHangTheoId(idDonHang);
					
					txtMaDH.setText(String.valueOf(donHang.getId()));
					txtNgayDatHang.setText(String.valueOf(donHang.getNgayDatHang()));
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}		        
			}
		});
		
		JPanel pnChiTietDonHang = new JPanel();
		tabbedPane.addTab("Chi tiết đơn hàng", null, pnChiTietDonHang, null);
		pnChiTietDonHang.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã đơn hàng");
		lblNewLabel_1.setBounds(10, 29, 145, 19);
		pnChiTietDonHang.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1_1.setBounds(10, 70, 145, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thông tin giao hàng");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 112, 134, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tên");
		lblNewLabel_1_1_1_1.setBounds(10, 153, 100, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1_1_1.setBounds(10, 191, 100, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Điện thoại");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 232, 100, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1_1_1_1);
		
		txtMaDH = new JTextField();
		txtMaDH.setBounds(165, 28, 86, 20);
		pnChiTietDonHang.add(txtMaDH);
		txtMaDH.setColumns(10);
		
		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(165, 69, 403, 20);
		pnChiTietDonHang.add(txtTenKH);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(165, 152, 251, 20);
		pnChiTietDonHang.add(txtTen);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(165, 190, 251, 20);
		pnChiTietDonHang.add(txtDiaChi);
		
		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(165, 231, 251, 20);
		pnChiTietDonHang.add(txtDienThoai);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Trạng thái");
		lblNewLabel_1_1_1_1_1_2.setBounds(451, 153, 117, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1_1_1_2);
		
		JRadioButton rdbtnThanhToan = new JRadioButton("Đã thanh toán");
		rdbtnThanhToan.setBounds(451, 189, 126, 23);
		pnChiTietDonHang.add(rdbtnThanhToan);
		
		JButton btnNewButton_2 = new JButton("Cập nhật");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(165, 269, 139, 23);
		pnChiTietDonHang.add(btnNewButton_2);
		
		JComboBox cbTrangThai = new JComboBox();
		cbTrangThai.setBounds(546, 151, 150, 19);
		pnChiTietDonHang.add(cbTrangThai);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 305, 686, 112);
		pnChiTietDonHang.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Th\u00E0nh ti\u1EC1n", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "T\u00EAn s\u1EA3n ph\u1EA9m", "ID"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Tình hình đơn hàng");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_2.setBounds(451, 112, 134, 19);
		pnChiTietDonHang.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày đặt hàng");
		lblNewLabel_1_2.setBounds(353, 29, 145, 19);
		pnChiTietDonHang.add(lblNewLabel_1_2);
		
		txtNgayDatHang = new JTextField();
		txtNgayDatHang.setColumns(10);
		txtNgayDatHang.setBounds(451, 28, 117, 20);
		pnChiTietDonHang.add(txtNgayDatHang);
		
		JButton btnNewButton_1_1 = new JButton("Xóa chi tiết đơn hàng đang chọn");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1_1.setBounds(10, 428, 383, 23);
		pnChiTietDonHang.add(btnNewButton_1_1);
	}
}



























