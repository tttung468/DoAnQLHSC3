/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.VerificationTokenDAO;
import pojos.VerificationToken;

/**
 *
 * @author ThanhTung
 */
public class VerificationTokenBUS extends AbstractHibernateBUS<VerificationToken>{

    public VerificationTokenBUS() {
        setAbstractHiberanateDAO(new VerificationTokenDAO());
    }
    
}
