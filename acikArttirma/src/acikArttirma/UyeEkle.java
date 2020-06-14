package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.border.BevelBorder;
import javax.swing.AbstractButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.SystemColor;

public class UyeEkle extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ad;
	private JTextField txt_sifre1;
	private JTextField txt_isim1;
	private JTextField txt_soyisim;
	private JTextField txt_yas1;
	public static Connection c;
	int tip;
	String kulad, isim, soyad, sifre, yas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeEkle frame = new UyeEkle();
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
	public UyeEkle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 404);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(34, 11, 247, 343);
		contentPane.add(panel);
		panel.setLayout(null);

		txt_ad = new JTextField();
		txt_ad.setBounds(117, 43, 86, 20);
		panel.add(txt_ad);
		txt_ad.setColumns(10);

		txt_sifre1 = new JTextField();
		txt_sifre1.setBounds(117, 78, 86, 20);
		panel.add(txt_sifre1);
		txt_sifre1.setColumns(10);

		txt_isim1 = new JTextField();
		txt_isim1.setBounds(117, 109, 86, 20);
		panel.add(txt_isim1);
		txt_isim1.setColumns(10);

		txt_soyisim = new JTextField();
		txt_soyisim.setBounds(117, 140, 86, 20);
		panel.add(txt_soyisim);
		txt_soyisim.setColumns(10);

		txt_yas1 = new JTextField();
		txt_yas1.setBounds(117, 171, 86, 20);
		panel.add(txt_yas1);
		txt_yas1.setColumns(10);

		JLabel txt_kulad = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		txt_kulad.setBounds(24, 46, 83, 14);
		panel.add(txt_kulad);

		JLabel txt_sifre = new JLabel("Sifre:");
		txt_sifre.setBounds(24, 81, 46, 14);
		panel.add(txt_sifre);

		JLabel txt_isim = new JLabel("\u0130sim:");
		txt_isim.setBounds(24, 112, 46, 14);
		panel.add(txt_isim);

		JLabel txt_soyad = new JLabel("Soy \u0130sim:");
		txt_soyad.setBounds(24, 140, 46, 14);
		panel.add(txt_soyad);

		JLabel txt_yas = new JLabel("Ya\u015F:");
		txt_yas.setBounds(24, 174, 46, 14);
		panel.add(txt_yas);

		JRadioButton rd_musteri1 = new JRadioButton("M\u00FC\u015Fteri");
		rd_musteri1.setBackground(SystemColor.activeCaption);
		rd_musteri1.setBounds(20, 225, 109, 23);
		panel.add(rd_musteri1);

		JRadioButton rd_satici1 = new JRadioButton("Sat\u0131c\u0131");
		rd_satici1.setBackground(SystemColor.activeCaption);
		rd_satici1.setBounds(131, 225, 109, 23);
		panel.add(rd_satici1);

		JButton btnNewButton = new JButton("Kay\u0131t");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kulad = txt_ad.getText();
				isim = txt_isim1.getText();
				soyad = txt_soyisim.getText();
				sifre = txt_sifre1.getText();
				yas = txt_yas1.getText();
				int onay = 1;

				if (rd_musteri1.isSelected())
					tip = 1;
				else if (rd_satici1.isSelected())
					tip = 2;
				baglantiAc();
				ekle(tip, kulad, isim, soyad, sifre, yas, onay);
				bagKapat();
				Yonetici s = new Yonetici();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnNewButton.setBounds(148, 289, 89, 23);
		panel.add(btnNewButton);

		JButton btnGeriDn = new JButton("Geri D\u00F6n");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Yonetici s = new Yonetici();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnGeriDn.setBounds(40, 289, 89, 23);
		panel.add(btnGeriDn);

	}

	public static void ekle(int tip, String kulad, String isim, String soyad, String sifre, String yas, int onay) {

		String sql = "INSERT INTO kullanici VALUES" + "('" + tip + "','" + kulad + "','" + isim + "', '" + soyad + "','"
				+ sifre + "', '" + yas + "','',' " + onay + " ')";

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
