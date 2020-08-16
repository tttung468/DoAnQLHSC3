/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.EthnicDAO;
import pojos.Ethnic;

/**
 *
 * @author ThanhTung
 */
public class EthnicDTO extends AbstractHibernateDTO<Ethnic>{

    public EthnicDTO() {
        setAbstractHiberanateDAO(new EthnicDAO());
    }
    
}
