package acikArttirma;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class Login extends JFrame {
	public static Connection c;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    private JTextField textkullanýcý;
    private JPasswordField  passwordField_1 ;
    Connection conn;
    ResultSet rs=null;
    PreparedStatement pst=null;
	    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		textkullanýcý = new JTextField();
		textkullanýcý.setBounds(81, 31, 86, 20);
		textkullanýcý.setColumns(10);
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(298, 62, 6, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 366);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton jButton = new JButton("Kay\u0131t Ol");
		jButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Kayit urunvs = new Kayit ();
            urunvs.setVisible(true); 
			}
		});
		jButton.setBounds(127, 198, 89, 23);
		contentPane.add(zeynep);
		JButton btnNewButton = new JButton("Giri\u015F");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
		baglantiAc();
		        String sql ="select * from kullanici where Kullanici_adi=? and Sifre=?";
		        try{
		        pst=c.prepareStatement(sql);
		        pst.setString(1,textField.getText());
		        pst.setString(2,passwordField.getText());
		        rs=pst.executeQuery();
		        if(rs.next()){
		        JOptionPane.showMessageDialog(null, "Kullanýcý Adý Ve Þifre Doðru");
		        String tip=rs.getString("Kullanici_tipi");
		        String kulad1=rs.getString("Kullanici_adi");
		        System.out.println(tip);
		        int yeni1=Integer.parseInt(tip);
		        if(yeni1==1)
		        {
		        Musteri s =new Musteri(kulad1);
		        setVisible(false);
		       s.setVisible(true);
		        }
		        if(yeni1==2)
		        {
		        Satici s =new Satici(kulad1);
		        setVisible(false);
		       s.setVisible(true);
		        }
		        if(yeni1==0)
		        {
		        Yonetici s =new Yonetici();
		        setVisible(false);
		       s.setVisible(true);
		        }
		        rs.close();
			      pst.close();
		        }
		        else{
		         JOptionPane.showMessageDialog(null, "KullanýcýAdý Ve Þifre Yanlýþ");
		     
		     
		        }
		        }
		        catch(Exception e1)
		    {
		           JOptionPane.showMessageDialog(null, e1);

		    } finally {
		try{
		  rs.close();
		      pst.close();
		 
		  }
		  catch(Exception e1) {
		                   }
		      }
		        bagKapat();
			}
		});
		btnNewButton.setBounds(239, 198, 89, 23);
		contentPane.add(btnNewButton);
		textField = new JTextField();
		textField.setBounds(242, 130, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(100, 0, 268, 108);
		contentPane.add(lblNewLabel_1);
		Image img=new ImageIcon(this.getClass().getResource("/logom.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		JLabel lblNewLabel_2 = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(133, 130, 99, 20);
		contentPane.add(lblNewLabel_2);
		passwordField = new JPasswordField();
		passwordField.setBounds(242, 158, 86, 20);
		contentPane.add(passwordField);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(100, 115, 262, 135);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Sifre");
		lblNewLabel.setBounds(34, 50, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
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
