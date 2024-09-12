/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Bill;
import model.CsStatByTotalDebt;
import model.PerLevelElecService;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phuongdt
 */
public class BillDAOTest {
    
    public BillDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDebtBillByCs method, of class BillDAO.
     */
    
    @Test
    public void testGetDebtBillByCs1() {
        System.out.println("getDebtBillByCs");
        CsStatByTotalDebt cs = new CsStatByTotalDebt();
        cs.setID(1);
        BillDAO instance = new BillDAO();
        ArrayList<Bill> result = instance.getDebtBillByCs(cs);
        
        assertEquals("Sai",2, result.size());
        assertEquals("Sai",1, result.get(0).getID());
        
//        Bill b = new Bill(0, closingDate, true, 0, user, monthlyLevel, listIEMOfBill);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("sai ngay chot so", sdf.format(new Date(2023-1900, 10-1,16)), sdf.format(result.get(0).getClosingDate()));
        assertEquals("Sai hoa don chua thanh toan", false, result.get(0).isIsPayment());
        assertEquals("sai thành tiền:", 1450000, result.get(0).getAmount());
        // new User(2, "Nhân viên nhập liệu 1", "dataEntry", "dataEntry@123", "Data entry specialist")
        assertEquals("Sai user nhập",2, result.get(0).getUser().getID());
        assertEquals("Sai user nhập","Nhân viên nhập liệu 1", result.get(0).getUser().getFullname());
        assertEquals("Sai user nhập","dataEntry", result.get(0).getUser().getUsername());
        assertEquals("Sai user nhập","dataEntry@123", result.get(0).getUser().getPassword());
        assertEquals("Sai user nhập","Data entry specialist", result.get(0).getUser().getPosition());
        
//        assertEquals("Sai ID bac thang gia dien theo tung thang",1, result.get(0).getMonthlyLevel().getID());
//        assertEquals("Sai ngay ap dung bac thang gia dien", sdf.format(new Date(2023-1900, 9-1,16)), sdf.format(result.get(0).getMonthlyLevel().getStartDate()));
        
        
        assertEquals("Sai id cap nhat bac thang gia dien",1, 
                result.get(0).getLevelUpdate().getID());
        assertEquals("Sai ngay cap nhat bac thang gia dien",sdf.format(new Date(2023-1900, 9-1,15)),
                sdf.format(result.get(0).getLevelUpdate().getUpdateDate()));
//        ArrayList<PerLevelElecService> perLevelElecServicesTest = new ArrayList<>();
//        perLevelElecServicesTest.add(new PerLevelElecService(1,200,1500));
//        perLevelElecServicesTest.add(new PerLevelElecService(2,200,2000));
//        perLevelElecServicesTest.add(new PerLevelElecService(3,200,2500));
        assertEquals("Sai bậc thang giá điện:",3,
                     result.get(0).getLevelUpdate().getListLevel().size()
                );
        assertEquals("Sai id bậc thang giá điện:",1,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",200,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",1500,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",2,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",200,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2000,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",3,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",0,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2500,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getPriceLevel()
                );
        
        assertEquals("Sai listIEmofbill", 2, result.get(0).getListIEMOfBill().size());
        assertEquals("Sai ID IEmofbill", 1, result.get(0).getListIEMOfBill().get(0).getID());
        assertEquals("IEmofbill khong phai cua thang truoc do", true, result.get(0).getListIEMOfBill().get(0).isIsPrevMonthIEM());
        assertEquals("Sai IEM", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 1, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 0, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 9-1, 16)),
                sdf.format(result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getReadDate()));
        assertEquals("Sai HouseholdOfContract", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 1, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 9-1, 16)), 
                sdf.format(result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("Sai household", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 1, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai id household", "Ao Sen 1", result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 1, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện dân dụng", result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
        
        // IEM OF BILL 1
        assertEquals("Sai ID IEmofbill", 2, result.get(0).getListIEMOfBill().get(1).getID());
        assertEquals("IEmofbill khong phai cua thang truoc do", false, result.get(0).getListIEMOfBill().get(1).isIsPrevMonthIEM());
        assertEquals("Sai IEM", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 2, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 700, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 10-1, 16)),
                sdf.format(result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getReadDate()));
        assertEquals("Sai HouseholdOfContract", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 1, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 9-1, 16)), 
                sdf.format(result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("Sai household", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 1, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai id household", "Ao Sen 1", result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 1, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện dân dụng", result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
        
        
        
        // test BILL 2 cua cs 1:
        assertEquals("Sai",false, result.get(1).equals(null));
        assertEquals("Sai",2, result.get(1).getID());
        
//        Bill b = new Bill(0, closingDate, true, 0, user, monthlyLevel, listIEMOfBill);
        
        assertEquals("sai ngay chot so", sdf.format(new Date(2023-1900, 11-1,16)), sdf.format(result.get(1).getClosingDate()));
        assertEquals("Sai hoa don chua thanh toan", false, result.get(1).isIsPayment());
        assertEquals("sai thành tiền:", 1450000, result.get(0).getAmount());
        // new User(2, "Nhân viên nhập liệu 1", "dataEntry", "dataEntry@123", "Data entry specialist")
        assertEquals("Sai user nhập",2, result.get(1).getUser().getID());
        assertEquals("Sai user nhập","Nhân viên nhập liệu 1", result.get(1).getUser().getFullname());
        assertEquals("Sai user nhập","dataEntry", result.get(1).getUser().getUsername());
        assertEquals("Sai user nhập","dataEntry@123", result.get(1).getUser().getPassword());
        assertEquals("Sai user nhập","Data entry specialist", result.get(1).getUser().getPosition());
        
//        assertEquals("Sai ID bac thang gia dien theo tung thang",4, result.get(1).getMonthlyLevel().getID());
//        assertEquals("Sai ngay ap dung bac thang gia dien", sdf.format(new Date(2023-1900, 10-1,16)), sdf.format(result.get(1).getMonthlyLevel().getStartDate()));
//        assertEquals("Sai ngay ket thuc ap dung bac thang gia dien", sdf.format(new Date(2023-1900, 11-1,15)), sdf.format(result.get(1).getMonthlyLevel().getEndDate()));
        
        
        assertEquals("Sai id cap nhat bac thang gia dien",1, 
                result.get(1).getLevelUpdate().getID());
        assertEquals("Sai ngay cap nhat bac thang gia dien",sdf.format(new Date(2023-1900, 9-1,15)),
                sdf.format(result.get(1).getLevelUpdate().getUpdateDate()));
//        ArrayList<PerLevelElecService> perLevelElecServicesTest = new ArrayList<>();
//        perLevelElecServicesTest.add(new PerLevelElecService(1,200,1500));
//        perLevelElecServicesTest.add(new PerLevelElecService(2,200,2000));
//        perLevelElecServicesTest.add(new PerLevelElecService(3,200,2500));
        assertEquals("Sai bậc thang giá điện:",3,
                     result.get(1).getLevelUpdate().getListLevel().size()
                );
        assertEquals("Sai id bậc thang giá điện:",1,
                     result.get(1).getLevelUpdate().getListLevel().get(0).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",200,
                     result.get(1).getLevelUpdate().getListLevel().get(0).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",1500,
                     result.get(1).getLevelUpdate().getListLevel().get(0).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",2,
                     result.get(1).getLevelUpdate().getListLevel().get(1).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",200,
                     result.get(1).getLevelUpdate().getListLevel().get(1).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2000,
                     result.get(1).getLevelUpdate().getListLevel().get(1).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",3,
                     result.get(1).getLevelUpdate().getListLevel().get(2).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",0,
                     result.get(1).getLevelUpdate().getListLevel().get(2).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2500,
                     result.get(1).getLevelUpdate().getListLevel().get(2).getPriceLevel()
                );
        
        assertEquals("Sai listIEmofbill", 2, result.get(1).getListIEMOfBill().size());
        assertEquals("Sai ID IEmofbill", 3, result.get(1).getListIEMOfBill().get(0).getID());
        assertEquals("IEmofbill khong phai cua thang truoc do", true, result.get(1).getListIEMOfBill().get(0).isIsPrevMonthIEM());
        assertEquals("Sai IEM", false, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 2, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 700, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 10-1, 16)),
                sdf.format(result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getReadDate()));
        assertEquals("Sai HouseholdOfContract", false, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 1, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 9-1, 16)), 
                sdf.format(result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("Sai household", false, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 1, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai id household", "Ao Sen 1", result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 1, result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện dân dụng", result.get(1).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
        // iemofbill 2 of bill 2
        assertEquals("Sai ID IEmofbill", 4, result.get(1).getListIEMOfBill().get(1).getID());
        assertEquals("IEmofbill khong phai cua thang sau do", false, result.get(1).getListIEMOfBill().get(1).isIsPrevMonthIEM());
        assertEquals("Sai IEM", false, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 5, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 1400, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 11-1, 16)),
                sdf.format(result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getReadDate()));
        assertEquals("Sai HouseholdOfContract", false, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 1, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 9-1, 16)), 
                sdf.format(result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("Sai household", false, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 1, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai id household", "Ao Sen 1", result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 1, result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện dân dụng", result.get(1).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
    }
    
    @Test
    public void testGetDebtBillByCs2() {
        System.out.println("getDebtBillByCs");
        CsStatByTotalDebt cs = new CsStatByTotalDebt();
        cs.setID(2);
        BillDAO instance = new BillDAO();
        ArrayList<Bill> expResult = new ArrayList<>();
        ArrayList<Bill> result = instance.getDebtBillByCs(cs);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        
        
        assertEquals("Sai",1, result.size());
        assertEquals("Sai",false, result.get(0).equals(null));
        assertEquals("Sai",3, result.get(0).getID());
        
//        Bill b = new Bill(0, closingDate, true, 0, user, monthlyLevel, listIEMOfBill);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("sai ngay chot so", sdf.format(new Date(2023-1900, 11-1,16)), sdf.format(result.get(0).getClosingDate()));
        assertEquals("Sai hoa don chua thanh toan", false, result.get(0).isIsPayment());
        assertEquals("sai thành tiền:", 940000, result.get(0).getAmount());
        // new User(2, "Nhân viên nhập liệu 1", "dataEntry", "dataEntry@123", "Data entry specialist")
        assertEquals("Sai user nhập",2, result.get(0).getUser().getID());
        assertEquals("Sai user nhập","Nhân viên nhập liệu 1", result.get(0).getUser().getFullname());
        assertEquals("Sai user nhập","dataEntry", result.get(0).getUser().getUsername());
        assertEquals("Sai user nhập","dataEntry@123", result.get(0).getUser().getPassword());
        assertEquals("Sai user nhập","Data entry specialist", result.get(0).getUser().getPosition());
        
//        assertEquals("Sai ID bac thang gia dien theo tung thang",6, result.get(0).getMonthlyLevel().getID());
//        assertEquals("Sai ngay ap dung bac thang gia dien", sdf.format(new Date(2023-1900, 10-1,16)), sdf.format(result.get(0).getMonthlyLevel().getStartDate()));
//        assertEquals("Sai ngay ket thuc ap dung bac thang gia dien", sdf.format(new Date(2023-1900, 11-1,15)), sdf.format(result.get(0).getMonthlyLevel().getEndDate()));
        
        
        assertEquals("Sai id cap nhat bac thang gia dien",4, 
                result.get(0).getLevelUpdate().getID());
        assertEquals("Sai ngay cap nhat bac thang gia dien",sdf.format(new Date(2023-1900, 10-1,15)),
                sdf.format(result.get(0).getLevelUpdate().getUpdateDate()));
//        ArrayList<PerLevelElecService> perLevelElecServicesTest = new ArrayList<>();
//        perLevelElecServicesTest.add(new PerLevelElecService(1,200,1500));
//        perLevelElecServicesTest.add(new PerLevelElecService(2,200,2000));
//        perLevelElecServicesTest.add(new PerLevelElecService(3,200,2500));
        assertEquals("Sai bậc thang giá điện:",3,
                     result.get(0).getLevelUpdate().getListLevel().size()
                );
        assertEquals("Sai id bậc thang giá điện:",10,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",300,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",1800,
                     result.get(0).getLevelUpdate().getListLevel().get(0).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",11,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",300,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2000,
                     result.get(0).getLevelUpdate().getListLevel().get(1).getPriceLevel()
                );
        assertEquals("Sai id bậc thang giá điện:",12,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getID()
                );
        assertEquals("Sai maxIdx bậc thang giá điện:",0,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getMaxIdxLevel()
                );
        assertEquals("Sai getPriceLevel:",2200,
                     result.get(0).getLevelUpdate().getListLevel().get(2).getPriceLevel()
                );
        
        assertEquals("Sai listIEmofbill", 2, result.get(0).getListIEMOfBill().size());
        assertEquals("Sai ID IEmofbill", 5, result.get(0).getListIEMOfBill().get(0).getID());
        assertEquals("IEmofbill khong phai cua thang truoc do", true, result.get(0).getListIEMOfBill().get(0).isIsPrevMonthIEM());
        assertEquals("thieu IEM", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 3, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 0, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 10-1, 16)),
                sdf.format(result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getReadDate()));
        assertEquals("thieu HouseholdOfContract", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 2, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 10-1, 16)), 
                sdf.format(result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("thieu household", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 2, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai ten household", "Ao Sen 2", result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 3, result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện kinh doanh", result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        assertEquals("Sai qua sai", 
                result.get(0).getLevelUpdate().getElecService().getName(), result.get(0).getListIEMOfBill().get(0).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
        // IEM2 OF BILL1 
        assertEquals("Sai ID IEmofbill", 6, result.get(0).getListIEMOfBill().get(1).getID());
        assertEquals("IEmofbill khong phai cua thang truoc do", false, result.get(0).getListIEMOfBill().get(1).isIsPrevMonthIEM());
        assertEquals("Sai IEM", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().equals(null));
        assertEquals("Sai ID IEM", 6, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getID());
        assertEquals("Sai idx IEM", 500, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getIdx());
        assertEquals("Sai readdate IEM", sdf.format(new Date(2023-1900, 11-1, 16)),
                sdf.format(result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getReadDate()));
        assertEquals("thieuD HouseholdOfContract", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().equals(null));
        assertEquals("Sai id HouseholdOfContract", 2, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getID());
        assertEquals("Sai installDate HouseholdOfContract", sdf.format(new Date(2023-1900, 10-1, 16)), 
                sdf.format(result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getInstallDateElecMeter()));
        assertEquals("Sai household", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().equals(null));
        assertEquals("Sai id household", 2, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getID());
        assertEquals("Sai id household", "Ao Sen 2", result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getHousehold().getAddress());
        assertEquals("Sai elecservice", false, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().equals(null));
        assertEquals("Sai id elecservice", 3, result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getID());
        assertEquals("Sai id elecservice", "Điện kinh doanh", result.get(0).getListIEMOfBill().get(1).getIdxElecMeter().getHouseholdOfContract().getElecService().getName());
        
    }
    
    @Test
    public void testGetDebtBillByCs3() {
        System.out.println("getDebtBillByCs");
        CsStatByTotalDebt cs = new CsStatByTotalDebt();
        cs.setID(3);
        BillDAO instance = new BillDAO();
        ArrayList<Bill> expResult = new ArrayList<>();
        ArrayList<Bill> result = instance.getDebtBillByCs(cs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
