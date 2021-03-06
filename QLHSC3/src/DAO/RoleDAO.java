/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;
import pojos.Role;
import pagination.Pageable;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class RoleDAO extends AbstractHibernateDAO<Role>{
    public RoleDAO(){
        setClazz(Role.class);
    }
    
    @Override
    public Role findByCode(String code) {
        Role entity = null;
        List<Role> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            String hql = "from Role role";
            hql += " where role.code = :code"; 
            Query query = session.createQuery(hql);

            query.setParameter("code", code, StringType.INSTANCE);
            tx = session.beginTransaction();
            
            list = query.list();    //get all
            if(!list.isEmpty()){
                entity = list.get(0);   //get first entity
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return entity;
    }
    
    @Override
    public String generateHQL(Pageable<Role> pageable){    
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Role role = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();

        //Dòng 1: SELECT * FROM Role

        //Dòng 2: WHERE code = ? AND name = ? AND priority = ? AND isdeleted = ?
        if (role != null || searchKey != null) {
            sql.append(" where");
        }
        if (role != null) {
            //code
            if (role.getCode() != null) {
                sql.append(" role.code = :code");
            }
            if (role.getCode() != null
                    && role.getName() != null) {
                sql.append(" and");
            }
            
            //name
            if (role.getName() != null) {
                sql.append(" role.name = :name");
            }
            if (role.getPriority() != null
                    && (role.getCode() != null
                    || role.getName() != null)) {
                sql.append(" and");
            }
            
            //priority
            if (role.getPriority() != null) {
                sql.append(" role.priority = :priority");
            }
            if (role.getIsDeleted()!= null
                    && (role.getCode() != null
                    || role.getName() != null
                    || role.getPriority() != null)) {
                sql.append(" and");
            }
            
            //isDeleted
            if (role.getIsDeleted() != null) {
                sql.append(" role.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (role.getCode() != null
                    || role.getName() != null
                    || role.getPriority() != null
                    || role.getIsDeleted() != null)) {
                sql.append(" and");
            }
        }
        
        //Dòng 3: AND (code LIKE ? OR name LIKE ? OR priority LIKE ?)
        if (searchKey != null) {
            sql.append(" (role.code like :searchKey");
            sql.append(" or role.name like :searchKey");
            sql.append(" or role.priority like :searchKey)");
        }
        
        //Dòng 4: ORDER BY ?, ?
        if (sorter != null
                && sorter.getSortBy() != null
                && sorter.getSortName() != null) {
            sql.append(" order by " + sorter.getSortBy() + " " + sorter.getSortName());
        }
        
        return sql.toString();
    }

    @Override
    public String generateHQLForFind(Pageable<Role> pageable) {
//        SELECT * FROM Role
//        WHERE code = ? AND name = ? AND priority = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR priority LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM Role    
        String hql = "from Role role";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }

    @Override
    public String generateHQLForCount(Pageable<Role> pageable) {
//        SELECT COUNT(*) FROM Role 
//        WHERE code = ? AND name = ? AND priority = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR priority LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //Dòng 1: SELECT COUNT(*) FROM Role    
        String hql = "select count(*) from Role role";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<Role> pageable, Session session, String hql) {
        Query query = session.createQuery(hql);
        Role role = pageable.getFilterModel();
        
        if (role != null) {
            if(role.getCode() != null) {
                query.setParameter("code", role.getCode(), StringType.INSTANCE);
            }
            if(role.getName() != null){
                query.setParameter("name", role.getName(), StringNVarcharType.INSTANCE);
            }
            if(role.getPriority() != null){
                query.setParameter("priority", role.getPriority());
            }
            if(role.getIsDeleted() != null){
                query.setParameter("isDeleted", role.getIsDeleted());
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
