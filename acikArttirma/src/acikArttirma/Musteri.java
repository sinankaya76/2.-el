package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.awt.SystemColor;

public class Musteri extends JFrame {
	public static Connection c;
	private JPanel contentPane;
	int urunId, yeni;
	String Miktar, kullanici;
	String gecici;
	Connection conn;
	private Connection baglayici = null;
	ResultSet rs = null;
	ResultSet st = null;
	PreparedStatement pst = null;
	static ResultSet personelSonucKumesi = null;
	private JTextField txt_teklif;

	private String url = "jdbc:mysql://localhost:3306/proje";// Veritabanýmýzýn urlsi
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root"; // MySQL kullanýcý adý
	private String password = " "; // MySQL parolasý
	private ResultSet personellerSonucKumesi;// Verilerin çekildiðinde saklanacaðý kýsým
	private Statement st1;// kontrol aracýmýz

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Musteri frame = new Musteri("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Musteri(String kulad1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setToolTipText("Bilgiler");
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(258, 11, 166, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\Zeynep\\Desktop\\HayvanPazariSon\\src\\hayvanpazari\\resources\\musteri.png"));
		lblNewLabel.setBounds(25, 11, 113, 128);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 150, 76, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Kullan\u0131c\u0131 Tipi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 197, 76, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(kulad1);
		lblNewLabel_3.setBounds(96, 150, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("M\u00FC\u015Fteri");
		lblNewLabel_4.setBounds(92, 197, 64, 14);
		panel.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 11, 238, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JComboBox personelCombo = new JComboBox();
		personelCombo.setBounds(94, 41, 99, 20);
		panel_1.add(personelCombo);
		String[] illerdizi = new String[4];
		illerdizi[0] = "ütü";
		illerdizi[1] = "dolap";
		illerdizi[2] = "telefon";
		illerdizi[3] = "müzik aletleri";
		ComboBoxModel illermodel = new DefaultComboBoxModel(illerdizi);
		personelCombo.setModel(illermodel);
		JButton btnNewButton = new JButton("Teklif Ver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Miktar = txt_teklif.getText();
				int Onay = 1;
				baglantiAc();
				urunId = personelCombo.getSelectedIndex();
				try {
					String sql = "select *from kullanici where Kullanici_adi='" + kulad1 + "'";
					pst = c.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next())
						kullanici = rs.getString("KullaniciId");
					yeni = Integer.parseInt(kullanici);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ekle(urunId, Miktar, Onay, yeni);
				JOptionPane.showMessageDialog(null, "Teklif Verildi ");
				bagKapat();
			}
		});
		btnNewButton.setBounds(94, 134, 89, 23);
		panel_1.add(btnNewButton);
		txt_teklif = new JTextField();
		txt_teklif.setBounds(94, 85, 99, 20);
		panel_1.add(txt_teklif);
		txt_teklif.setColumns(10);
		JButton btnNewButton_1 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login s = new Login();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(94, 172, 89, 23);
		panel_1.add(btnNewButton_1);
		JLabel lblNewLabel_5 = new JLabel("\u00DCr\u00FCnler");
		lblNewLabel_5.setBounds(21, 44, 46, 14);
		panel_1.add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("Teklif");
		lblNewLabel_6.setBounds(21, 88, 46, 14);
		panel_1.add(lblNewLabel_6);
	}

	public void personelComboYaz() {
		try {
			while (personelSonucKumesi.next()) {
				Combo1.addItem(personelSonucKumesi.getString("ad"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ekle(int urunId, String Miktar, int Onay, int yeni) {
		String sql = "INSERT INTO teklif VALUES" + "('','" + urunId + "','" + Miktar + "','" + Onay + "', '" + yeni
				+ "')";
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

	public Statement baglantiAc1() throws Exception {
		String driver = null;
		Class.forName(driver).newInstance();
		baglayici = DriverManager.getConnection(url, userName, password);
		return baglayici.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
}
