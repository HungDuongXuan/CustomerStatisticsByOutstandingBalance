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
public class HouseholdOfContract {
    private int ID;
    private Date installDateElecMeter;
    private Household household;
    private ElecService elecService;

    public HouseholdOfContract(int ID, Date installDateElecMeter, Household household, ElecService elecService) {
        this.ID = ID;
        this.installDateElecMeter = installDateElecMeter;
        this.household = household;
        this.elecService = elecService;
    }

    public HouseholdOfContract() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getInstallDateElecMeter() {
        return installDateElecMeter;
    }

    public void setInstallDateElecMeter(Date installDateElecMeter) {
        this.installDateElecMeter = installDateElecMeter;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public ElecService getElecService() {
        return elecService;
    }

    public void setElecService(ElecService elecService) {
        this.elecService = elecService;
    }
    

}
