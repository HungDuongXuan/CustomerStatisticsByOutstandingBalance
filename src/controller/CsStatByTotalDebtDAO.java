/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Bill;
import model.CsStatByTotalDebt;
import model.Customer;
import model.ElecService;
import model.Household;
import model.HouseholdOfContract;
import model.IEMOfBill;
import model.IdxElecMeter;
import model.LevelElecServiceUpdate;
import model.PerLevelElecService;
import model.User;

/**
 *
 * @author phuongdt
 */
public class CsStatByTotalDebtDAO extends DAO{

    public CsStatByTotalDebtDAO() {
        super();
    }

    
    public ArrayList<CsStatByTotalDebt> getCsStatByTotalDebt() {
        ArrayList<CsStatByTotalDebt> lsCs = new ArrayList<>();
//        String sql = """
//                        SELECT distinct cs.ID as CustomerID, cs.fullname, cs.dob, cs.address, cs.citizenIDCardNum, cs.phoneNum, cs.taxIdenNum,b.ID,  (Select max(iem1.idxElecMeter) - min (iem2.idxElecMeter)FROM tbl_idx_elec_meter iem1, tbl_idx_elec_meter iem2 where iem1.id = iemof), per.maxIdxLevel, per.priceLevel
//                        from tbl_bill as b
//                        join tbl_iem_of_bill as iemofbill on b.ID = iemofbill.BillID
//                        join tbl_idx_elec_meter as iem on iem.ID = iemofbill.IdxElecMeterID
//                        join tbl_household_of_contract as hoc on hoc.ID = iem.HouseholdOfContractID
//                        join tbl_contract as con on con.ID = hoc.ContractID
//                        join tbl_customer as cs on cs.ID = con.CustomerID
//                        join tbl_Level_elec_service_update as levelUpdate on levelUpdate.ID = b.LevelElecServiceUpdateID
//                        join tbl_per_level_elec_service as per on per.LevelElecServiceUpdateID = levelUpdate.ID
//                        where con.signDate <= b.closingDate and con.endDate >= b.closingDate and b.isPayment = 0;  
//                     """;
        String sql = """
            SELECT distinct  cs.id as CustomerID, cs.fullname, cs.dob, cs.address, cs.citizenIDCardNum, cs.phoneNum, cs.taxIdenNum,
                                   (select
                                       count(distinct b.id )
                                       from tbl_contract as con
                                       join tbl_household_of_contract as hoc on con.ID = hoc.ContractID
                                       join tbl_idx_elec_meter as iem on iem.HouseholdOfContractID = hoc.ID
                                       join tbl_iem_of_bill as iemofbill on iemofbill.IdxElecMeterID = iem.ID
                                       join tbl_bill as b on b.id = iemofbill.billid
                                       where cs.ID = con.CustomerID and con.signDate <= b.closingDate and con.endDate >= b.closingDate and b.isPayment = 0
                                   ) as numDebtBill,
                   
                                   (select 
                                       case
                                           when cast(sum(amount) * 1.1 as unsigned) is null then 0
                                           else cast(sum(amount) * 1.1 as unsigned)
                                       end 
                                   from (
                                       SELECT distinct
                                       con.CustomerID as customerID1,
                                       tbl3.billid,
                                       tbl3.useIdx AS useIdx,
                                       (select
                                           sum(tbl5.priceLevel*tbl5.useLevelIdx) as amount
                                           from 
                   							(select
                   								priceLevel,
                   								case 
                   									   when useIdx >= tbl4.endLevelIdx and tbl4.maxIdxLevel = 0  then useIdx - tbl4.beginLevelIdx
                   									   when useIdx >= tbl4.endLevelIdx then tbl4.maxIdxLevel
                   									   when useIdx < tbl4.beginLevelIdx then 0
                   									   else useIdx - tbl4.beginLevelIdx
                   								end as useLevelIdx
                   								from 
                   								(
                   									select distinct perLevel.ID as perLevelID,
                   									perLevel.maxIdxLevel AS maxIdxLevel,
                   									perLevel.priceLevel AS priceLevel,
                   									(
                   										select 
                   											 case
                   													 when sum(perLevel1.maxIdxLevel) is null then 0
                   													 else sum(perLevel1.maxIdxLevel)
                   											 end
                   										from tbl_per_level_elec_service as perLevel1
                                                                                                join tbl_per_level_of_bill as plob on plob.PerLevelElecServiceID = perLevel1.ID
                   										where plob.BillID = b.ID and perLevel1.id < perLevelID
                   									) as beginLevelIdx,
                   									(
                   										select 
                   											 case
                   													 when sum(perLevel1.maxIdxLevel) is null then 0
                   													 else sum(perLevel1.maxIdxLevel)
                   											 end
                   										from tbl_per_level_elec_service as perLevel1
                                                           join tbl_per_level_of_bill as plob on plob.PerLevelElecServiceID = perLevel1.ID
                   										where plob.BillID = b.ID and perLevel1.id <= perLevelID
                   									) as endLevelIdx
                   									from tbl_per_level_elec_service as perLevel
                                                       join tbl_per_level_of_bill as plob on plob.PerLevelElecServiceID = perLevel.ID
                   									where perLevel.ID = Plob.PerLevelElecServiceID and Plob.BillID = b.ID
                   								) as tbl4
                   						) as tbl5
                   					) as amount
                   
                                   FROM tbl_contract AS con
                                   JOIN tbl_household_of_contract AS hoc ON con.ID = hoc.ContractID
                                   JOIN (
                                       SELECT
                                              tbl2.idx - tbl1.idx AS useIdx,
                                              tbl1.BillID,
                                              tbl1.householdofcontractid
                                       FROM 
                                           (
                                               SELECT
                                                    iem1.idx,
                                                    iemofbill1.BillID AS billID,
                                                    iem1.householdofcontractid
                                               FROM tbl_idx_elec_meter AS iem1
                                               JOIN tbl_iem_of_bill AS iemofbill1 ON iemofbill1.IdxElecMeterID = iem1.ID
                                               WHERE iemofbill1.isPrevMonthIEM = 1
                                           ) AS tbl1
                                           JOIN (
                                               SELECT
                                                   iem2.idx,
                                                   iemofbill2.BillID AS billID
                                               FROM tbl_idx_elec_meter AS iem2
                                               JOIN tbl_iem_of_bill AS iemofbill2 ON iemofbill2.IdxElecMeterID = iem2.ID
                                               WHERE iemofbill2.isPrevMonthIEM = 0
                                           ) AS tbl2 ON tbl1.BillID = tbl2.BillID
                                   ) AS tbl3 ON tbl3.householdofcontractid = hoc.ID
                                   JOIN tbl_bill AS b ON b.id = tbl3.billid
                                   WHERE con.signDate <= b.closingDate
                                           AND con.endDate >= b.closingDate
                                           AND b.isPayment = 0 
                                   GROUP BY customerID1, tbl3.billid, tbl3.useIdx, amount) as tbl6
                                  where customerID1 = CustomerID 
                   			) as totalDebt
                                      from tbl_customer as cs;
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
           ResultSet rs = ps.executeQuery();
           while(rs.next()) {
               CsStatByTotalDebt cs = new CsStatByTotalDebt(); 
               
               cs.setID(rs.getInt("CustomerID"));
               cs.setFullname(rs.getString("fullname"));
               cs.setAddress(rs.getString("address"));
               cs.setCitizenIDCardNum(rs.getString("CitizenIDCardNum"));
               cs.setTaxIdenNum(rs.getString("taxIdenNum"));
               cs.setPhoneNum(rs.getString("phoneNum"));
               cs.setDob(rs.getDate("dob"));
               cs.setNumDebtBill(rs.getInt("numDebtBill"));
               cs.setTotalDebt(rs.getInt("totalDebt"));
               lsCs.add(cs);
           }
        } catch (SQLException ex) {
            ex.printStackTrace(); // In ra thông tin lỗi để gỡ lỗi
        }
        lsCs.sort((o1, o2)-> {
            return -o1.getTotalDebt()+o2.getTotalDebt();
        });
        return lsCs;
    }
    
    
    
   
}
