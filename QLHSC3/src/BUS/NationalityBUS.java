/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NationalityDAO;
import pojos.Nationality;

/**
 *
 * @author ThanhTung
 */
public class NationalityBUS extends AbstractHibernateBUS<Nationality>{

    public NationalityBUS() {
        setAbstractHiberanateDAO(new NationalityDAO());
    }
    
}
