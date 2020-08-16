/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.SemesterDAO;
import pojos.Semester;

/**
 *
 * @author ThanhTung
 */
public class SemesterDTO extends AbstractHibernateDTO<Semester>{

    public SemesterDTO() {
        setAbstractHiberanateDAO(new SemesterDAO());
    }
    
}
