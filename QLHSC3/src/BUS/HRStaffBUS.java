/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HRStaffDAO;
import pojos.HRStaff;

/**
 *
 * @author ThanhTung
 */
public class HRStaffBUS extends AbstractHibernateBUS<HRStaff>{

    public HRStaffBUS() {
        setAbstractHiberanateDAO(new HRStaffDAO());
    }
    
}
