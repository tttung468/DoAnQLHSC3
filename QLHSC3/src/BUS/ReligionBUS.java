/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ReligionDAO;
import pojos.Religion;

/**
 *
 * @author ThanhTung
 */
public class ReligionBUS extends AbstractHibernateBUS<Religion>{

    public ReligionBUS() {
        setAbstractHiberanateDAO(new ReligionDAO());
    }
    
}
