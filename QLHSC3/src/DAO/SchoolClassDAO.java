/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import org.hibernate.type.StringType;
import pagination.Pageable;
import pojos.SchoolClass;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class SchoolClassDAO extends AbstractHibernateDAO<SchoolClass>{
    public SchoolClassDAO(){
        setClazz(SchoolClass.class);
    }
    
    @Override
    public String generateHQL(Pageable<SchoolClass> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        SchoolClass schoolClass = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();

        //SELECT * FROM SchoolClass

        //WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
        if (schoolClass != null || searchKey != null) {
            sql.append(" where");
        }
        if (schoolClass != null) {
            //code
            if (schoolClass.getCode() != null) {
                sql.append(" schoolClass.code = :code");
            }
            if (schoolClass.getCode() != null
                    && schoolClass.getName() != null) {
                sql.append(" and");
            }
            
            //name
            if (schoolClass.getName() != null) {
                sql.append(" schoolClass.name = :name");
            }
            if (schoolClass.getGrade() != null
                    && (schoolClass.getCode() != null
                    || schoolClass.getName() != null)) {
                sql.append(" and");
            }
            
            //gradeid
            if (schoolClass.getGrade()!= null) {
                sql.append(" schoolClass.grade.id = :id");
            }
            if (schoolClass.getIsDeleted()!= null
                    && (schoolClass.getCode() != null
                    || schoolClass.getName() != null
                    || schoolClass.getGrade() != null)) {
                sql.append(" and");
            }
            
            //isDeleted
            if (schoolClass.getIsDeleted() != null) {
                sql.append(" schoolClass.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (schoolClass.getCode() != null
                    || schoolClass.getName() != null
                    || schoolClass.getGrade()!= null
                    || schoolClass.getIsDeleted() != null)) {
                sql.append(" and");
            }
        }
        
        //Dòng 3: AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
        if (searchKey != null) {
            sql.append(" (schoolClass.code like :searchKey");
            sql.append(" or schoolClass.name like :searchKey");
            sql.append(" or schoolClass.grade.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<SchoolClass> pageable){
//        SELECT * FROM SchoolClass
//        WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM SchoolClass    
        String hql = " from SchoolClass schoolClass";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<SchoolClass> pageable){
//        SELECT COUNT(*) FROM SchoolClass 
//        WHERE code = ? AND name = ? AND gradeid = ? AND isdeleted = ?
//        AND (code LIKE ? OR name LIKE ? OR gradeid LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM SchoolClass    
        String hql = " select count(*) from SchoolClass schoolClass";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<SchoolClass> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        SchoolClass schoolClass = pageable.getFilterModel();
        
        if (schoolClass != null) {
            if(schoolClass.getCode() != null) {
                query.setParameter("code", schoolClass.getCode(), StringType.INSTANCE);
            }
            if(schoolClass.getName() != null){
                query.setParameter("name", schoolClass.getName(), StringNVarcharType.INSTANCE);
            }
            if(schoolClass.getGrade()!= null){
                query.setParameter("id", schoolClass.getGrade().getId());
            }
            if(schoolClass.getIsDeleted() != null){
                query.setParameter("isDeleted", schoolClass.getIsDeleted());
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
