/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ConductTypeDAO;
import pojos.ConductType;

/**
 *
 * @author ThanhTung
 */
public class ConductTypeBUS extends AbstractHibernateBUS<ConductType>{

    public ConductTypeBUS() {
        setAbstractHiberanateDAO(new ConductTypeDAO());
    }
    
}
