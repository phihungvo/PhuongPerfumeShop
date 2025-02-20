package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Frm_QL_VaiTro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenVaiTro;
	private JTextField txtMoTa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_QL_VaiTro frame = new Frm_QL_VaiTro();
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
	public Frm_QL_VaiTro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên vai trò");
		lblNewLabel.setBounds(29, 29, 101, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMT = new JLabel("Mô tả");
		lblMT.setBounds(29, 77, 101, 14);
		contentPane.add(lblMT);
		
		txtTenVaiTro = new JTextField();
		txtTenVaiTro.setBounds(151, 22, 190, 29);
		contentPane.add(txtTenVaiTro);
		txtTenVaiTro.setColumns(10);
		
		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(151, 62, 190, 29);
		contentPane.add(txtMoTa);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(74, 105, 89, 23);
		contentPane.add(btnThem);
		
		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.setBounds(238, 105, 89, 23);
		contentPane.add(btnTiepTuc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 146, 383, 84);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cập nhật bảng vai trò");
		btnNewButton.setBounds(29, 241, 183, 23);
		contentPane.add(btnNewButton);
	}

}
