/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.HeadmasterDAO;
import pojos.Headmaster;

/**
 *
 * @author ThanhTung
 */
public class HeadmasterDTO extends AbstractHibernateDTO<Headmaster>{

    public HeadmasterDTO() {
        setAbstractHiberanateDAO(new HeadmasterDAO());
    }
    
}
