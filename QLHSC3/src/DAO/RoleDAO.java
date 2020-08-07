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
import pojos.Role;
import qlhsc3.Pageable;
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
    public List<Role> find(Pageable<Role> pageable) {
        Integer offset = pageable.getOffset();
        Integer limit = pageable.getLimit();
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Role role = pageable.getFilterModel();
        List<Role> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Query query;
        
//        Câu truy vấn:
//        SELECT * FROM Role 
//        WHERE code = ? AND name = ? AND priority = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR priority LIKE ?)
//        ORDER BY ?, ?
//        LIMIT ?, ?

        //Dòng 1: SELECT * FROM Role 
        StringBuilder sql = new StringBuilder("from Role role");

        //Dòng 2: WHERE code = ? AND name = ? AND priority = ? AND isdeleted = ?
        if (role != null || searchKey != null) {
            sql.append(" WHERE");
        }
        if (role != null) {
            //code
            if (role.getCode() != null) {
                sql.append(" role.code=:code");
            }
            if (role.getCode() != null
                    && role.getName() != null) {
                sql.append(" AND");
            }
            
            //name
            if (role.getName() != null) {
                sql.append(" role.name=:name");
            }
            if (role.getPriority() != null
                    && (role.getCode() != null
                    || role.getName() != null)) {
                sql.append(" AND");
            }
            
            //priority
            if (role.getPriority() != null) {
                sql.append(" role.priority=:priority");
            }
            if (role.getIsdeleted()!= null
                    && (role.getCode() != null
                    || role.getName() != null
                    || role.getPriority() != null)) {
                sql.append(" AND");
            }
            
            //isDeleted
            if (role.getIsdeleted() != null) {
                sql.append(" role.isdeleted=:isdeleted");
            }
            if (searchKey != null
                    && (role.getCode() != null
                    || role.getName() != null
                    || role.getPriority() != null
                    || role.getIsdeleted() != null)) {
                sql.append(" AND");
            }
        }
        
        //Dòng 3: AND (code LIKE ? OR name LIKE ? OR priority LIKE ?)
        if (searchKey != null) {
            sql.append(" code LIKE :searchKey");
            sql.append(" OR name LIKE :searchKey");
            sql.append(" OR priority LIKE :searchKey");
        }
        
        //Dòng 4: ORDER BY ?, ?
        if (sorter != null
                && sorter.getSortBy() != null
                && sorter.getSortName() != null) {
            sql.append(" ORDER BY ").append(sorter.getSortBy()).append(" ");
            sql.append(sorter.getSortName());
        }
        
        //Dòng 5: LIMIT ?, ?
//        if (offset != null
//                && limit != null) {
//            sql.append(" LIMIT " + offset + ", " + limit);
//        }


        try {
            query = session.createQuery(sql.toString());
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            tx = session.beginTransaction();
            list = query.list();    //get all
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return null;
    }
}
