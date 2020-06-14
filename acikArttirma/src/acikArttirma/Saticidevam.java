package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import javax.swing.JComboBox;

import java.awt.Choice;
import java.awt.Panel;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Saticidevam extends JFrame {
	private JPanel contentPane;
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Saticidevam frame = new Saticidevam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Saticidevam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table_1 = new JTable();
		table_1.setBounds(68, 53, 283, 152);
		contentPane.add(table_1);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje", "root", ""); // Mysql
																											// sunucusuna
																											// baðlandýk
			Statement st = (Statement) con.createStatement();
			try (ResultSet rs = st.executeQuery("Select * from kategori")) { // Veritabanýndaki tabloya baðlandýk
				int colcount = rs.getMetaData().getColumnCount(); // Veritabanýndaki tabloda kaç tane sütun var?
				DefaultTableModel tm = new DefaultTableModel(); // Model oluþturuyoruz
				for (int i = 1; i <= colcount; i++)
					tm.addColumn(rs.getMetaData().getColumnName(i)); // Tabloya sütun ekliyoruz veritabanýmýzdaki sütun
																		// ismiyle ayný olacak þekilde
				while (rs.next()) {
					Object[] row = new Object[colcount];
					for (int i = 1; i <= colcount; i++)
						row[i - 1] = rs.getObject(i);
					tm.addRow(row);
				}
				table_1.setModel(tm);

				JLabel lblNewLabel = new JLabel("KATEGOR\u0130LER");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setBounds(70, 8, 153, 34);
				contentPane.add(lblNewLabel);

				JButton btnNewButton = new JButton("Geri D\u00F6n");
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Satici s = new Satici("");
						setVisible(false);
						s.setVisible(true);
					}
				});
				btnNewButton.setBounds(68, 227, 89, 23);
				contentPane.add(btnNewButton);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException hata) {
			Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, hata);
		}
	}
}
