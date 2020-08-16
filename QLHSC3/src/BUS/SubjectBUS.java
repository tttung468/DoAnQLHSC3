/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SubjectDAO;
import pojos.Subject;

/**
 *
 * @author ThanhTung
 */
public class SubjectBUS extends AbstractHibernateBUS<Subject>{

    public SubjectBUS() {
        setAbstractHiberanateDAO(new SubjectDAO());
    }
    
}
