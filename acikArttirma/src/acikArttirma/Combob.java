package acikArttirma;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class Combob extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Combob frame = new Combob();
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
	public Combob() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(176, 67, 111, 20);
		contentPane.add(comboBox);
		final JComboBox departmanBox = new JComboBox(DepartmanDao.listele()
                .toArray());
		DepartmanDomain secilenDepartman = (DepartmanDomain) departmanBox.getSelectedItem();
	}
	public static void initRecord(DepartmanDomain yeniKayit) {

        Connection baglanti = getConnection();
        try {
        Statement sorgu = baglanti.createStatement();
        sorgu.executeUpdate("INSERT INTO departman (departmanAdi) VALUES('"+ yeniKayit.getDepartmanAdi() + "')");
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          JOptionPane.showMessageDialog(null, e);
        }
}
	public static List<DepartmanDomain> listele() {

		   List<DepartmanDomain> liste = new ArrayList<DepartmanDomain>();
		   Connection conn = getConnection();
		   try {
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * FROM epartman");

		   while (rs.next()) {
		         DepartmanDomain siradakiDepartmanDomain = new DepartmanDomain();
		         siradakiDepartmanDomain.setId(rs.getInt("id"));
		         siradakiDepartmanDomain.setDepartmanAdi(rs.getString("departmanAdi"));
		         liste.add(siradakiDepartmanDomain);
		  }

		     stmt.close();
		     conn.close();
		  } catch (SQLException e) {
		     e.printStackTrace();
		  }

		  return liste;
		            }
}
