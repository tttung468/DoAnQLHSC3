/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.RegulationDAO;
import pojos.Regulation;

/**
 *
 * @author ThanhTung
 */
public class RegulationDTO extends AbstractHibernateDTO<Regulation>{

    public RegulationDTO() {
        setAbstractHiberanateDAO(new RegulationDAO());
    }
    
}
