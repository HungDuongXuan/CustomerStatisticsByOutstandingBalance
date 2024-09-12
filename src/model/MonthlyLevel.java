/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class MonthlyLevel {
    private int ID;
    private Date startDate;
    private Date endDate;
    private LevelElecServiceUpdate updateLevel;

    public MonthlyLevel() {
    }

    
    
    public MonthlyLevel(int ID, Date startDate, Date endDate, LevelElecServiceUpdate updateLevel) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.updateLevel = updateLevel;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LevelElecServiceUpdate getUpdateLevel() {
        return updateLevel;
    }

    public void setUpdateLevel(LevelElecServiceUpdate updateLevel) {
        this.updateLevel = updateLevel;
    }

    
}
