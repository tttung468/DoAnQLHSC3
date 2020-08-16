/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ScoreTypeDAO;
import pojos.ScoreType;

/**
 *
 * @author ThanhTung
 */
public class ScoreTypeBUS extends AbstractHibernateBUS<ScoreType>{

    public ScoreTypeBUS() {
        setAbstractHiberanateDAO(new ScoreTypeDAO());
    }
    
}
