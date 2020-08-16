/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ScoreDAO;
import pojos.Score;

/**
 *
 * @author ThanhTung
 */
public class ScoreBUS extends AbstractHibernateBUS<Score>{

    public ScoreBUS() {
        setAbstractHiberanateDAO(new ScoreDAO());
    }
    
}
