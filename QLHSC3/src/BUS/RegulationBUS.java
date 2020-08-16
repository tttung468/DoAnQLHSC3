/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.RegulationDAO;
import pojos.Regulation;

/**
 *
 * @author ThanhTung
 */
public class RegulationBUS extends AbstractHibernateBUS<Regulation>{

    public RegulationBUS() {
        setAbstractHiberanateDAO(new RegulationDAO());
    }
    
}
