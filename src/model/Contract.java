/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Contract implements Serializable{
    private int ID;
    private Date signDate;
    private Date endDate;
    private int numHousehold;
    private ArrayList<Household> listHouseholds;
    private Customer customer;
    private User user;

    public Contract(int ID, Date signDate, Date endDate, int numHousehold, ArrayList<Household> listHouseholds, Customer customer, User user) {
        this.ID = ID;
        this.signDate = signDate;
        this.endDate = endDate;
        this.numHousehold = numHousehold;
        this.listHouseholds = listHouseholds;
        this.customer = customer;
        this.user = user;
    }

    public Contract() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumHousehold() {
        return numHousehold;
    }

    public void setNumHousehold(int numHousehold) {
        this.numHousehold = numHousehold;
    }

    public ArrayList<Household> getListHouseholds() {
        return listHouseholds;
    }

    public void setListHouseholds(ArrayList<Household> listHouseholds) {
        this.listHouseholds = listHouseholds;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
}
