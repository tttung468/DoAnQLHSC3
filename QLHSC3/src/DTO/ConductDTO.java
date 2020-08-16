/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.ConductDAO;
import pojos.Conduct;

/**
 *
 * @author ThanhTung
 */
public class ConductDTO extends AbstractHibernateDTO<Conduct>{
    
    public ConductDTO() {
        setAbstractHiberanateDAO(new ConductDAO());
    }
    
}
