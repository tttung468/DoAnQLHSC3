/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.ObservationDAO;
import pojos.Observation;

/**
 *
 * @author ThanhTung
 */
public class ObservationDTO extends AbstractHibernateDTO<Observation>{

    public ObservationDTO() {
        setAbstractHiberanateDAO(new ObservationDAO());
    }
    
}
