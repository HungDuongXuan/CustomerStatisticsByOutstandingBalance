/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import model.CsStatByTotalDebt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

/**
 *
 * @author phuongdt
 */
public class CsStatByTotalDebtDAOTest {

    public CsStatByTotalDebtDAOTest() {
    }

    @Test
    public void testGetCsStatByTotalDebt() {
        System.out.println("getCsStatByTotalDebt");
        CsStatByTotalDebtDAO instance = new CsStatByTotalDebtDAO();
        ArrayList<CsStatByTotalDebt> expResult = new ArrayList<>();

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDateTime localDateTime = LocalDateTime.parse("20/02/2002", dtf);
//        Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        localDateTime = LocalDateTime.parse("21/02/2002", dtf);
//        Date date2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        localDateTime = LocalDateTime.parse("20/01/1992", dtf);
//        Date date3 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        CsStatByTotalDebt cs = new CsStatByTotalDebt(3190000, 2, 1, "Lê A", new Date(2002 - 1900, 02 - 1, 20), "Ao Sen 1", "001234567890", "0123456789", "0000000001");
        expResult.add(cs);

        cs = new CsStatByTotalDebt(1034000, 1, 2, "Văn B", new Date(2002 - 1900, 02 - 1, 21), "Ao Sen 2", "001234567891", "0123456788", "0000000002");
        expResult.add(cs);

        cs = new CsStatByTotalDebt(0, 0, 3, "Mỹ Lan", new Date(1992 - 1900, 01-1, 20), "Hà Đông 1", "001234567892", "0123456787", "0000000003");
        expResult.add(cs);

        ArrayList<CsStatByTotalDebt> result = instance.getCsStatByTotalDebt();

        // id 1
        for (int i = 0; i < result.size(); i++ ) {
            CsStatByTotalDebt x = result.get(i), y = expResult.get(i);
            Assert.assertEquals(y.getAddress(), x.getAddress());
            Assert.assertEquals(y.getCitizenIDCardNum(), x.getCitizenIDCardNum());
            Assert.assertEquals(y.getID(), x.getID());
            Assert.assertEquals(y.getFullname(), x.getFullname());
            Assert.assertEquals(y.getTaxIdenNum(), x.getTaxIdenNum());
            Assert.assertEquals(y.getTotalDebt(), x.getTotalDebt());
            Assert.assertEquals(y.getNumDebtBill(), x.getNumDebtBill());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String string1 = sdf.format(y.getDob());
            String string2 = sdf.format(x.getDob());
            Assert.assertEquals("Sai ngày sinh",string1, string2);
        }
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
