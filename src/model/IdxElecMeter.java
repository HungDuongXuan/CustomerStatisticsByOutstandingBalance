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
public class IdxElecMeter implements Serializable{
    private int ID;
    private int idx;
    private Date readDate;
    private HouseholdOfContract householdOfContract;

    public IdxElecMeter() {
    }

    public IdxElecMeter(int ID, int idx, Date readDate, HouseholdOfContract householdOfContract) {
        this.ID = ID;
        this.idx = idx;
        this.readDate = readDate;
        this.householdOfContract = householdOfContract;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public HouseholdOfContract getHouseholdOfContract() {
        return householdOfContract;
    }

    public void setHouseholdOfContract(HouseholdOfContract householdOfContract) {
        this.householdOfContract = householdOfContract;
    }
    
    

    
}
