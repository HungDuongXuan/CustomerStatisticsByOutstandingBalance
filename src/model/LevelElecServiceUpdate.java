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
public class LevelElecServiceUpdate implements Serializable{
    private int ID;
    private Date updateDate;
    private ArrayList<PerLevelElecService> listLevel;
    private ElecService elecService;

    public LevelElecServiceUpdate() {
    }
    
    public LevelElecServiceUpdate(int ID, Date updateDate, ArrayList<PerLevelElecService> listLevel, ElecService elecService) {
        this.ID = ID;
        this.updateDate = updateDate;
        this.listLevel = listLevel;
        this.elecService = elecService;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public ArrayList<PerLevelElecService> getListLevel() {
        return listLevel;
    }

    public void setListLevel(ArrayList<PerLevelElecService> listLevel) {
        this.listLevel = listLevel;
    }

    public ElecService getElecService() {
        return elecService;
    }

    public void setElecService(ElecService elecService) {
        this.elecService = elecService;
    }

    
    
}
