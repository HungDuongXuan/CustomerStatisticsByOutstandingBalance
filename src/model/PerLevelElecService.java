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
public class PerLevelElecService implements Serializable{
    private int ID;
    private int maxIdxLevel;
    private int priceLevel;

    public PerLevelElecService() {
    }
    
    

    public PerLevelElecService(int ID, int maxIdxLevel, int priceLevel) {
        this.ID = ID;
        this.maxIdxLevel = maxIdxLevel;
        this.priceLevel = priceLevel;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaxIdxLevel() {
        return maxIdxLevel;
    }

    public void setMaxIdxLevel(int maxIdxLevel) {
        this.maxIdxLevel = maxIdxLevel;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }
}
