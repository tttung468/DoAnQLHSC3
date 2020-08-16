/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.NationalityDAO;
import pojos.Nationality;

/**
 *
 * @author ThanhTung
 */
public class NationalityDTO extends AbstractHibernateDTO<Nationality>{

    public NationalityDTO() {
        setAbstractHiberanateDAO(new NationalityDAO());
    }
    
}
