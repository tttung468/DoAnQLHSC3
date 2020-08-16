/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SchoolYearDAO;
import pojos.SchoolYear;

/**
 *
 * @author ThanhTung
 */
public class SchoolYearBUS extends AbstractHibernateBUS<SchoolYear>{

    public SchoolYearBUS() {
        setAbstractHiberanateDAO(new SchoolYearDAO());
    }
    
}
