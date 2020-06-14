package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JTable;
import javax.swing.JComboBox;

import java.awt.Choice;

import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToolBar;
import java.awt.SystemColor;

public class Satici extends JFrame {
	public static Connection c;
	Object[] baslik = { "id", "Ad", "Soyad", "Yas", "Cinsiyet" };
	Object[][] veri;
	String urunad, fiyat, Aciklama;
	int kategori, tip, yeni;
	String kullanici;
	private JPanel b;
	private JTable table;
	private JTextField txt_ad;
	private JTextField txt_aciklama;
	private JTextField txt_fiyat;
	Connection conn;
	ResultSet rs = null;
	PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Satici frame = new Satici("");
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
	public Satici(String kulad1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 421);
		b = new JPanel();
		b.setBackground(SystemColor.activeCaption);
		b.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(b);
		b.setLayout(null);

		table = new JTable();
		table.setBounds(125, 74, -105, 54);
		b.add(table);
		table.setModel(new DefaultTableModel(veri, baslik));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sat\u0131c\u0131",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tabbedPane.setBounds(10, 11, 236, 343);
		b.add(tabbedPane);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Ürünler", null, panel, null);
		JButton btnNewButton_1 = new JButton("Kategoriler");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 21, 100, 56);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Saticidevam s = new Saticidevam();
				setVisible(false);
				s.setVisible(true);
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u00DCr\u00FCnler");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Esyalar s = new Esyalar();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(120, 21, 89, 56);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login s = new Login();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(10, 89, 89, 56);
		panel.add(btnNewButton_3);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ürün Ekle", null, panel_1, null);
		panel_1.setLayout(null);

		txt_ad = new JTextField();
		txt_ad.setBounds(67, 11, 131, 20);
		panel_1.add(txt_ad);
		txt_ad.setColumns(10);

		JRadioButton rd_1 = new JRadioButton("Beyaz E\u015Fyalar");
		rd_1.setBounds(6, 56, 93, 23);
		panel_1.add(rd_1);

		JRadioButton rd_2 = new JRadioButton("Ev,Bah\u00E7e,Ofis");
		rd_2.setBounds(6, 87, 93, 23);
		panel_1.add(rd_2);

		JRadioButton rd_3 = new JRadioButton("Elektronik");
		rd_3.setBounds(101, 56, 76, 23);
		panel_1.add(rd_3);

		JRadioButton rd_4 = new JRadioButton("Otomobil,Motosiklet");
		rd_4.setBounds(101, 87, 150, 23);
		panel_1.add(rd_4);

		JRadioButton rd_5 = new JRadioButton("Hobi,E\u011Flence,Sanat");
		rd_5.setBounds(6, 118, 147, 23);
		panel_1.add(rd_5);

		txt_aciklama = new JTextField();
		txt_aciklama.setBounds(67, 148, 86, 20);
		panel_1.add(txt_aciklama);
		txt_aciklama.setColumns(10);

		txt_fiyat = new JTextField();
		txt_fiyat.setBounds(67, 179, 86, 20);
		panel_1.add(txt_fiyat);
		txt_fiyat.setColumns(10);

		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				urunad = txt_ad.getText();
				Aciklama = txt_aciklama.getText();
				fiyat = txt_fiyat.getText();

				if (rd_1.isSelected())
					tip = 1;
				else if (rd_2.isSelected())
					tip = 2;
				else if (rd_3.isSelected())
					tip = 3;
				else if (rd_4.isSelected())
					tip = 4;
				else if (rd_5.isSelected())
					tip = 5;
				baglantiAc();

				try {
					String sql = "select *from kullanici where Kullanici_adi='" + kulad1 + "'";

					pst = c.prepareStatement(sql);

					rs = pst.executeQuery();

					if (rs.next())
						kullanici = rs.getString("KullaniciId");
					yeni = Integer.parseInt(kullanici);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ekle(tip, urunad, Aciklama, fiyat, yeni);
				JOptionPane.showMessageDialog(null, "Ürün Eklendi ");
				bagKapat();

			}
		});
		btnNewButton.setBounds(67, 210, 89, 23);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("\u00DCr\u00FCn Ad\u0131:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(6, 14, 62, 14);
		panel_1.add(lblNewLabel_4);

		JLabel lblAciklama = new JLabel("Aciklama:");
		lblAciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAciklama.setBounds(6, 145, 62, 14);
		panel_1.add(lblAciklama);

		JLabel lblNewLabel_5 = new JLabel("Fiyat:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(11, 182, 46, 14);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Kategori");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(6, 39, 76, 14);
		panel_1.add(lblNewLabel_6);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(252, 11, 210, 283);
		b.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\Zeynep\\Desktop\\HayvanPazariSon\\src\\hayvanpazari\\resources\\satici.png"));
		lblNewLabel.setBounds(45, 11, 140, 125);
		panel_2.add(lblNewLabel);

		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKullancAd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKullancAd.setBounds(25, 172, 79, 14);
		panel_2.add(lblKullancAd);

		JLabel lblNewLabel_1 = new JLabel(kulad1);
		lblNewLabel_1.setBounds(118, 172, 46, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Kullanici Tipi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(25, 214, 79, 14);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sat\u0131c\u0131");
		lblNewLabel_3.setBounds(118, 214, 46, 14);
		panel_2.add(lblNewLabel_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(25, 11, 160, 131);
		panel_2.add(panel_3);

	}

	public static void ekle(int tip, String urunad, String Aciklama, String fiyat, int yeni) {

		String sql = "INSERT INTO ürünler VALUES" + "('','" + tip + "','" + urunad + "','" + Aciklama + "', '" + fiyat
				+ "','" + yeni + "')";

		try {
			Statement sta = c.createStatement();
			sta.execute(sql);
			System.out.println("Ekleme baþarýlý");

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public static void baglantiAc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String bag = "jdbc:mysql://localhost:3306/proje";
			String kul = "root";
			String sif = "";

			c = DriverManager.getConnection(bag, kul, sif);
			System.out.println("Baðlantý baþarýlý");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void bagKapat() {
		try {
			c.close();
			System.out.println("Baðlantý baþarýyla kapatýldý.");

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
}
