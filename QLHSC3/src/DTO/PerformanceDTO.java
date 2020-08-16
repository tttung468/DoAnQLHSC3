/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.PerformanceDAO;
import pojos.Performance;

/**
 *
 * @author ThanhTung
 */
public class PerformanceDTO extends AbstractHibernateDTO<Performance>{

    public PerformanceDTO() {
        setAbstractHiberanateDAO(new PerformanceDAO());
    }
    
}
