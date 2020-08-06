/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import pojos.Role;

/**
 *
 * @author ThanhTung
 */
public class RoleDAO extends AbstractHibernateDAO<Role>{
    public RoleDAO(){
        setClazz(Role.class);
    }
}
