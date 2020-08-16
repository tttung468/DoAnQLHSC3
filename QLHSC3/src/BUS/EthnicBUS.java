/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.EthnicDAO;
import pojos.Ethnic;

/**
 *
 * @author ThanhTung
 */
public class EthnicBUS extends AbstractHibernateBUS<Ethnic>{

    public EthnicBUS() {
        setAbstractHiberanateDAO(new EthnicDAO());
    }
    
}
