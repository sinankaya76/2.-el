package acikArttirma;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import com.mysql.jdbc.Connection;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;
public class Teklif extends JFrame {
	private JPanel contentPane;
	private JTable table_1;
	private JButton btn_ekle;
	private JButton btn_sil;
	private JButton btn_guncelle;
	private JTextField AdTextField;
	private JTextField txt_aciklama;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txt_fiyat;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnTeklifVer;
	private Connection conn = null; //Baðlantý nesnemiz
	private String url = "jdbc:mysql://localhost:3306/";//veritabanýnýn adresi ve portu
	private String dbName = "proje"; //veritabanýnýn ismi
	private String properties= "?useUnicode=true&amp;characterEncoding=utf8"; //Türkçe karakter problemi yaþamamak için
	private String driver = "com.mysql.jdbc.Driver";//MySQL-Java baðlantýsýný saðlayan JDBC sürücüsü
	private String userName = "root"; //veritabaný için kullanýcý adý
	private String password = ""; //kullanýcý þifresi
	private ResultSet res; // sorgulardan dönecek kayýtlar (sonuç kümesi) bu nesne içerisinde tutulacak
	 int urunId,yeni;
	    String Miktar,kullanici;
	    String gecici;
	    
		ResultSet rs=null;
		ResultSet st1=null;
		PreparedStatement pst=null;
		PreparedStatement personelSonucKumesi=null;
	Statement st=null;
	private JComboBox personelCombo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teklif frame = new Teklif("");
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
	public Teklif(String kulad1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 426);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		table_1.setBounds(50, 41, 357, 154);
		contentPane.add(table_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 11, 450, 202);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("\u00DCr\u00FCn Ad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(11, 243, 52, 14);
		contentPane.add(lblNewLabel);
		
		AdTextField = new JTextField();
		AdTextField.setBounds(73, 240, 86, 20);
		contentPane.add(AdTextField);
		AdTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Aciklama");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 368, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txt_aciklama = new JTextField();
		txt_aciklama.setBounds(73, 361, 89, 29);
		contentPane.add(txt_aciklama);
		txt_aciklama.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Fiyat");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 285, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setBounds(73, 282, 86, 20);
		contentPane.add(txt_fiyat);
		txt_fiyat.setColumns(10);
		
		lblNewLabel_3 = new JLabel("\u00DCr\u00FCn Id");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 330, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(73, 327, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btn_guncelle = new JButton("G\u00FCncelle");
		btn_guncelle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_guncelle.setBounds(70, 457, 89, 23);
		contentPane.add(btn_guncelle);
		
		btn_ekle = new JButton("Ekle");
		btn_ekle.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_ekle.setBounds(73, 423, 89, 23);
		contentPane.add(btn_ekle);
		
		btn_sil = new JButton("Sil");
		btn_sil.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_sil.setBounds(169, 457, 89, 23);
		contentPane.add(btn_sil);
		
		textField_3 = new JTextField();
		textField_3.setBounds(294, 282, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Teklif Ver");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(208, 285, 59, 14);
		contentPane.add(lblNewLabel_4);
		
		btnTeklifVer = new JButton("Teklif ver");
		btnTeklifVer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTeklifVer.setBounds(294, 326, 89, 23);
		contentPane.add(btnTeklifVer);
		
		personelCombo = new JComboBox();
		personelCombo.setBounds(294, 240, 84, 20);
		contentPane.add(personelCombo);
		 String[] illerdizi = new String[5];
	        
	       
	        illerdizi[0] ="1";
	        illerdizi[1] = "2";
	        illerdizi[2] = "3";
	        illerdizi[3] = "4";
	        illerdizi[4] = "5"; 
	        
	        ComboBoxModel illermodel = new DefaultComboBoxModel(illerdizi);
	        //setModel diyerek modelimizi combobox a aktarýyoruz ve ekranda
	        //gösteriyoruz.
	        personelCombo.setModel(illermodel);
		btn_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = table_1.getValueAt(table_1.getSelectedRow(), 0).toString();
			    KayitSil(ID);
			}
		});
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* String ad = AdTextField.getText();
				    String soyad = txt_aciklama.getText();
				    String yas = txt_fiyat.getText();
				    String cinsiyet;
				   
				 
				    KayitEkle(ad, soyad, yas,kulad1); */
			}
		});
		btn_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String ID = table_1.getValueAt(table_1.getSelectedRow(), 0).toString();
			    String ad = AdTextField.getText();
			    String soyad = txt_aciklama.getText();
			    String yas = txt_fiyat.getText();
			    
			   
			 
			KayitGuncelle(ID, ad, soyad, yas); */
			}
		});
	}
}
}