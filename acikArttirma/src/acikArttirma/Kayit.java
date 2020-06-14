package acikArttirma;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import acikArttirma.Login;

import javax.swing.UIManager;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class Kayit extends JFrame {
	public static Connection c;
	int tip;
	String kulad,isim,soyad,sifre,yas;
	private JPanel contentPane;
	private JTextField txt_kulad;
	private JTextField txt_isim;
	private JTextField txt_soyad;
	private JPasswordField txt_sifre;
	private JPasswordField txt_sifretekrar;
	private JTextField txt_yas1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kayit frame = new Kayit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Kayit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 285);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bilgiler", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 22, 414, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txt_kulad = new JTextField();
		txt_kulad.setBounds(81, 31, 86, 20);
		panel.add(txt_kulad);
		txt_kulad.setColumns(10);
		
		txt_isim = new JTextField();
		txt_isim.setBounds(81, 93, 86, 20);
		panel.add(txt_isim);
		txt_isim.setColumns(10);
		
		txt_soyad = new JTextField();
		txt_soyad.setBounds(288, 93, 86, 20);
		panel.add(txt_soyad);
		txt_soyad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sifre");
		lblNewLabel.setBounds(10, 65, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0130sim");
		lblNewLabel_1.setBounds(10, 96, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel_2.setBounds(10, 34, 74, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Soyisim");
		lblNewLabel_3.setBounds(204, 96, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sifre Tekrar");
		lblNewLabel_4.setBounds(204, 65, 74, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Yas");
		lblNewLabel_5.setBounds(208, 34, 46, 14);
		panel.add(lblNewLabel_5);
		
		JRadioButton rd_musteri = new JRadioButton("M\u00FC\u015Fteri");
		rd_musteri.setBounds(81, 139, 109, 23);
		panel.add(rd_musteri);
		
		JRadioButton rd_satici = new JRadioButton("Sat\u0131c\u0131");
		rd_satici.setBounds(277, 139, 109, 23);
		panel.add(rd_satici);
		
		JButton btnNewButton = new JButton("Kayit Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kulad=txt_kulad.getText();
		        isim=txt_isim.getText();
		        soyad=txt_soyad.getText();
		        sifre=txt_sifre.getText();
		        yas=txt_yas1.getText();
		        int onay=1;
		        
				if(rd_musteri.isSelected())
			     	tip=1;
			     else if(rd_satici.isSelected())
			     	tip=2;
				baglantiAc();
				ekle(tip,kulad,isim,soyad,sifre,yas,onay);
				bagKapat();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(259, 169, 109, 23);
		panel.add(btnNewButton);
		
		txt_sifre = new JPasswordField();
		txt_sifre.setBounds(81, 62, 86, 20);
		panel.add(txt_sifre);
		
		txt_sifretekrar = new JPasswordField();
		txt_sifretekrar.setBounds(288, 62, 86, 20);
		panel.add(txt_sifretekrar);
		
		txt_yas1 = new JTextField();
		txt_yas1.setBounds(288, 31, 86, 20);
		panel.add(txt_yas1);
		txt_yas1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\Zeynep\\Desktop\\HayvanPazariSon\\src\\hayvanpazari\\resources\\musteriS.png"));
		lblNewLabel_6.setBounds(48, 133, 36, 39);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Zeynep\\Desktop\\HayvanPazariSon\\src\\hayvanpazari\\resources\\saticiS.png"));
		lblNewLabel_7.setBounds(232, 135, 46, 30);
		panel.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			        setVisible(false);
			     
			}
		});
		btnNewButton_1.setBounds(145, 169, 89, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton.addActionListener( new ActionListener() {
	         public void actionPerformed( ActionEvent event )
	         {
	           

	         } 

	      }

	   );
		 
		}
public static void ekle(int tip,String kulad,String isim,String soyad,String sifre,String yas,int onay){
		
		
		String sql = "INSERT INTO kullanici VALUES"+ "('"+ tip+"','"+kulad+"','"+isim+"', '" + soyad + "','" + sifre + "', '" + yas+"','',' " +onay+" ')";
        
	   
		try {
			Statement sta = c.createStatement();
			sta.execute(sql);
			System.out.println("Ekleme baþarýlý");	
			
			Login s =new Login();
	       s.setVisible(true);
	       
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public static void baglantiAc(){
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
	
	public static void bagKapat(){
		try {
			c.close();
			System.out.println("Baðlantý baþarýyla kapatýldý.");	
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
}
