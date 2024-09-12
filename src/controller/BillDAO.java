/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change bill license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit bill template
 */
package controller;

import static controller.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bill;
import model.CsStatByTotalDebt;
import model.ElecService;
import model.Household;
import model.HouseholdOfContract;
import model.IEMOfBill;
import model.IdxElecMeter;
import model.MonthlyLevel;
import model.PerLevelElecService;
import model.LevelElecServiceUpdate;
import model.PerLevelOfBill;
import model.User;

/**
 *
 * @author phuongdt
 */
public class BillDAO extends DAO {

    public BillDAO() {
        super();
    }

    private void setAmount(Bill bill) {
        int useIdx = bill.getListIEMOfBill().get(1).getIdxElecMeter().getIdx() - bill.getListIEMOfBill().get(0).getIdxElecMeter().getIdx();
        int sum = 0;

        int size = bill.getPlob().getListLevel().size();
        for (int i = 0; i < size; i++) {
            PerLevelElecService x = bill.getPlob().getListLevel().get(i);
            if (i == size - 1) {
                sum += useIdx * x.getPriceLevel();
                break;
            } else if (useIdx <= x.getMaxIdxLevel()) {
                sum += useIdx * x.getPriceLevel();
                break;
            }
            sum += x.getMaxIdxLevel() * x.getPriceLevel();
            useIdx -= x.getMaxIdxLevel();
        }
        bill.setAmount(sum);
    }

    public ArrayList<Bill> getDebtBillByCs(CsStatByTotalDebt cs) {
        ArrayList<Bill> lsBill = new ArrayList<>();

        String sql = """
                     SELECT distinct b.ID as billID, b.closingDate, b.isPayment, 
                   						perLevel.ID as perLevelID, perLevel.maxIdxLevel, perLevel.priceLevel,
                                           plob.id as plobID,
                                        b.UserID, u.fullname, u.username, u.password, u.position,
                                        (
                                        	select distinct count(*)
                                        	from tbl_per_level_elec_service as perLevel
                                        	join tbl_per_level_of_bill as plob2 on plob2.PerLevelElecServiceID = perLevel.id
                                           where plob2.BillID = b.id
                                        ) as numPerLevel,
                                        
                                        
                                        iemofbill.ID as iemofbillID, iemofbill.isPrevMonthIEM,
                                        iemofbill.idxElecMeterID, iem.idx, iem.readDate, 
                                        iem.householdOfContractID, hoc.installDateElecMeter, 
                                        hoc.householdID, household.address as addressHousehold,
                                        hoc.elecserviceID, es.name as nameElecService
                                        from tbl_bill as b
                                        join tbl_user as u on u.ID = b.UserID
                                        join tbl_per_level_of_bill as plob on plob.BillID = b.ID
                                        join tbl_per_level_elec_service as perLevel on perLevel.id = plob.PerLevelElecServiceID
                                        join tbl_iem_of_bill as iemofbill on b.ID = iemofbill.BillID
                                        join tbl_idx_elec_meter as iem on iem.ID = iemofbill.IdxElecMeterID
                                        join tbl_household_of_contract as hoc on hoc.ID = iem.HouseholdOfContractID
                                        join tbl_household as household on household.id = hoc.HouseholdId
                                        join tbl_elec_service as es on es.id = hoc.ElecServiceID
                                        join tbl_contract as con on con.ID = hoc.ContractID
                                        join tbl_customer as cs on cs.ID = con.CustomerID
                                        where cs.ID = ? and con.signDate <= b.closingDate and con.endDate >= b.closingDate and b.isPayment = 0;
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cs.getID());
            ResultSet rs = ps.executeQuery();
//            System.out.println(rs);
            rs.next();
            while (true) {
                boolean check = false;
                
                Bill b = new Bill();
                b.setID(rs.getInt("BillID"));
                b.setClosingDate(rs.getDate("closingDate"));
                b.setIsPayment(rs.getByte("isPayment") == 1 ? true : false);
                
                User u = new User();
                u.setID(rs.getInt("UserID"));
                u.setFullname(rs.getString("fullname"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setPosition(rs.getString("position"));
                
                b.setUser(u);

                ArrayList<IEMOfBill> listIEMOfBills = new ArrayList<>();
                for (int i = 1; i <= 2; i++) {
                    IEMOfBill iemob = new IEMOfBill();
                    iemob.setID(rs.getInt("IEMofBILLID"));
                    iemob.setIsPrevMonthIEM(rs.getByte("IsPrevMonthIEM") == 1 ? true : false);

                    IdxElecMeter idxElecMeter = new IdxElecMeter();
                    idxElecMeter.setID(rs.getInt("idxElecMeterID"));
                    idxElecMeter.setIdx(rs.getInt("idx"));
                    idxElecMeter.setReadDate(rs.getDate("readDate"));

                    HouseholdOfContract householdOfContract = new HouseholdOfContract();
                    householdOfContract.setID(rs.getInt("householdOfContractID"));
                    householdOfContract.setInstallDateElecMeter(rs.getDate("InstallDateElecMeter"));

                    ElecService elecService = new ElecService();
                    elecService.setID(rs.getInt("elecserviceID"));
                    elecService.setName(rs.getString("nameElecService"));
                    householdOfContract.setElecService(elecService);

                    Household household = new Household();
                    household.setID(rs.getShort("householdid"));
                    household.setAddress(rs.getString("addressHousehold"));
                    householdOfContract.setHousehold(household);
                    idxElecMeter.setHouseholdOfContract(householdOfContract);
                    iemob.setIdxElecMeter(idxElecMeter);
                    listIEMOfBills.add(iemob);

                    PerLevelOfBill plob = new PerLevelOfBill();
                    plob.setId(rs.getInt("plobID"));
                    
                    
                    plob.setListLevel(new ArrayList<>());
                    int x = rs.getInt("numPerLevel");
                    for (int j = 0; j < x; j++) {
                        PerLevelElecService perLevel = new PerLevelElecService();
                        perLevel.setID(rs.getInt("perlevelID"));
                        perLevel.setMaxIdxLevel(rs.getInt("maxIdxLevel"));
                        perLevel.setPriceLevel(rs.getInt("pricelevel"));
                        plob.getListLevel().add(perLevel);
                        if(!rs.next()) {
                            check = true;
                            break;
                        };
                    }
                    b.setPlob(plob);
                }
                b.setListIEMOfBill(listIEMOfBills);
                setAmount(b);
                
                if(b.getAmount()!= 0) {
                    lsBill.add(b);
                }
                
                if (check) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lsBill.sort((o1, o2) -> {
            return o1.getClosingDate().compareTo(o2.getClosingDate());
        });
        return lsBill;
    }

}
