/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.StudentOfClassDAO;
import pojos.StudentOfClass;

/**
 *
 * @author ThanhTung
 */
public class StudentOfClassBUS extends AbstractHibernateBUS<StudentOfClass>{

    public StudentOfClassBUS() {
        setAbstractHiberanateDAO(new StudentOfClassDAO());
    }
    
}
