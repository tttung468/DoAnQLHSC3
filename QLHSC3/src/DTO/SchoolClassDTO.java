/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.SchoolClassDAO;
import pojos.SchoolClass;

/**
 *
 * @author ThanhTung
 */
public class SchoolClassDTO extends AbstractHibernateDTO<SchoolClass>{

    public SchoolClassDTO() {
        setAbstractHiberanateDAO(new SchoolClassDAO());
    }
    
}
