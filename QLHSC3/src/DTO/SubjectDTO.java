/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.SubjectDAO;
import pojos.Subject;

/**
 *
 * @author ThanhTung
 */
public class SubjectDTO extends AbstractHibernateDTO<Subject>{

    public SubjectDTO() {
        setAbstractHiberanateDAO(new SubjectDAO());
    }
    
}
