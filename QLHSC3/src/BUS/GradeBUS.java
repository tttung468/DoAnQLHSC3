/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.GradeDAO;
import pojos.Grade;

/**
 *
 * @author ThanhTung
 */
public class GradeBUS extends AbstractHibernateBUS<Grade>{

    public GradeBUS() {
        setAbstractHiberanateDAO(new GradeDAO());
    }
    
}
