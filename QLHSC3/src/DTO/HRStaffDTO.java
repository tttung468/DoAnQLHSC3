/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.HRStaffDAO;
import pojos.HRStaff;

/**
 *
 * @author ThanhTung
 */
public class HRStaffDTO extends AbstractHibernateDTO<HRStaff>{

    public HRStaffDTO() {
        setAbstractHiberanateDAO(new HRStaffDAO());
    }
    
}
