package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bussinessLogicLayer.UserBL;
import bussinessLogicLayer.VaiTroBL;
import dataTransferObject.NguoiDung;
import dataTransferObject.VaiTro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class Frm_QL_NguoiDung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenNguoiDung;
	private JTable table;
	private JTextField txtID;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtHoTen;
	private JTextField txtDTDD;
	private DefaultTableModel tableModel;  // Thêm biến này
	private JComboBox cbVaiTro;
	private int[] id_vaitro_arr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_QL_NguoiDung frame = new Frm_QL_NguoiDung();
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
	public Frm_QL_NguoiDung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên người dùng");
		lblNewLabel.setBounds(26, 28, 124, 21);
		contentPane.add(lblNewLabel);
		
		txtTenNguoiDung = new JTextField();
		txtTenNguoiDung.setBounds(144, 25, 290, 27);
		contentPane.add(txtTenNguoiDung);
		txtTenNguoiDung.setColumns(10);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<NguoiDung> dsNguoiDung;
					String param = txtTenNguoiDung.getText();
					String sql = "SELECT * FROM nguoidung WHERE nguoidung.hoten like '%"
					+ param + "%'";
					
					dsNguoiDung = UserBL.dsNguoiDung(sql);

					tableModel.setRowCount(0);
					
					id_vaitro_arr = new int[dsNguoiDung.size()];
					int i = 0;
			        for (NguoiDung nguoiDung : dsNguoiDung) {
			        	Object[] rowData = {
			        			nguoiDung.getId(),
			        			nguoiDung.getEmail(),
			        			nguoiDung.getHoTen(),
			        			nguoiDung.getDiaChi(),
			        			nguoiDung.getDtdd()
			        	};
			        	tableModel.addRow(rowData);
			        	id_vaitro_arr[i] = nguoiDung.getIdVaiTro();
			        	i++;
			        }
			        
			        for (int a : id_vaitro_arr)
			        	System.out.println(a);
			        
				} catch (SQLException e1) {					
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, 
	                        "Lỗi khi tìm kiếm: " + e1.getMessage(),
	                        "Lỗi",
	                        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTim.setBounds(460, 24, 152, 28);
		contentPane.add(btnTim);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 78, 586, 134);
		contentPane.add(scrollPane);
		
		String[] columnNames = {"ID", "Email", "Họ tên", "Địa chỉ", "DTDD"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @SuppressWarnings("unchecked")
			@Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()) { // Kiểm tra để tránh sự kiện xảy ra nhiều lần khi chọn dòng
		        	try {
			        	int selectedRow = table.getSelectedRow();
			            if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
			                Integer value = (Integer) table.getValueAt(selectedRow, 0); // Lấy giá trị cột đầu tiên
			                txtID.setText(String.valueOf(value));
			                txtEmail.setText(String.valueOf(table.getValueAt(selectedRow, 1)));
			                txtHoTen.setText(String.valueOf(table.getValueAt(selectedRow, 2)));
			                txtDiaChi.setText(String.valueOf(table.getValueAt(selectedRow, 3)));
			                txtDTDD.setText(String.valueOf(table.getValueAt(selectedRow, 4)));
			                
							List<VaiTro> lsVaiTro = VaiTroBL.dsVaiTro();
							
							int selectedIndex = 0;							
							String[] arrVaiTro = new String[lsVaiTro.size()];
							
							// Create array of role names and find the user's role index
							for (int i = 0; i < lsVaiTro.size(); i++) {
								// String tenVaiTro = lsVaiTro.get(i).getTenVaiTro();
								// arrVaiTro[i] = tenVaiTro;
								VaiTro vaiTro = lsVaiTro.get(i);
								arrVaiTro[i] = vaiTro.getTenVaiTro();
								
								// Check if this is the user's role	
								if (vaiTro.getId() == id_vaitro_arr[selectedRow]) 
									selectedIndex = i;
							}
							
							// Update combobox model and select the correct role
							cbVaiTro.setModel(new DefaultComboBoxModel<>(arrVaiTro));
							cbVaiTro.setSelectedIndex(selectedIndex);
			            }
		        	} catch (SQLException e) {
						e.printStackTrace();
					}
		        }
		    }
		});

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(26, 242, 77, 21);
		contentPane.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setBounds(91, 239, 53, 27);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setBounds(26, 280, 77, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1.setBounds(26, 324, 77, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(91, 277, 244, 27);
		contentPane.add(txtEmail);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(91, 321, 244, 27);
		contentPane.add(txtDiaChi);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vai trò");
		lblNewLabel_1_2.setBounds(375, 242, 77, 21);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Họ tên");
		lblNewLabel_1_1_2.setBounds(375, 280, 77, 21);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(437, 280, 175, 27);
		contentPane.add(txtHoTen);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("DTDD");
		lblNewLabel_1_1_1_1.setBounds(375, 324, 77, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtDTDD = new JTextField();
		txtDTDD.setColumns(10);
		txtDTDD.setBounds(437, 327, 175, 27);
		contentPane.add(txtDTDD);
		
		cbVaiTro = new JComboBox();
		cbVaiTro.setBounds(434, 236, 178, 27);
		contentPane.add(cbVaiTro);
		
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(91, 359, 143, 28);
		contentPane.add(btnNewButton);
	}

}
