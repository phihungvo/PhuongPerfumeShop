package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bussinessLogicLayer.NguoiDungBL;
import dataTransferObject.NguoiDung;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDangNhap extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
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
	public FrmDangNhap() {
		setClosable(true);
		setTitle("Dang nhap");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(55, 31, 81, 23);
		getContentPane().add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(146, 31, 242, 23);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(55, 104, 81, 23);
		getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(146, 105, 242, 21);
		getContentPane().add(txtPassword);
		
		JButton btnNewButton = new JButton("Dang nhap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email, password;
				
				email = txtEmail.getText();
				password = new String(txtPassword.getPassword());
				
				NguoiDung nd = NguoiDungBL.timNguoiDung(email, password);
				
				if (nd != null) {
					JOptionPane.showMessageDialog(rootPane, "Dang nhap thanh cong");
				}else
					JOptionPane.showMessageDialog(rootPane, "Dang nhap khong thanh cong! Sai Email hoac Password!");
				
				
			}
		});
		btnNewButton.setBounds(110, 160, 194, 41);
		getContentPane().add(btnNewButton);

	}
}
