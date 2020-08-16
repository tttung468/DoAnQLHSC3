/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.RelativesDAO;
import pojos.Relatives;

/**
 *
 * @author ThanhTung
 */
public class RelativesBUS extends AbstractHibernateBUS<Relatives>{

    public RelativesBUS() {
        setAbstractHiberanateDAO(new RelativesDAO());
    }
    
}
