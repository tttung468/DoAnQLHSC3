/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ConductDAO;
import pojos.Conduct;

/**
 *
 * @author ThanhTung
 */
public class ConductBUS extends AbstractHibernateBUS<Conduct>{
    
    public ConductBUS() {
        setAbstractHiberanateDAO(new ConductDAO());
    }
    
}
