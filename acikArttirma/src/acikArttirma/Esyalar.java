package acikArttirma;
 
import java.sql.*;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import acikArttirma.Data;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Esyalar extends javax.swing.JFrame {
 
    Connection con = null;
    Statement stm = null;
 
    public Esyalar() {
    	getContentPane().setBackground(SystemColor.activeCaption);
        initComponents();
        pop_tree(); 
    }
   
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(SystemColor.inactiveCaption);
        jScrollPane1 = new javax.swing.JScrollPane();
        cat_tree = new javax.swing.JTree();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Eþyalar");
        jScrollPane1.setViewportView(cat_tree);
        JButton btnNewButton = new JButton("Geri D\u00F6n");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Satici s =new Satici("");
 		        setVisible(false);
 		       s.setVisible(true);
        	}
        });
        btnNewButton.setBackground(SystemColor.textHighlight);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
 
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnNewButton)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(23)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNewButton)
        			.addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
        			.addContainerGap())
        );
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
                Object value[] = {rs.getString(1), rs.getString(2)};
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
                    node.add(child);//add each created child to root
                    String sql2 = "SELECT ad from ürünler where KategoriId= '" + L1Id[childIndex] + "' ";
                    ResultSet rs3 = stm.executeQuery(sql2);
                    while (rs3.next()) {
                        grandchild = new DefaultMutableTreeNode(rs3.getString("ad"));
                        child.add(grandchild);//add each grandchild to each child
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
                new Esyalar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JTree cat_tree;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
}




	

