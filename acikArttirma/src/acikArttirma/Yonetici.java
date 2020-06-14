package acikArttirma;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import acikArttirma.Data;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Yonetici extends javax.swing.JFrame {
	Connection con = null;
	Statement stm = null;

	public Yonetici() {
		getContentPane().setBackground(SystemColor.activeCaption);
		initComponents();
		pop_tree();// call to populate the JTree
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(18, 23, 199, 235);
		cat_tree = new javax.swing.JTree();
		cat_tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Eþyalar");
		jScrollPane1.setViewportView(cat_tree);
		btnNewButton = new JButton("\u00DCye Ekle");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UyeEkle s = new UyeEkle();
				setVisible(false);
				s.setVisible(true);
			}
		});
		btnNewButton_1 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login s = new Login();
				setVisible(false);
				s.setVisible(true);
			}

		});
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Zeynep\\Desktop\\as.PNG"));
		lblNewLabel_1 = new JLabel("Y\u00D6NET\u0130C\u0130");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addContainerGap().addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(28)
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel)
								.addContainerGap())
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1).addGap(32)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
								GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(38)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
										.addGroup(layout.createSequentialGroup().addComponent(btnNewButton).addGap(37)
												.addComponent(btnNewButton_1)))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)))
						.addContainerGap()));
		jPanel1.setLayout(null);
		jPanel1.add(jScrollPane1);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>

	@SuppressWarnings("CallToThreadDumpStack")
	public final void pop_tree() {
		try {

			try {
				con = Data.getConnection();
				stm = con.createStatement();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			ArrayList list = new ArrayList();
			list.add("Ürünler");
			String sql = "SELECT * from kategori";

			ResultSet rs = stm.executeQuery(sql);

			while (rs.next()) {
				Object value[] = { rs.getString(1), rs.getString(2) };
				list.add(value);
			}
			Object hierarchy[] = list.toArray();
			DefaultMutableTreeNode root = processHierarchy(hierarchy);

			DefaultTreeModel treeModel = new DefaultTreeModel(root);
			cat_tree.setModel(treeModel);
		} catch (Exception e) {
		}

	}

	@SuppressWarnings("CallToThreadDumpStack")
	public DefaultMutableTreeNode processHierarchy(Object[] hierarchy) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
		try {
			int ctrow = 0;
			int i = 0;
			try {

				try {
					con = Data.getConnection();
					stm = con.createStatement();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				String sql = "SELECT KategoriId,Ad from kategori";
				ResultSet rs = stm.executeQuery(sql);

				while (rs.next()) {
					ctrow = rs.getRow();
				}
				String L1Nam[] = new String[ctrow];
				String L1Id[] = new String[ctrow];
				ResultSet rs1 = stm.executeQuery(sql);
				while (rs1.next()) {
					L1Nam[i] = rs1.getString("Ad");
					L1Id[i] = rs1.getString("KategoriId");
					i++;
				}
				DefaultMutableTreeNode child, grandchild;
				for (int childIndex = 0; childIndex < L1Nam.length; childIndex++) {
					child = new DefaultMutableTreeNode(L1Nam[childIndex]);
					node.add(child);// add each created child to root
					String sql2 = "SELECT ad from ürünler where KategoriId= '" + L1Id[childIndex] + "' ";
					ResultSet rs3 = stm.executeQuery(sql2);
					while (rs3.next()) {
						grandchild = new DefaultMutableTreeNode(rs3.getString("ad"));
						child.add(grandchild);// add each grandchild to each child
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
		}

		return (node);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Yonetici().setVisible(true);
			}
		});
	}

	private javax.swing.JTree cat_tree;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
}
