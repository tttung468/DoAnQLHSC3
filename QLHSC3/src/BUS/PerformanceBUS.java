/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PerformanceDAO;
import pojos.Performance;

/**
 *
 * @author ThanhTung
 */
public class PerformanceBUS extends AbstractHibernateBUS<Performance>{

    public PerformanceBUS() {
        setAbstractHiberanateDAO(new PerformanceDAO());
    }
    
}
