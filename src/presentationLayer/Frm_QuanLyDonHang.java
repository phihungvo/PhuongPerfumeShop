package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

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
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Frm_QuanLyDonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenKhachHang;
	private JTable table;

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

	/**
	 * Create the frame.
	 */
	public Frm_QuanLyDonHang() {
		setTitle("Quản lý đơn hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 11, 532, 439);
		contentPane.add(tabbedPane);
		
		JPanel pnTiemKiemDonHang = new JPanel();
		pnTiemKiemDonHang.setName("");
		pnTiemKiemDonHang.setToolTipText("");
		tabbedPane.addTab("Tìm kiếm đơn hàng", null, pnTiemKiemDonHang, null);
		pnTiemKiemDonHang.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm theo");
		lblNewLabel.setBounds(10, 45, 125, 14);
		pnTiemKiemDonHang.add(lblNewLabel);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setBounds(165, 80, 153, 20);
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
		
		JComboBox cbTimKiem = new JComboBox();
		cbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tên khách hàng", "Ngày đặt hàng", "Trạng thái đơn hàng"}));
		cbTimKiem.setBounds(165, 41, 153, 20);
		pnTiemKiemDonHang.add(cbTimKiem);

		// Tạo JDateChooser
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(165, 122, 153, 20); 

		pnTiemKiemDonHang.add(dateChooser);
		
		JComboBox cbTrangThaiDonHang = new JComboBox();
		cbTrangThaiDonHang.setBounds(165, 163, 153, 22);
		pnTiemKiemDonHang.add(cbTrangThaiDonHang);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(378, 41, 89, 23);
		pnTiemKiemDonHang.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 207, 507, 171);
		pnTiemKiemDonHang.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"M\u00E3 \u0110\u01A1n H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "Ng\u00E0y \u0110\u1EB7t",
					 "T\u00ECnh Tr\u1EA1ng", "Thanh To\u00E1n"
			}
		));
		
		JPanel pnChiTietDonHang = new JPanel();
		tabbedPane.addTab("Chi tiết đơn hàng", null, pnChiTietDonHang, null);
		
		JButton btnNewButton_1 = new JButton("Xem chi tiết đơn hàng đang chọn");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(27, 467, 520, 23);
		contentPane.add(btnNewButton_1);
	}
}



























