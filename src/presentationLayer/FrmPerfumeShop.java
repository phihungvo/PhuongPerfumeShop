package presentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.plaf.DesktopPaneUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

public class FrmPerfumeShop {

	private JFrame frmQuanLyPerfume;
	private JDesktopPane desktopPane ;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_7;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmNewMenuItem_8;
	private JMenuItem mntmNewMenuItem_9;
	private JMenu mnNewMenu_3;
	private JMenuItem mntmNewMenuItem_10;
	private JMenuItem mntmNewMenuItem_11;
	private JMenu mnNewMenu_4;
	private JMenuItem mntmNewMenuItem_12;
	private JMenuItem mntmNewMenuItem_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPerfumeShop window = new FrmPerfumeShop();
					window.frmQuanLyPerfume.setExtendedState(JFrame.MAXIMIZED_BOTH);;
					window.frmQuanLyPerfume.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmPerfumeShop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuanLyPerfume = new JFrame();
		frmQuanLyPerfume.setTitle("Quan ly PerfumeShop");
		frmQuanLyPerfume.setBounds(100, 100, 741, 300);
		frmQuanLyPerfume.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmQuanLyPerfume.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_5 = new JMenu("He thong");
		menuBar.add(mnNewMenu_5);
		
		mntmNewMenuItem_4 = new JMenuItem("Gioi thieu");
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("Dang nhap");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDangNhap fdn= new FrmDangNhap();
				desktopPane.add(fdn);
				fdn.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_6 = new JMenuItem("Thoat ra");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_6);
		
		mnNewMenu = new JMenu("San pham");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Quan ly thuong hieu");
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_2 = new JMenuItem("Quan ly loai");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mnNewMenu_1 = new JMenu("Quan ly san pham");
		mnNewMenu.add(mnNewMenu_1);
		
		mntmNewMenuItem_3 = new JMenuItem("Them moi san pham");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_7 = new JMenuItem("Cap nhat san pham");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		mnNewMenu_2 = new JMenu("Don hang");
		menuBar.add(mnNewMenu_2);
		
		mntmNewMenuItem_8 = new JMenuItem("Quan ly don hang");
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		mntmNewMenuItem_9 = new JMenuItem("Thong ke don hang");
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		mnNewMenu_3 = new JMenu("Quang cao");
		menuBar.add(mnNewMenu_3);
		
		mntmNewMenuItem_10 = new JMenuItem("Them moi quang cao");
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		mntmNewMenuItem_11 = new JMenuItem("Cap nhat quang cao");
		mnNewMenu_3.add(mntmNewMenuItem_11);
		
		mnNewMenu_4 = new JMenu("Nguoi dung");
		menuBar.add(mnNewMenu_4);
		
		mntmNewMenuItem_12 = new JMenuItem("Quan ly vai tro");
		mnNewMenu_4.add(mntmNewMenuItem_12);
		
		mntmNewMenuItem_13 = new JMenuItem("Quan ly nguoi dung");
		mnNewMenu_4.add(mntmNewMenuItem_13);
		frmQuanLyPerfume.getContentPane().setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		frmQuanLyPerfume.getContentPane().add(desktopPane, BorderLayout.CENTER);
	}
}
