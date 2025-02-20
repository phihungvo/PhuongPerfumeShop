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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

@SuppressWarnings("unused")
public class Frm_QuanLyDonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenKhachHang;
	private JTable table;
	private JDateChooser dateChooser;
	private JComboBox<String> cbTrangThaiDonHang;
	private JComboBox cbTimKiem;
	private JPanel pnTiemKiemDonHang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_QuanLyDonHang frame = new Frm_QuanLyDonHang();
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
	    
	    String sql = "SELECT dh.id, dh.ngaydathang, dh.thanhtoan, nd.hoten, tt.tentrangthai "
	    		   + "FROM DONHANG dh "
	               + "INNER JOIN nguoidung nd ON dh.id_khachhang = nd.id "
	               + "INNER JOIN trangthaidonhang tt ON dh.id_trangthai = tt.id "
	               + "WHERE LOWER(nd.hoten) LIKE LOWER(?)";

	    try {
	        List<DonHangDTO> dsDonHang = DonHangBL.dsDonHangTheoSQL2(sql, tenKh);

	        String[] columnNames = {"Mã ĐH", "Tên Khách Hàng", "Ngày Đặt", "Trạng Thái", "Thanh Toán"};
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	        // Đổ dữ liệu vào bảng
	        for (DonHangDTO dh : dsDonHang) {
	            Object[] rowData = {dh.getId(), dh.getTenKhachHang(), dh.getNgayDatHang(),
	            					dh.getId_trangThai(), dh.isThanhToan()};
	            model.addRow(rowData);
	        }

	        table.setModel(model); // Cập nhật bảng

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Frm_QuanLyDonHang() throws ClassNotFoundException, SQLException {
		setTitle("Quản lý đơn hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 11, 769, 439);
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
				if (selectedItem == -1) return;

		        dateChooser.setEnabled(false);
		        txtTenKhachHang.setEnabled(false);
		        cbTrangThaiDonHang.setEnabled(false);
				
				switch (selectedItem) {
					case 0:
		                txtTenKhachHang.setEnabled(true);
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

		btnNewButton.setBounds(545, 42, 209, 143);
		pnTiemKiemDonHang.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 744, 171);
		pnTiemKiemDonHang.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {"Mã ĐH", "Tên Khách Hàng", "Ngày Đặt", "Trạng Thái", "Thanh Toán"}
		));
		
		JPanel pnChiTietDonHang = new JPanel();
		tabbedPane.addTab("Chi tiết đơn hàng", null, pnChiTietDonHang, null);
		
		JButton btnNewButton_1 = new JButton("Xem chi tiết đơn hàng đang chọn");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(27, 467, 769, 23);
		contentPane.add(btnNewButton_1);
	}
}



























