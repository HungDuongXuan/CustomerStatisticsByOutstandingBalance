/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author phuongdt
 */
public class PerLevelOfBill {
    private int id;
    private ArrayList<PerLevelElecService> listLevel;

    public PerLevelOfBill() {
    }

    public PerLevelOfBill(int id, ArrayList<PerLevelElecService> listLevel) {
        this.id = id;
        this.listLevel = listLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<PerLevelElecService> getListLevel() {
        return listLevel;
    }

    public void setListLevel(ArrayList<PerLevelElecService> listLevel) {
        this.listLevel = listLevel;
    }
}
