package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bussinessLogicLayer.NguoiDungBL;
import dataTransferObject.NguoiDung;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmDangNhap.class.getResource("/images/d_g.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(127, 57, 230, 29);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(127, 118, 230, 29);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(35, 64, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(35, 125, 46, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email, password;
				
				email = txtEmail.getText();
				password = txtPassword.getText();
				
				NguoiDung nd;
				try {
					nd = NguoiDungBL.dangNhapNguoiDung(email, password);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (nd != null) {
					JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công");
				}else {
					JOptionPane.showMessageDialog(rootPane, "Đăng nhập không thành công! Sai Email hoặc Password!");
				}
			}
		});
		btnLogin.setBounds(140, 183, 124, 29);
		contentPane.add(btnLogin);
	}
}
