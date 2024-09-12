/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.CsStatByTotalDebt;
import model.Customer;
import model.PerLevelElecService;
import model.User;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author phuongdt
 */
public class DetailCsDebtBill extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form DetailCsDebtBill
     */
    private User user;
    private CsStatByTotalDebt csByTotalDebt;
    private Bill bill;

    public DetailCsDebtBill(User u, CsStatByTotalDebt cs, Bill b) {
        user = u;
        csByTotalDebt = cs;
        bill = b;
        initComponents();
        btnClose.addActionListener(this);
        this.setLocationRelativeTo(null);
        long amount = bill.getAmount();
        long taxAmount = Math.round(bill.getAmount() * 0.1);
        long paymentAmount = Math.round(bill.getAmount() * 1.1);

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        // tạo bảng 1
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
        String[][] data = new String[1][5];
        data[0][0] = String.format("%06d", bill.getID());
        data[0][1] = sdfOutput.format(bill.getClosingDate());
        int oldIdx = bill.getListIEMOfBill().get(0).getIdxElecMeter().getIdx();
        int newIdx = bill.getListIEMOfBill().get(1).getIdxElecMeter().getIdx();
        int useIdx = newIdx - oldIdx;
        data[0][2] = numberFormat.format(oldIdx);
        data[0][3] = numberFormat.format(newIdx);
        data[0][4] = numberFormat.format(useIdx);

        String[] ColumnName = {
            "Mã hóa đơn", "Thời gian chốt sổ", "Chỉ số cũ", "Chỉ số mới", "Điện năng tiêu thụ (kWh)"
        };
        DefaultTableModel dtm = new DefaultTableModel(data, ColumnName);
        tblDetailCsDebtBill.setModel(dtm);

        DefaultTableModel model2 = new DefaultTableModel(5, 1);
        JTable tblTotalPayment = new JTable(model2) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new TableCellRenderer() {

                    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        if (row == 0) {
                            int beginIdx = 0;
                            int endIdx = 0;
                            int useIdx = bill.getListIEMOfBill().get(1).getIdxElecMeter().getIdx() - bill.getListIEMOfBill().get(0).getIdxElecMeter().getIdx();
                            int sum = 0;
                            int size = bill.getPlob().getListLevel().size();

                            Object[][] data2 = new Object[size + 1][5];

                            data2[0][0] = "<html><body style='width: 50px'>Bậc</body></html>";
                            data2[0][1] = "<html><body style='width: 150px'>Đơn giá từng bậc (VND/kWh)</body></html>";
                            data2[0][2] = "<html><body style='width: 150px'>Khoảng điện từng bậc</body></html>";
                            data2[0][3] = "<html><body style='width: 200px'>Lượng điện sử dụng từng bậc (kWh)</body></html>";
                            data2[0][4] = "<html><body style='width: 150px'>Thành tiền (VND)</body></html>";

                            for (int i = 0; i < size; i++) {
                                data2[i + 1][0] = "Bậc " + (i + 1);
                                PerLevelElecService x = bill.getPlob().getListLevel().get(i);
                                data2[i + 1][1] = numberFormat.format(x.getPriceLevel()) + "";
                                if (i == size - 1) {
                                    sum += useIdx * x.getPriceLevel();
                                    data2[i + 1][2] = "Trên " + numberFormat.format(endIdx) + "";
                                    data2[i + 1][3] = numberFormat.format(useIdx);
                                    data2[i + 1][4] = numberFormat.format(useIdx * x.getPriceLevel()) + "";
                                    useIdx = 0;
                                } else if (useIdx <= x.getMaxIdxLevel()) {
                                    sum += useIdx * x.getPriceLevel();
                                    endIdx += x.getMaxIdxLevel();
                                    data2[i + 1][2] = "Từ " + (beginIdx) + " đến " + numberFormat.format(endIdx);
                                    data2[i + 1][3] = numberFormat.format(useIdx);
                                    data2[i + 1][4] = numberFormat.format(useIdx * x.getPriceLevel()) + "";
                                    beginIdx += x.getMaxIdxLevel() + 1;
                                    useIdx = 0;
                                } else if (i == 0) {
                                    sum += x.getMaxIdxLevel() * x.getPriceLevel();
                                    useIdx -= x.getMaxIdxLevel();
                                    endIdx = x.getMaxIdxLevel();
                                    data2[i + 1][2] = "Từ 0" + " đến " + numberFormat.format(endIdx);
                                    data2[i + 1][3] = numberFormat.format(x.getMaxIdxLevel()) + "";
                                    data2[i + 1][4] = numberFormat.format(x.getMaxIdxLevel() * x.getPriceLevel()) + "";
                                    beginIdx += x.getMaxIdxLevel() + 1;
                                } else {
                                    sum += x.getMaxIdxLevel() * x.getPriceLevel();
                                    useIdx -= x.getMaxIdxLevel();
                                    endIdx += x.getMaxIdxLevel();
                                    data2[i + 1][2] = "Từ " + (beginIdx) + " đến " + numberFormat.format(endIdx);
                                    data2[i + 1][3] = numberFormat.format(x.getMaxIdxLevel()) + "";
                                    data2[i + 1][4] = numberFormat.format(x.getMaxIdxLevel() * x.getPriceLevel()) + "";
                                    beginIdx += x.getMaxIdxLevel() + 1;
                                }
                            }
                            String[] columnNames = {
                                "Bậc", "Đơn giá từng bậc (VND/kWh)", "Khoảng điện từng bậc", "Lượng điện sử dụng từng bậc (kWh)", "Thành tiền (VND)"
                            };
                            DefaultTableModel dtm2 = new DefaultTableModel(data2, columnNames);
                            JTable tblStat = new JTable(dtm2);
                            tblStat.getTableHeader().setVisible(false);
                            tblStat.setRowHeight(20);
                            tblStat.getColumnModel().getColumn(0).setPreferredWidth(50);
                            tblStat.getColumnModel().getColumn(1).setPreferredWidth(150);
                            tblStat.getColumnModel().getColumn(2).setPreferredWidth(150);
                            tblStat.getColumnModel().getColumn(3).setPreferredWidth(250);
                            tblStat.getColumnModel().getColumn(4).setPreferredWidth(150);
                            return tblStat;
                        } else if (row == 1) {
                            NumberFormat numberFormat2 = NumberFormat.getInstance(new Locale("vi", "VN"));
                            // Add nested table with 1 row and 2 columns
                            String[][] nestedData1
                                    = {{"Tổng điện năng tiêu thụ (kWh)",
                                        numberFormat2.format(useIdx)}};
                            String[] columnName = {"cột 1", "Cột 2"};
                            JTable nestedTable1 = new JTable(new DefaultTableModel(nestedData1, columnName));
//                                nestedTable1.getTableHeader().setVisible(false);
                            nestedTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
                            nestedTable1.setRowHeight(20);
                            nestedTable1.setFont(new Font("Arial", Font.BOLD, 14));
                            return nestedTable1;
                        } else if (row == 2) {
                            // Add nested table with 1 row and 2 columns
                            NumberFormat numberFormat2 = NumberFormat.getInstance(new Locale("vi", "VN"));
                            String[][] nestedData2
                                    = {{"Tổng tiền điện chưa thuế (VND)", numberFormat2.format(bill.getAmount())},
                                    {"Thuế GTGT (10%)", numberFormat2.format(Math.round(bill.getAmount() * 0.1))}};
                            String[] columnName = {"cột 1", "Cột 2"};
                            JTable nestedTable2 = new JTable(new DefaultTableModel(nestedData2, columnName));
                            nestedTable2.setFont(new Font("Arial", Font.BOLD, 14));
//                                nestedTable1.getTableHeader().setVisible(false);
//                                nestedTable2.setForeground(Color.RED);
                            nestedTable2.setRowHeight(20);
                            nestedTable2.getColumnModel().getColumn(0).setPreferredWidth(580);
//                                nestedTable2.setRowHeight(20);
                            return nestedTable2;
                        } else if (row == 3) {
                            // Add nested table with 1 row and 2 columns
                            NumberFormat numberFormat2 = NumberFormat.getInstance(new Locale("vi", "VN"));
                            String[][] nestedData2
                                    = {{"Tổng cộng tiền thanh toán (VND)", numberFormat2.format(Math.round(bill.getAmount() * 1.1))}};
                            String[] columnName = {"cột 1", "Cột 2"};
                            JTable nestedTable2 = new JTable(new DefaultTableModel(nestedData2, columnName));
                            nestedTable2.setFont(new Font("Arial", Font.BOLD, 14));
//                                nestedTable1.getTableHeader().setVisible(false);
                            nestedTable2.setForeground(Color.RED);
                            nestedTable2.setRowHeight(20);
                            nestedTable2.getColumnModel().getColumn(0).setPreferredWidth(580);
//                                nestedTable2.setRowHeight(20);
                            return nestedTable2;
                        } else if (row == 4) {
                            // Add nested table with 1 row and 2 columns
                            String[][] nestedData2
//                                    = {{"Bằng chữ: " + NumToText(234500000) + "đồng"}};
                                    = {{"Bằng chữ: " + NumToText(Math.round(bill.getAmount() * 1.1)) + " đồng"}};
                            String[] columnName = {"cột 1"};
                            JTable nestedTable2 = new JTable(new DefaultTableModel(nestedData2, columnName));
                            nestedTable2.setFont(new Font("Arial", Font.BOLD, 14));
                            nestedTable2.setRowHeight(20);
                            nestedTable2.getColumnModel().getColumn(0).setPreferredWidth(580);
                            return nestedTable2;
                        }
                        return new JLabel(value.toString());
                    }
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblTotalPayment.setRowHeight(0, 20 * (bill.getPlob().getListLevel().size() + 1) + 1);
        tblTotalPayment.setRowHeight(1, 20 + 1);
        tblTotalPayment.setRowHeight(2, 20 * 2 + 1);
        tblTotalPayment.setRowHeight(3, 20 + 1);
        tblTotalPayment.setRowHeight(4, 20 + 1);

        tblTotalPayment.setShowGrid(false);
        tblTotalPayment.getTableHeader().setUI(null); // Remove table header

        jScrollPanew.setSize(1000, 20 * (bill.getPlob().getListLevel().size() + 1) + 20 * 4 + 6);
        jScrollPanew.setViewportView(tblTotalPayment);

        int day = (int) bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate().getDate() - 1;
        int month = (int) bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate().getMonth();
        int year = (int) bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate().getYear();
        jLabel2.setText("Từ ngày "
                + sdfOutput.format(bill.getListIEMOfBill().get(0).getIdxElecMeter().getReadDate())
                + " đến ngày "
                + sdfOutput.format(new Date(year, month, day))
        );
        jLabel11.setText(String.format("%06d", cs.getID()));
        jLabel12.setText(cs.getFullname() + "");
        jLabel13.setText(sdfOutput.format((Date) cs.getDob()));
        jLabel14.setText(cs.getAddress() + "");
        jLabel15.setText(cs.getTaxIdenNum() + "");
        jLabel16.setText(cs.getPhoneNum());
        jLabel17.setText(cs.getCitizenIDCardNum());

        jLabel21.setText("NV" + String.format("%04d", bill.getUser().getID()));
        jLabel22.setText(bill.getUser().getFullname());
        jLabel25.setText(bill.getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        jLabel27.setText(bill.getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        jLabel46.setText(bill.isIsPayment() == true ? "Đã thanh toán" : "Chưa thanh toán");

        jLabel38.setText("Kỳ hóa đơn: Tháng "
                + (bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate().getMonth() + 1)
                + " ( "
                + dateDiff(bill.getListIEMOfBill().get(0).getIdxElecMeter().getReadDate(), bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate())
                + " ngày từ "
                + sdfOutput.format(bill.getListIEMOfBill().get(0).getIdxElecMeter().getReadDate())
                + " đến ngày "
                + sdfOutput.format(endDated(bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate()))
                + ")"
        );

        jLabel42.setText(numberFormat.format(Math.round(bill.getAmount() * 1.1)) + " VND");
        jLabel44.setText(sdfOutput.format(expireDated(bill.getListIEMOfBill().get(1).getIdxElecMeter().getReadDate())));

//        jLabel23.setText(NumToText(paymentAmount) + "đồng");
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailCsDebtBill = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPanew = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1424, 1000));

        jPanel1.setMinimumSize(new java.awt.Dimension(20, 20));
        jPanel1.setPreferredSize(new java.awt.Dimension(1404, 911));

        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(30, 30));

        jPanel2.setBackground(new java.awt.Color(224, 246, 230));
        jPanel2.setPreferredSize(new java.awt.Dimension(1404, 910));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Hóa đơn tiền điện");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thông tin khách hàng:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã khách hàng:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên khách hàng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Ngày sinh:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Địa chỉ:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mã số thuế:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Điện thoại:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("CCCD:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("jLabel12");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("jLabel12");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("jLabel15");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("jLabel17");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Thông tin nhân viên tạo hóa đơn:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Mã nhân viên:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Tên nhân viên:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("jLabel21");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("jLabel22");

        tblDetailCsDebtBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Thời gian chốt sổ", "Chỉ số cũ", "Chỉ số mới", "Điện năng tiêu thụ (kWh)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetailCsDebtBill);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Địa chỉ hộ dùng điện:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("jLabel25");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Loại dịch vụ điện:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("jLabel27");

        btnClose.setBackground(new java.awt.Color(255, 153, 0));
        btnClose.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setText("Tình hình sử dụng điện của khách hàng:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("jLabel38");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel40.setText("Tổng số tiền thanh toán:");

        jPanel3.setBackground(new java.awt.Color(39, 92, 162));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Số tiền thanh toán");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("jLabel42");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(39, 92, 162));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Hạn thanh toán");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("jLabel44");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(39, 92, 162));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Trạng thái thanh toán");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("jLabel46");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logoevnngangfull (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addGap(396, 396, 396))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel19))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel26)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(133, 133, 133)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(374, 374, 374))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPanew, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel18))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPanew, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))))
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1404, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCloseActionPerformed

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
//            java.util.logging.Logger.getLogger(DetailCsDebtBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DetailCsDebtBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DetailCsDebtBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DetailCsDebtBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                User u = new User();
//                CsStatByTotalDebt cs = new CsStatByTotalDebt();
//                BillDAO billDAO = new BillDAO();
//                cs.setID(1);
//                cs.setAddress("28 Trần Hưng Đạo, Phan Chu Trinh, Hoàn Kiếm, Hà Nội");
//                Bill b = billDAO.getDebtBillByCs(cs).get(0);
//                new DetailCsDebtBill(u, cs, b).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPanew;
    private javax.swing.JTable tblDetailCsDebtBill;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            (new CsDebtBillFrm(user, csByTotalDebt)).setVisible(true);
            this.dispose();
        }
    }

    private long dateDiff(Date d1, Date d2) {
        return (d2.getTime() - d1.getTime()) / 24 / 60 / 60 / 1000;
    }

    private Date expireDated(Date d1) {
        return new Date(d1.getTime() + 14 * 24 * 60 * 60 * 1000);
    }

    private Date endDated(Date d1) {
        return new Date(d1.getTime() - 24 * 60 * 60 * 1000);
    }

    private static String NumToText(long a) {
        String[] hangChuc = {"lẻ", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
        String[] hangTram = {"không trăm", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};
        String[] hangDonVi = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mốt", "tư", "lăm"};

        String ans = "";

        String b = a + "";
        ArrayList<String> ls = new ArrayList<>();
        while (b.length() > 0) {
            if (b.length() >= 3) {
                ls.add(b.substring(b.length() - 3, b.length()));
                b = b.substring(0, b.length() - 3);
            } else {
                ls.add(b);
                b = "";
            }
        }

        for (int i = 0; i < ls.size(); i++) {
            String s = ls.get(i);
            if (s.equals("000")) {
                if (!ls.get(i + 1).equals("000")) {
                    switch ((i + 1) % 3) {
                        case 1:
                            ans = "nghìn " + ans;
                            break;
                        case 2:
                            ans = "triệu " + ans;
                            break;
                        case 0:
                            ans = "tỷ " + ans;
                            break;
                    }
                }
            } else if (i < ls.size() - 1) {
                String x = hangTram[s.charAt(0) - '0'] + " " + hangChuc[s.charAt(1) - '0'] + " ";
                if (s.charAt(1) == '0' && s.charAt(2) == '0') {
                    x = hangTram[s.charAt(0) - '0'] + ' ';
                } else {
                    switch (s.charAt(2)) {
                        case '1':
                            if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                x += hangDonVi[1];
                            }
                            x += hangDonVi[10];
                            break;
                        case '4':
                            if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                x += hangDonVi[4];
                            } else {
                                x += hangDonVi[11];
                            }
                            break;
                        case '5':
                            if (s.charAt(1) == '0') {
                                x += hangDonVi[5];
                            } else {
                                x += hangDonVi[12];
                            }
                            break;
                        default:
                            x += hangDonVi[s.charAt(2) - '0'];
                    }
                }

                switch ((i + 1) % 3) {
                    case 1:
                        x = "nghìn " + x;
                        break;
                    case 2:
                        x = "triệu " + x;
                        break;
                    case 0:
                        x = "tỷ " + x;
                        break;
                }
                ans = x + " " + ans;
            } else {
                String x = "";
                if (s.length() == 1) {
                    x += hangDonVi[s.charAt(0) - '0'];
                } else if (s.length() == 2) {
                    x += hangChuc[s.charAt(0) - '0'] + " ";
                    switch (s.charAt(1)) {
                        case '1':
                            if (s.charAt(0) == '1') {
                                x += hangDonVi[1];
                            } else {
                                x += hangDonVi[10];
                            }
                            break;
                        case '4':
                            if (s.charAt(0) == '1') {
                                x += hangDonVi[4];
                            } else {
                                x += hangDonVi[11];
                            }
                            break;
                        case '5':
                            if (s.charAt(1) == '0') {
                                x += hangDonVi[5];
                            } else {
                                x += hangDonVi[12];
                            }
                            break;
                        default:
                            x += hangDonVi[s.charAt(1) - '0'];
                    }
                } else {
                    if (s.charAt(1) == '0' && s.charAt(2) == '0') {
                        x = hangTram[s.charAt(0) - '0'];
                    } else {
                        x = hangTram[s.charAt(0) - '0'] + " " + hangChuc[s.charAt(1) - '0'] + " ";
                        switch (s.charAt(2)) {
                            case '1':
                                if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                    x += hangDonVi[1];
                                }
                                x += hangDonVi[10];
                                break;
                            case '4':
                                if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                    x += hangDonVi[4];
                                } else {
                                    x += hangDonVi[11];
                                }
                                break;
                            case '5':
                                if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                    x += hangDonVi[4];
                                } else {
                                    x += hangDonVi[12];
                                }
                                break;
                            default:
                                x += hangDonVi[s.charAt(2) - '0'];

                        }
                    }
                }
                ans = x + " " + ans;
            }

        }

        return (Character.toUpperCase(ans.charAt(0)) + ans.substring(1)).replaceAll("\\s+", " ");
    }

}
