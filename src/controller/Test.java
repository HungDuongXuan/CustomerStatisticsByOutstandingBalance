//*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controller;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import model.Bill;
//import model.CsStatByTotalDebt;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.temporal.ChronoUnit;
//
///**
// *
// * @author phuongdt
// */
//public class Test{
//    
//
//    
//    public static void main(String[] args) throws SQLException {
//        
////            Connection con = null;
////            String dbUrl = "jdbc:mysql://localhost:3306/testcasecnpm?autoReconnect=true&useSSL=false";
////            String dbClass = "com.mysql.cj.jdbc.Driver";
////
////            try {
////                Class.forName(dbClass);
////                con = DriverManager.getConnection(dbUrl, "root", "123456");
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//       
//        
////        BillDAO billDAO = new BillDAO();
////        CsStatByTotalDebt cs = new CsStatByTotalDebt();
////        cs.setID(2);
////        ArrayList<Bill> lsBill = billDAO.getDebtBillByCs(cs);
////        System.out.println(lsBill.size());
////        for (Bill x : lsBill) {
////            System.out.println(x);
//////            
////        }
////        
//        
////        PreparedStatement ps =  con.prepareStatement("Select * from test");
////        ResultSet rs = ps.executeQuery();
////        while(rs.next()) {
////            System.out.println(rs.getInt("num"));
////            rs.next();
////            rs.next();
////        }
////        
//        
////        CsStatByTotalDebtDAO csStatByTotalDebtDAO = new CsStatByTotalDebtDAO();
////        ArrayList<CsStatByTotalDebt> lsCs = csStatByTotalDebtDAO.getCsStatByTotalDebt();
////
////        for (CsStatByTotalDebt x : lsCs) {
////            System.out.println(x);
////        }
//
//
////        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
////        Date d = new Date(2002-1900, 02,20);
////        System.out.println(sf.format(d));
////        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////        LocalDate ngayMoi = localDate.plusDays(15);
////        System.out.println("Ngày mới sau khi thêm 15 ngày: " + ngayMoi);
//    }
//}

//
//
//import javax.swing.*;
//import javax.swing.table.*;
//import java.awt.*;
//
//public class Test {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Nested Table Example");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Data for the main table
//            Object[][] mainData = {
//                {"Row 1", new Object[][]{{"1.1", "1.2"}, {"1.3", "1.4"}}},
//                {"Row 2", new Object[][]{{"2.1", "2.2"}, {"2.3", "2.4"}}}
//            };
//            String[] mainColumns = {"Main Column 1", "Main Column 2"};
//
//            // Main table model
//            DefaultTableModel mainTableModel = new DefaultTableModel(mainData, mainColumns) {
//                @Override
//                public Class<?> getColumnClass(int columnIndex) {
//                    if (columnIndex == 1) {
//                        return Object[][].class;
//                    }
//                    return super.getColumnClass(columnIndex);
//                }
//            };
//
//            JTable mainTable = new JTable(mainTableModel);
//
//            // Custom cell renderer for nested tables
//            TableCellRenderer nestedTableCellRenderer = new TableCellRenderer() {
//                @Override
//                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                    if (value instanceof Object[][]) {
//                        Object[][] nestedData = (Object[][]) value;
//                        String[] nestedColumns = {"Nested Column 1", "Nested Column 2"};
//                        JTable nestedTable = new JTable(nestedData, nestedColumns);
//                        nestedTable.setPreferredScrollableViewportSize(new Dimension(200, nestedTable.getRowHeight() * nestedData.length));
//                        return new JScrollPane(nestedTable);
//                    }
//                    return new JLabel(value.toString());
//                }
//            };
//
//            mainTable.getColumnModel().getColumn(1).setCellRenderer(nestedTableCellRenderer);
//
//            // Add main table to frame
//            frame.add(new JScrollPane(mainTable));
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
//}
//
//
//
//
//
//import javax.swing.*;
//import javax.swing.table.*;
//import java.awt.*;
//import java.text.NumberFormat;
//import java.util.List;
//
//public class Test {
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Nested Table Example");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            NumberFormat numberFormat = NumberFormat.getInstance();
//
//            // Data for the main table (one column, three rows)
//            DefaultTableModel model = new DefaultTableModel(0, 1);
//            model.addRow(new Object[]{});
//            model.addRow(new Object[]{});
//
//            // Tạo bảng chính
//            JTable mainTable = new JTable(model) {
//                @Override
//                public TableCellRenderer getCellRenderer(int row, int column) {
//                    return new TableCellRenderer() {
//                        @Override
//                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                            if (row == 0) {
//                                Object[][] data = new Object[5][5];
//                                
//                                    data[0][0] = "Bậc ";
//                                    data[0][1] = "Đơn giá từng bậc (VND/kWh)";
//                                    data[0][2] = "Khoảng điện từng bậc";
//                                    data[0][3] = "Lượng điện sử dụng từng bậc (kWh)";
//                                    data[0][4] = "Thành tiền (VND)";
//                                
//                                for (int i = 1; i < 5; i++) {
//                                    data[i][0] = "Bậc " + (i + 1);
//                                    data[i][1] = "1";
//                                    data[i][2] = "1";
//                                    data[i][3] = "1";
//                                    data[i][4] = "1";
//                                }
//
//                                String[] columnNames = {
//                                    "Bậc", "Đơn giá từng bậc (VND/kWh)", "Khoảng điện từng bậc", "Lượng điện sử dụng từng bậc (kWh)", "Thành tiền (VND)"
//                                };
//
//                                DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
//                                JTable tblTotalPayment = new JTable(dtm);
//                                return tblTotalPayment;
//                            } else if (row == 1) {
//                                // Add nested table with 1 row and 2 columns
//                                String[][] nestedData1 = 
//                                {{"Nested 1-1", "Nested 1-2"},
//                                {"Nested 1-3", "Nested 1-4"},
//                                {"Nested 1-5", "Nested 1-6"},};
//                                String [] columnName = {"cột 1", "Cột 2"};
//                                JTable nestedTable1 = new JTable(new DefaultTableModel(nestedData1, columnName));
//                                nestedTable1.getTableHeader().setVisible(false);
//                                return nestedTable1;
//                            } 
//                            return new JLabel(value.toString());
//                        }
//                    };
//                }
//
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//
//            mainTable.setRowHeight(100);
//            mainTable.setShowGrid(false);
//            mainTable.getTableHeader().setUI(null); // Remove table header
//
//            // Add main table to frame
//            frame.add(new JScrollPane(mainTable));
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
//
//    private static Bill getBillData() {
//        // Mock method to get Bill data, implement as needed
//        return new Bill();
//    }
//}
//
//class Bill {
//
//    private LevelUpdate levelUpdate;
//
//    public LevelUpdate getLevelUpdate() {
//        return levelUpdate;
//    }
//
//    // Mock class, implement as needed
//}
//
//class LevelUpdate {
//
//    private List<PerLevelElecService> listLevel;
//
//    public List<PerLevelElecService> getListLevel() {
//        return listLevel;
//    }
//
//    // Mock class, implement as needed
//}
//
//class PerLevelElecService {
//
//    private int maxIdxLevel;
//    private double priceLevel;
//
//    public int getMaxIdxLevel() {
//        return maxIdxLevel;
//    }
//
//    public double getPriceLevel() {
//        return priceLevel;
//    }
//
//    // Mock class, implement as needed
//}
//
//
//
//
//
//
//
//
//import javax.swing.*;
//import javax.swing.table.*;
//import java.awt.*;
//import java.text.NumberFormat;
//import java.util.List;
//
//public class Test {
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Nested Table Example");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            NumberFormat numberFormat = NumberFormat.getInstance();
//
//            // Data for the main table (one column, three rows)
//            DefaultTableModel model = new DefaultTableModel(0, 1);
//            model.addRow(new Object[]{});
//            model.addRow(new Object[]{});
//            model.addRow(new Object[]{});
//            model.addRow(new Object[]{});
//
//            // Tạo bảng chính
//            long h1, h2, h3;
//            JTable mainTable = new JTable(model) {
//                @Override
//                public TableCellRenderer getCellRenderer(int row, int column) {
//                    return new TableCellRenderer() {
//
//                        private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//
//                        @Override
//                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                            if (row == 0) {
//                                Object[][] data = new Object[6][5];
//
//                                data[0][0] = "<html><body style='width: 50px'>Bậc</body></html>";
//                                data[0][1] = "<html><body style='width: 150px'>Đơn giá từng bậc (VND/kWh)</body></html>";
//                                data[0][2] = "<html><body style='width: 150px'>Khoảng điện từng bậc</body></html>";
//                                data[0][3] = "<html><body style='width: 200px'>Lượng điện sử dụng từng bậc (kWh)</body></html>";
//                                data[0][4] = "<html><body style='width: 150px'>Thành tiền (VND)</body></html>";
//
//                                for (int i = 1; i < 6; i++) {
//                                    data[i][0] = "1";
//                                    data[i][1] = "1";
//                                    data[i][2] = "1";
//                                    data[i][3] = "1";
//                                    data[i][4] = "1";
//                                }
//
//                                String[] columnNames = {
//                                    "Bậc", "Đơn giá từng bậc (VND/kWh)", "Khoảng điện từng bậc", "Lượng điện sử dụng từng bậc (kWh)", "Thành tiền (VND)"
//                                };
//                                DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
//                                JTable tblTotalPayment = new JTable(dtm);
//                                tblTotalPayment.getTableHeader().setVisible(false);
//                                tblTotalPayment.setRowHeight(20);
//                                tblTotalPayment.getColumnModel().getColumn(0).setPreferredWidth(50);
//                                tblTotalPayment.getColumnModel().getColumn(1).setPreferredWidth(150);
//                                tblTotalPayment.getColumnModel().getColumn(02).setPreferredWidth(150);
//                                tblTotalPayment.getColumnModel().getColumn(03).setPreferredWidth(250);
//                                tblTotalPayment.getColumnModel().getColumn(04).setPreferredWidth(150);
//                                return tblTotalPayment;
//                            } else if (row == 1) {
//                                // Add nested table with 1 row and 2 columns
//                                String[][] nestedData1
//                                        = {{"Nested 1-1", "Nested 1-2"}};
//                                String[] columnName = {"cột 1", "Cột 2"};
//                                JTable nestedTable1 = new JTable(new DefaultTableModel(nestedData1, columnName));
////                                nestedTable1.getTableHeader().setVisible(false);
//                                nestedTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
//                                nestedTable1.setRowHeight(20);
//                                nestedTable1.setFont(new Font("Arial", Font.BOLD, 14));
//                                return nestedTable1;
//                            } else if (row == 2) {
//                                // Add nested table with 1 row and 2 columns
//                                String[][] nestedData2
//                                        = {{"hay", "Nested 1-2"},
//                                        {"Nested 1-1", "Nested 1-2"}};
//                                String[] columnName = {"cột 1", "Cột 2"};
//                                JTable nestedTable2 = new JTable(new DefaultTableModel(nestedData2, columnName));
//                                nestedTable2.setFont(new Font("Arial", Font.BOLD, 14));
////                                nestedTable1.getTableHeader().setVisible(false);
////                                nestedTable2.setForeground(Color.RED);
//                                nestedTable2.setRowHeight(20);
//                                nestedTable2.getColumnModel().getColumn(0).setPreferredWidth(660);
////                                nestedTable2.setRowHeight(20);
//                                return nestedTable2;
//                            }else if (row == 3) {
//                                // Add nested table with 1 row and 2 columns
//                                String[][] nestedData2
//                                        = {{"hay", "Nested 1-2"}};
//                                String[] columnName = {"cột 1", "Cột 2"};
//                                JTable nestedTable2 = new JTable(new DefaultTableModel(nestedData2, columnName));
//                                nestedTable2.setFont(new Font("Arial", Font.BOLD, 14));
////                                nestedTable1.getTableHeader().setVisible(false);
//                                nestedTable2.setForeground(Color.RED);
//                                nestedTable2.setRowHeight(20);
//                                nestedTable2.getColumnModel().getColumn(0).setPreferredWidth(660);
////                                nestedTable2.setRowHeight(20);
//                                return nestedTable2;
//                            }
//                            return new JLabel(value.toString());
//                        }
//                    };
//                }
//
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//
//           
//            mainTable.setRowHeight(0, 120+1);
//            mainTable.setRowHeight(1, 20 + 1);
//            mainTable.setRowHeight(2, 20 * 2 + 1);
//
//            mainTable.setShowGrid(false);
//            mainTable.getTableHeader().setUI(null); // Remove table header
//
//            JScrollPane scrollPane = new JScrollPane(mainTable);
//            scrollPane.setLocation(50, 50);
//            frame.add(scrollPane);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//            frame.setSize(1000, 900);
//        });
//    }
//}
import java.util.ArrayList;

public class Test {

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
            if (i < ls.size() - 1) {
                String x = hangTram[s.charAt(0) - '0'] + " " + hangChuc[s.charAt(1) - '0'] + " ";
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
                            if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                                x += hangDonVi[4];
                            } else {
                                x += hangDonVi[12];
                            }
                            break;
                        default:
                            x += hangDonVi[s.charAt(1) - '0'];
                    }
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
                ans = x + " " + ans;
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        long number = 2123021013;
        System.out.println(NumToText(number));
    }
}
