package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Teklifver extends JFrame {

	private JPanel contentPane;
	private JTextField AdTextField;
	private JTextField soyadTextField;
	private JTextField yasTextField;
	private JTable jTable1;
	private Connection conn = null;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teklifver frame = new Teklifver();
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
	public Teklifver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		jTable1 = new JTable();
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 //Tabloda üzerine týklanýlan satýrýn bilgilerini ilgili form elemanlarýna yazar.
			    AdTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
			    soyadTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
			    yasTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
			    String cinsiyet = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
			    JComboBox cinsiyetComboBox = null;
				if (cinsiyet.equals("1")){
			         cinsiyetComboBox.setSelectedIndex(1);
			    }
			    else{
			        cinsiyetComboBox.setSelectedIndex(0);
			   }
			}
		});
		jTable1.setBounds(24, 11, 365, 119);
		panel.add(jTable1);
		
		AdTextField = new JTextField();
		AdTextField.setBounds(72, 163, 86, 20);
		contentPane.add(AdTextField);
		AdTextField.setColumns(10);
		
		soyadTextField = new JTextField();
		soyadTextField.setBounds(72, 203, 86, 20);
		contentPane.add(soyadTextField);
		soyadTextField.setColumns(10);
		
		yasTextField = new JTextField();
		yasTextField.setBounds(72, 247, 86, 20);
		contentPane.add(yasTextField);
		yasTextField.setColumns(10);
		
		JComboBox cinsiyetComboBox = new JComboBox();
		cinsiyetComboBox.setBounds(72, 278, 87, 20);
		contentPane.add(cinsiyetComboBox);
		
		JLabel lblNewLabel = new JLabel("ad");
		lblNewLabel.setBounds(10, 163, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("soyad");
		lblNewLabel_1.setBounds(10, 206, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("yas");
		lblNewLabel_2.setBounds(10, 250, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("yeni");
		btnNewButton.setBounds(209, 163, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton ekleButton = new JButton("ekle");
		ekleButton.setBounds(209, 202, 89, 23);
		contentPane.add(ekleButton);
		
		JButton guncelleButton = new JButton("g\u00FCncelle");
		guncelleButton.setBounds(209, 246, 89, 23);
		contentPane.add(guncelleButton);
		
		JButton silButton = new JButton("sil");
		silButton.setBounds(209, 286, 89, 23);
		contentPane.add(silButton);
	}
	public void TabloDoldur() {
		try {
		 
		    Statement st = baglantiAc(); //Baðlantýyý aç
		    ResultSet res = ((java.sql.Statement) st).executeQuery("SELECT * FROM  ürünler"); //VT'den kayýtlarý ResultSet'e al
		    MyTableModel model = new MyTableModel(res); //Tablomuza model oluþtur
		    jTable1.setModel(model); //Tabloyu res'teki kayýtlar ile doldur
		    baglantiKapat(); //Baðlantýyý kapat
		 
		} catch (Exception e) {
		    JOptionPane.showConfirmDialog(null, "Baðlantý Baþarýsýz", "MySQL Baðlantýsý", JOptionPane.PLAIN_MESSAGE);
		}
		}
	public void KayitEkle(String ad, String soyad, String yas, String cinsiyet) {
		try {
		 
		    Statement st = baglantiAc();
		    String sql= "INSERT INTO vt.kisiler (Ad, Soyad, Yas, Cinsiyet) VALUES (" +
		    "'" + ad + "', " +
		    "'" + soyad + "', " +
		    "" + yas + ", " +
		    "" + cinsiyet + ")";
		 
		    //System.out.println(sql); sorgunun doðru yazýldýðýndan emin olmak için ekrana yazdýrabilirsiniz.
		 
		    ((java.sql.Statement) st).executeUpdate(sql); //sorguyu çalýþtýr
		 
		    baglantiKapat(); //baðlantýyý kapat
		 
		    TabloDoldur(); //Penceremizdeki tabloyu yeniden doldur (güncelle)
		 
		}catch (Exception e) {
		JOptionPane.showConfirmDialog(null, "Kayýt Eklenemedi", "Kisiler Tablosu", JOptionPane.PLAIN_MESSAGE);
		}
		}
	public void KayitSil(String ID) {
		try {
		    Statement st = baglantiAc();
		    ((java.sql.Statement) st).executeUpdate("Delete FROM  kisiler where id=" + ID);
		    baglantiKapat(); //baðlantýyý kapat
		    TabloDoldur();  //Penceremizdeki tabloyu yeniden doldur (güncelle)
		} catch (Exception e) {
		JOptionPane.showConfirmDialog(null, "Kayýt Silinemedi", "Kiþiler Tablosu", JOptionPane.PLAIN_MESSAGE);
		}
		}
	public void KayitGuncelle(String ID, String ad, String soyad, String yas, String cinsiyet) {
		try {
		    Statement st = baglantiAc();
		    String sql = "UPDATE vt.kisiler SET " +
		    "Ad='" + ad + "', " +
		    "Soyad='" + soyad + "', " +
		    "yas=" + yas + ", " +
		    "cinsiyet=" + cinsiyet +
		    " WHERE ID=" + ID ;
		 
		    ((java.sql.Statement) st).executeUpdate(sql); //sorguyu çalýþtýr
		    baglantiKapat(); //baðlantýyý kapat
		    TabloDoldur(); //Penceremizdeki tabloyu yeniden doldur (güncelle)
		 
		} catch (Exception e) {
		JOptionPane.showConfirmDialog(null, "Kayýt Güncellenemedi", "Kiþiler Tablosu", JOptionPane.PLAIN_MESSAGE);
		}
		}
	public Statement baglantiAc() throws Exception {
	    Class.forName(driver).newInstance();
	    conn = (Connection) DriverManager.getConnection(url + dbName + properties, userName, password);//baðlantý açýlýyor
	    return (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    //return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	 
	}
	public void baglantiKapat() throws Exception {
	    conn.close();
	}
}
