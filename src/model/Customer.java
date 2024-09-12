/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Customer implements Serializable{
    private int ID;
    private String fullname;
    private Date dob;
    private String address;
    private String citizenIDCardNum;
    private String phoneNum;
    private String taxIdenNum;

        
    public Customer() {
        
    }

    public Customer(int ID, String fullname, Date dob, String address, String citizenIDCardNum, String phoneNum, String tacIdenNum) {
        this.ID = ID;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.citizenIDCardNum = citizenIDCardNum;
        this.phoneNum = phoneNum;
        this.taxIdenNum = tacIdenNum;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCitizenIDCardNum(String citizenIDCardNum) {
        this.citizenIDCardNum = citizenIDCardNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setTaxIdenNum(String tacIdenNum) {
        this.taxIdenNum = tacIdenNum;
    }


    public int getID() {
        return ID;
    }

    public String getFullname() {
        return fullname;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getCitizenIDCardNum() {
        return citizenIDCardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getTaxIdenNum() {
        return taxIdenNum;
    }
}
