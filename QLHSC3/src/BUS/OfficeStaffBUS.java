/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.OfficeStaffDAO;
import pojos.OfficeStaff;

/**
 *
 * @author ThanhTung
 */
public class OfficeStaffBUS extends AbstractHibernateBUS<OfficeStaff>{

    public OfficeStaffBUS() {
        setAbstractHiberanateDAO(new OfficeStaffDAO());
    }
    
}
