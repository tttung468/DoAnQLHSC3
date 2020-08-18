/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import pagination.Pageable;
import pojos.Account;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class AccountDAO extends AbstractHibernateDAO<Account> {
    public AccountDAO(){
        setClazz(Account.class);
    }
    
    @Override
    public String generateHQL(Pageable<Account> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Account account = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();

//        WHERE account.username=? AND account.password=? 
//              AND account.role.id = ? AND account.isDeleted = ?
        if (account != null || searchKey != null) {
            sql.append(" where");
        }
        if (account != null) {
            // account.username=? 
            if (account.getUsername()!= null) {
                sql.append(" account.username = :username");
            }
            if (account.getPassword() != null
                    && account.getUsername() != null) {
                sql.append(" and");
            }
            
            // account.password=? 
            if (account.getPassword()!= null) {
                sql.append(" account.password = :password");
            }
            if (account.getRole()!= null
                    && (account.getUsername() != null
                    || account.getPassword() != null)) {
                sql.append(" and");
            }
            
            // account.role.id = ? 
            if (account.getRole()!= null) {
                sql.append(" account.role.id = :roleID");
            }
            if (account.getIsDeleted()!= null
                    && (account.getUsername() != null
                    || account.getPassword() != null
                    || account.getRole()!= null)) {
                sql.append(" and");
            }
            
            // account.isDeleted = ?  
            if (account.getIsDeleted()!= null) {
                sql.append(" account.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (account.getUsername() != null
                    || account.getPassword() != null
                    || account.getRole()!= null
                    || account.getIsDeleted()!= null)) {
                sql.append(" and");
            }
        }

//        AND (account.username LIKE ? OR account.password LIKE ?
//              OR account.role.id LIKE ?)
        if (searchKey != null) {
            sql.append(" (account.username like :searchKey");
            sql.append(" or account.password like :searchKey");
            sql.append(" or account.role.id like :searchKey)");
        }

//        ORDER BY ? ?       
        if (sorter != null
                && sorter.getSortBy() != null
                && sorter.getSortName() != null) {
            sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
        }
        
        return sql.toString();
    }
    
    @Override
    public String generateHQLForFind(Pageable<Account> pageable){
//        SELECT * FROM Account account
//        WHERE account.username=? AND account.password=? 
//              AND account.role.id = ? AND account.isDeleted = ?
//        AND (account.username LIKE ? OR account.password LIKE ?
//              OR account.role.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM Account account
        String hql = "from Account account";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<Account> pageable){
//        SELECT COUNT(*) FROM Account account
//        WHERE account.username=? AND account.password=? 
//              AND account.role.id = ? AND account.isDeleted = ?
//        AND (account.username LIKE ? OR account.password LIKE ?
//              OR account.role.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM Account account
        String hql = "select count(*) from Account account";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<Account> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        Account account = pageable.getFilterModel();
        
        if (account != null) {
            if(account.getUsername()!= null) {
                query.setParameter("username", account.getUsername(), StringType.INSTANCE);
            }
            if(account.getPassword()!= null) {
                query.setParameter("password", account.getPassword(), StringType.INSTANCE);
            }
            if(account.getRole()!= null){
                query.setParameter("roleID", account.getRole().getId());
            }
            if(account.getIsDeleted() != null){
                query.setParameter("isDeleted", account.getIsDeleted());
            }
        }
        if (pageable.getSearchKey() != null) {
            query.setString("searchKey", "%"+pageable.getSearchKey()+"%");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getLimit());
        }  
        
        return query;
    }
}
