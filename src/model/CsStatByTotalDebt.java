/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author phuongdt
 */
public class CsStatByTotalDebt extends Customer{
    private int totalDebt;
    private int numDebtBill;
    
    

    public CsStatByTotalDebt(int totalDebt, int numBillDebt, int ID, String fullname, Date dob, String address, String citizenIDCardNum, String phoneNum, String taxIdenNum) {
        super(ID, fullname, dob, address, citizenIDCardNum, phoneNum, taxIdenNum);
        this.totalDebt = totalDebt;
        this.numDebtBill = numBillDebt;
    }

    public CsStatByTotalDebt() {
    }
    
    public CsStatByTotalDebt(int ID, String fullname, Date dob, String address, String citizenIDCardNum, String phoneNum, String tacIdenNum) {
        super(ID, fullname, dob, address, citizenIDCardNum, phoneNum, tacIdenNum);
    }
    
    public int getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        this.totalDebt = totalDebt;
    }

    public int getNumDebtBill() {
        return numDebtBill;
    }

    public void setNumDebtBill(int numBillDebt) {
        this.numDebtBill = numBillDebt;
    }

    @Override
    public String toString() {
        return super.getID() + " " + super.getAddress() + " " + super.getFullname() +" " + super.getDob() + " " + numDebtBill + " " +totalDebt + "\n";
    }
    
    
    
}
