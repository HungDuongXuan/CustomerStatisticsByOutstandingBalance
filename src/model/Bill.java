/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author ADMIN
 */
public class Bill implements Serializable{
    private int ID;
    private Date closingDate;
    private boolean isPayment;
    private int amount;
    private User user;
    private PerLevelOfBill plob;
    private ArrayList<IEMOfBill> listIEMOfBill;

    public Bill() {
    }

    public Bill(int ID, Date closingDate, boolean isPayment, int amount, User user, PerLevelOfBill plob, ArrayList<IEMOfBill> listIEMOfBill) {
        this.ID = ID;
        this.closingDate = closingDate;
        this.isPayment = isPayment;
        this.amount = amount;
        this.user = user;
        this.plob = plob;
        this.listIEMOfBill = listIEMOfBill;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isIsPayment() {
        return isPayment;
    }

    public void setIsPayment(boolean isPayment) {
        this.isPayment = isPayment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PerLevelOfBill getPlob() {
        return plob;
    }

    public void setPlob(PerLevelOfBill plob) {
        this.plob = plob;
    }

    public ArrayList<IEMOfBill> getListIEMOfBill() {
        return listIEMOfBill;
    }

    public void setListIEMOfBill(ArrayList<IEMOfBill> listIEMOfBill) {
        this.listIEMOfBill = listIEMOfBill;
    }
    
    
}


