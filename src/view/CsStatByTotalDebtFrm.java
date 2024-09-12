/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.CsStatByTotalDebtDAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import model.CsStatByTotalDebt;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CsStatByTotalDebtFrm extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form CsStatByTotalDebtFrm
     */
    private User user;
    private CsStatByTotalDebtFrm mainFrm;
    private ArrayList<CsStatByTotalDebt> lsCs;

    public CsStatByTotalDebtFrm(User u) {
        user = u;
        initComponents();
        this.setLocationRelativeTo(null);
        mainFrm = this;
        btnBackSelectStatFuncFrm.addActionListener(this);
        CsStatByTotalDebtDAO csStatByTotalDebtDAO = new CsStatByTotalDebtDAO();
        lsCs = csStatByTotalDebtDAO.getCsStatByTotalDebt();



        String[] columnNames = {
            "Stt", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Điện thoại", "Tổng số hóa đơn chưa thanh toán", "Tổng số tiền chưa thanh toán (VND)"
        };
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //unable to edit cells
                return false;
            }
        };

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        dtm.setColumnIdentifiers(columnNames);

        for (int i = 0; i < lsCs.size(); i++) {
            CsStatByTotalDebt cs = lsCs.get(i);
            Object[] rowData = {
                (i + 1) + "",
                String.format("%06d", cs.getID()),
                cs.getFullname(), cs.getAddress(),
                cs.getPhoneNum(),
                numberFormat.format(cs.getNumDebtBill()),
                numberFormat.format(cs.getTotalDebt())
            };
            dtm.addRow(rowData);
        }

//        String[][] data = new String[lsCs.size()][8];
//        for (int i = 0; i < lsCs.size(); i ++) {
//            data[i][0] = (i+1) +"";
//            data[i][1] = String.format("%06d", lsCs.get(i).getID());
//            data[i][2] = lsCs.get(i).getFullname();
//            data[i][3] = lsCs.get(i).getAddress();
//            data[i][4] = lsCs.get(i).getPhoneNum();
//            data[i][5] = lsCs.get(i).getNumBillDebt()+"";
//            data[i][6] = lsCs.get(i).getTotalDebt()+"";
//        }
//        DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
        tblListCsStat.setModel(dtm);

//        TableColumnModel columnModel = tblListCsStat.getColumnModel();
//        columnModel.getColumn(5).setPreferredWidth(200); // "Tổng số hóa đơn chưa thanh toán"
//        columnModel.getColumn(6).setPreferredWidth(250);

//        JTableHeader tableHeader = tblListCsStat.getTableHeader();
//        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                JTextArea headerLabel = new JTextArea(value.toString());
//                headerLabel.setWrapStyleWord(true);
//                headerLabel.setLineWrap(true);
//                headerLabel.setOpaque(true);
//                headerLabel.setFont(getFont());
//                headerLabel.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
//                headerLabel.setBackground(UIManager.getColor("TableHeader.background"));
//                headerLabel.setForeground(UIManager.getColor("TableHeader.foreground"));
//                return headerLabel;
//            }
//        });

        tblListCsStat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblListCsStat.getColumnModel().
                        getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblListCsStat.getRowHeight(); // get row 

                // *Checking the row or column is valid or not
                if (row < tblListCsStat.getRowCount() && row >= 0
                        && column < tblListCsStat.getColumnCount() && column >= 0) {
                    (new CsDebtBillFrm(user, lsCs.get(row))).setVisible(true);
                    mainFrm.dispose();
                }
            }
        });

        System.out.println("MOI MOI MOI");
//
//

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        btnBackSelectStatFuncFrm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListCsStat = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(224, 246, 230));
        setSize(new java.awt.Dimension(1200, 900));

        jPanel1.setBackground(new java.awt.Color(224, 246, 230));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 800));

        btnBackSelectStatFuncFrm.setBackground(new java.awt.Color(102, 204, 255));
        btnBackSelectStatFuncFrm.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBackSelectStatFuncFrm.setText("Quay lại");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Thống kê khách hàng theo dư nợ");

        tblListCsStat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblListCsStat);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logoevnngangfull (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(341, 341, 341))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBackSelectStatFuncFrm)
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnBackSelectStatFuncFrm)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CsStatByTotalDebtFrm().setVisible(true);
//            }
//        });
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBackSelectStatFuncFrm) {
            (new SelectStatFuncFrm(user)).setVisible(true);
            this.dispose();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackSelectStatFuncFrm;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblListCsStat;
    // End of variables declaration//GEN-END:variables

}
