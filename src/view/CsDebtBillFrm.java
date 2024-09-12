/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.BillDAO;
import controller.CsStatByTotalDebtDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.CsStatByTotalDebt;
import model.ElecService;
import model.Household;
import model.User;

/**
 *
 * @author phuongdt
 */
public class CsDebtBillFrm extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form CsDebtBillFrm
     */
    
    private User user;
    private CsStatByTotalDebt customer;
    private CsDebtBillFrm mainFrm;
    public CsDebtBillFrm(User u, CsStatByTotalDebt cs) {
        initComponents();
        user = u;
        customer = cs;
        mainFrm = this;
        this.setLocationRelativeTo(null);
        btnBackCsStatByTotalDebtFrm.addActionListener(this);
        
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
        lblCsID.setText(String.format("%06d", cs.getID()));
        lblCsAddress.setText(cs.getAddress()+"");
        lblCsCityID.setText(cs.getCitizenIDCardNum()+"");
        lblCsFullname.setText(cs.getFullname()+"");
        lblCsDob.setText(sdfOutput.format( (Date) cs.getDob()));
        lblCsTel.setText(cs.getPhoneNum());
        lblCsTaxNum.setText(cs.getTaxIdenNum());
        
        BillDAO billDAO = new BillDAO();
        ArrayList<Bill> result = billDAO.getDebtBillByCs(cs);
        DefaultTableModel dtm = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
        };
        
        String[] columnNames = {
                "Số thứ tự", "Mã hóa đơn", "Địa chỉ hộ dùng điện", "Loại dịch vụ điện", "Điện năng tiêu thụ (kWh)", "Thời gian chốt sổ", "Thành tiền (VND)"
        };
        
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        dtm.setColumnIdentifiers(columnNames);
        int sum = 0;
        for (int i = 0; i < result.size(); i++) {
            Bill b = result.get(i);
            int idx = b.getListIEMOfBill().get(1).getIdxElecMeter().getIdx() - b.getListIEMOfBill().get(0).getIdxElecMeter().getIdx();
            sum += b.getAmount();
            Object[] rowData = {
                (i + 1)+"",
                String.format("%06d", b.getID()),
                b.getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress(), 
                b.getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName(),
                numberFormat.format(idx),
                sdfOutput.format( (Date) b.getClosingDate()),
                numberFormat.format(b.getAmount())
            };
            dtm.addRow(rowData);
        }
        tblListBillDebtCs.setModel(dtm);
        
        tblListBillDebtCs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblListBillDebtCs.getColumnModel().
                        getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY()/tblListBillDebtCs.getRowHeight(); // get row 

                // *Checking the row or column is valid or not
                if (row < tblListBillDebtCs.getRowCount() && row >= 0 && 
                            column < tblListBillDebtCs.getColumnCount() && column >= 0) {
                    (new DetailCsDebtBill(user,customer,result.get(row))).setVisible(true);
                    mainFrm.dispose();
                }
            }
        });
        
        lblTotalDebtBeforeTax.setText(numberFormat.format( sum));
        lblTax.setText(numberFormat.format((int) Math.round(sum*0.1)));
        lblTotalDebt.setText(numberFormat.format((int) Math.round(sum*1.1)));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCsID = new javax.swing.JLabel();
        lblCsFullname = new javax.swing.JLabel();
        lblCsDob = new javax.swing.JLabel();
        lblCsAddress = new javax.swing.JLabel();
        lblCsTel = new javax.swing.JLabel();
        lblCsTaxNum = new javax.swing.JLabel();
        lblCsCityID = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTax = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotalDebt = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnBackCsStatByTotalDebtFrm = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListBillDebtCs = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalDebtBeforeTax = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1200, 900));

        jPanel1.setBackground(new java.awt.Color(224, 246, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã khách hàng:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên khách hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Điện thoại:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mã số thuế:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Căn cước công dân:");

        lblCsID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsID.setText("jLabel8");

        lblCsFullname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsFullname.setText("jLabel9");

        lblCsDob.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsDob.setText("jLabel10");

        lblCsAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsAddress.setText("jLabel11");

        lblCsTel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsTel.setText("jLabel12");

        lblCsTaxNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsTaxNum.setText("jLabel13");

        lblCsCityID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCsCityID.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Thuế GTGT(10%):");

        lblTax.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTax.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Tổng tiền nợ (VND):");

        lblTotalDebt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalDebt.setForeground(new java.awt.Color(255, 51, 51));
        lblTotalDebt.setText("jLabel18");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel19.setText("Thông tin các hóa đơn còn nợ của khách hàng");

        btnBackCsStatByTotalDebtFrm.setBackground(new java.awt.Color(102, 204, 255));
        btnBackCsStatByTotalDebtFrm.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBackCsStatByTotalDebtFrm.setText("Quay lại");
        btnBackCsStatByTotalDebtFrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackCsStatByTotalDebtFrmActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblListBillDebtCs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblListBillDebtCs);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logoevnngangfull (2).png"))); // NOI18N
        jLabel9.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tổng tiền nợ trước thuế:");

        lblTotalDebtBeforeTax.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalDebtBeforeTax.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblCsTel, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                                .addComponent(lblCsTaxNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCsCityID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblCsAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                                    .addComponent(lblCsDob, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCsFullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCsID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBackCsStatByTotalDebtFrm))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(227, 227, 227)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalDebt, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(lblTax, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(lblTotalDebtBeforeTax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(235, 235, 235))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblCsID))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblCsFullname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblCsDob)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnBackCsStatByTotalDebtFrm)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCsAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblCsTel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCsTaxNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblCsCityID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTotalDebtBeforeTax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblTax))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDebt)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17))
                .addGap(95, 95, 95))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackCsStatByTotalDebtFrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackCsStatByTotalDebtFrmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackCsStatByTotalDebtFrmActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CsDebtBillFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CsDebtBillFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CsDebtBillFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CsDebtBillFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CsDebtBillFrm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackCsStatByTotalDebtFrm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCsAddress;
    private javax.swing.JLabel lblCsCityID;
    private javax.swing.JLabel lblCsDob;
    private javax.swing.JLabel lblCsFullname;
    private javax.swing.JLabel lblCsID;
    private javax.swing.JLabel lblCsTaxNum;
    private javax.swing.JLabel lblCsTel;
    private javax.swing.JLabel lblTax;
    private javax.swing.JLabel lblTotalDebt;
    private javax.swing.JLabel lblTotalDebtBeforeTax;
    private javax.swing.JTable tblListBillDebtCs;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBackCsStatByTotalDebtFrm) {
            (new CsStatByTotalDebtFrm(user)).setVisible(true);
            this.dispose();
        }
    }
}
