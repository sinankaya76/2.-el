package acikArttirma;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnection extends javax.swing.JFrame {
	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "proje"; 
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = ""; 
	private ResultSet res; 

	public DBConnection(String kulad1) {
		initComponents(); 
		TabloDoldur();
	}

	public Statement baglantiAc() throws Exception {
		Class.forName(driver).newInstance(); 
		conn = DriverManager.getConnection(url + dbName, userName, password);
		return conn.createStatement();
	}

	public void baglantiKapat() throws Exception {
		conn.close();
	}

	public void TabloDoldur() {
		try {

			Statement st = baglantiAc();
			ResultSet res = st.executeQuery("SELECT KategoriId,  ad, Aciklama,  Fiyat FROM  �r�nler"); 
																										// kay覺tlar覺
																										// ResultSet'e
																										// al
			MyTableModel model = new MyTableModel(res); 
			jTable1.setModel(model); 
			baglantiKapat(); 

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglanti Basarisiz", "MySQL Baglantisi",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void KayitEkle(String KategoriId, String ad, String Aciklama, String Fiyat) {
		try {

			Statement st = baglantiAc();
			String sql = "INSERT INTO proje.�r�nler (KategoriId,Ad, Aciklama, Fiyat) VALUES (" + "'" + KategoriId
					+ "', " + "'" + ad + "', " + "" + Aciklama + ", " + "" + Fiyat + ")";

			

			st.executeUpdate(sql);

			baglantiKapat(); 

			TabloDoldur(); 

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Kayit Eklenemedi", "MySQL Baglantisi", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void KayitSil(String ID) {
		try {
			Statement st = baglantiAc();
			st.executeUpdate("Delete FROM  �r�nler where 鈜�nId=" + ID);
			baglantiKapat(); 
			TabloDoldur(); 

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Kayit Silinemedi", "MySQL Baglantisi", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void KayitGuncelle(String ID, String ad, String soyad, String yas) {
		try {

			Statement st = baglantiAc();
			String sql = "UPDATE vt.kisiler SET " + "Ad='" + ad + "', " + "Soyad='" + soyad + "', " + "yas=" + yas
					+ " WHERE ID=" + ID;
			System.out.println(sql);

			

			st.executeUpdate(sql); 

			baglantiKapat(); 

			TabloDoldur(); 

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Kayit Guncellenemedi", "MySQL Baglantisi",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		silButton = new javax.swing.JButton();
		guncelleButton = new javax.swing.JButton();
		AdTextField = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		soyadTextField = new javax.swing.JTextField();
		yasTextField = new javax.swing.JTextField();

		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		ekleButton = new javax.swing.JButton();
		yeniButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTable1MousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		silButton.setText("Geri D霵");
		silButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				silButtonActionPerformed(evt);
			}
		});

		guncelleButton.setText("Guncelle");
		guncelleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				guncelleButtonActionPerformed(evt);
			}
		});

		jLabel1.setText("Ad:");

		jLabel2.setText("Soyad:");

		jLabel3.setText("Yas:");

		jLabel4.setText("Cinsiyet:");

		ekleButton.setText("Ekle");
		ekleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ekleButtonActionPerformed(evt);
			}
		});

		yeniButton.setText("Yeni");
		yeniButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				yeniButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jLabel4).addComponent(jLabel1).addComponent(jLabel2)
										.addComponent(jLabel3))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

										.addComponent(soyadTextField).addComponent(yasTextField)
										.addComponent(AdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 146,
												Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(silButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(ekleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 104,
												Short.MAX_VALUE)
										.addComponent(guncelleButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(yeniButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addContainerGap(15, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
						.addComponent(AdTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(ekleButton))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(soyadTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel2).addComponent(silButton))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(yasTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel3).addComponent(guncelleButton))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15,
										Short.MAX_VALUE)
								.addComponent(jLabel4))
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cinsiyetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(yeniButton))))
				.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void guncelleButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_guncelleButtonActionPerformed

		String ID = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
		String ad = AdTextField.getText();
		String soyad = soyadTextField.getText();
		String yas = yasTextField.getText();

		KayitGuncelle(ID, ad, soyad, yas);
		// TODO add your handling code here:
	}// GEN-LAST:event_guncelleButtonActionPerformed

	private void ekleButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ekleButtonActionPerformed

		String ad = AdTextField.getText();
		String soyad = soyadTextField.getText();
		String yas = yasTextField.getText();
		String cinsiyet = null;

		KayitEkle(ad, soyad, yas, cinsiyet);

	}// GEN-LAST:event_ekleButtonActionPerformed

	private void silButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_silButtonActionPerformed

		Login s = new Login();
		setVisible(false);
		s.setVisible(true);
		String ID = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
		KayitSil(ID);

	}// GEN-LAST:event_silButtonActionPerformed

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MousePressed
		// TODO add your handling code here:
		// Tabloda 羹zerine t覺klan覺lan sat覺r覺n bilgilerini ilgili form elemanlar覺na
		// yazar.
		AdTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
		soyadTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
		yasTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
		String cinsiyet = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();

	}// GEN-LAST:event_jTable1MousePressed

	private void yeniButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_yeniButtonActionPerformed
		AdTextField.setText("");
		soyadTextField.setText("");
		yasTextField.setText("");
		cinsiyetComboBox.setSelectedIndex(0);
	}// GEN-LAST:event_yeniButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new DBConnection("").setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTextField AdTextField;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox cinsiyetComboBox;
	private javax.swing.JButton ekleButton;
	private javax.swing.JButton guncelleButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JButton silButton;
	private javax.swing.JTextField soyadTextField;
	private javax.swing.JTextField yasTextField;
	private javax.swing.JButton yeniButton;
	// End of variables declaration//GEN-END:variables
}
