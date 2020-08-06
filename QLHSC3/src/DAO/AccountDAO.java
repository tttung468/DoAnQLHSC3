/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Account;

/**
 *
 * @author ThanhTung
 */
public class AccountDAO extends AbstractHibernateDAO<Account> {
    public AccountDAO(){
        setClazz(Account.class);
    }
    
    
}
