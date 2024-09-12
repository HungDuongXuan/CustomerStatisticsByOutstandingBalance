/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class IEMOfBill implements Serializable{
    private int ID;
    private boolean isPrevMonthIEM;
    private IdxElecMeter idxElecMeter;
    private HouseholdOfContract householdOfContract;

    public IEMOfBill() {
    }

    public IEMOfBill(int ID, boolean isPrevMonthIEM, IdxElecMeter idxElecMeter) {
        this.ID = ID;
        this.isPrevMonthIEM = isPrevMonthIEM;
        this.idxElecMeter = idxElecMeter;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isIsPrevMonthIEM() {
        return isPrevMonthIEM;
    }

    public void setIsPrevMonthIEM(boolean isPrevMonthIEM) {
        this.isPrevMonthIEM = isPrevMonthIEM;
    }

    public IdxElecMeter getIdxElecMeter() {
        return idxElecMeter;
    }

    public void setIdxElecMeter(IdxElecMeter idxElecMeter) {
        this.idxElecMeter = idxElecMeter;
    }
    
    
}
