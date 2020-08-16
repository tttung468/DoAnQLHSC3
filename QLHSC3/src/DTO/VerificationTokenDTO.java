/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.VerificationTokenDAO;
import pojos.VerificationToken;

/**
 *
 * @author ThanhTung
 */
public class VerificationTokenDTO extends AbstractHibernateDTO<VerificationToken>{

    public VerificationTokenDTO() {
        setAbstractHiberanateDAO(new VerificationTokenDAO());
    }
    
}
