/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.AccountDAO;
import pojos.Account;

/**
 *
 * @author ThanhTung
 */
public class AccountBUS extends AbstractHibernateBUS<Account>{
    
    public AccountBUS(){
        setAbstractHiberanateDAO(new AccountDAO());
    }
}
