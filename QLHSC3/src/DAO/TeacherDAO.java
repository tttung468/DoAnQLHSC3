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
import pagination.Pageable;
import pojos.Teacher;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class TeacherDAO extends AbstractHibernateDAO<Teacher>{
    public TeacherDAO(){
        setClazz(Teacher.class);
    }
    
    @Override
    public Teacher findByCode(String code) {
        Teacher entity = null;
        List<Teacher> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            String hql = "from Teacher teacher";
            hql += " where teacher.code = :code"; 
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
    public String generateHQL(Pageable<Teacher> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Teacher teacher = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
        
//        WHERE teacher.code=? AND teacher.name=? AND teacher.phone=? AND teacher.avatarpath=?
//              AND teacher.gender=? AND teacher.birth=? AND teacher.address=? AND teacher.salary=?
//              AND teacher.subject.id=? AND teacher.account.id=? AND teacher.isDeleted=?
        if (teacher != null || searchKey != null) {
            sql.append(" where");
        }
        if (teacher != null) {
            //teacher.code=? 
            if (teacher.getCode() != null) {
                sql.append(" teacher.code = :code");
            }
            if (teacher.getCode() != null
                    && teacher.getName() != null) {
                sql.append(" and");
            }
            
            //teacher.name=? 
            if (teacher.getName() != null) {
                sql.append(" teacher.name = :name");
            }
            if (teacher.getPhone() != null
                    && (teacher.getCode() != null
                    || teacher.getName() != null)) {
                sql.append(" and");
            }
            
            //teacher.phone=? 
            if (teacher.getPhone()!= null) {
                sql.append(" teacher.phone = :phone");
            }
            if (teacher.getAvatarpath()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null)) {
                sql.append(" and");
            }
            
            //teacher.avatarpath=?
            if (teacher.getAvatarpath()!= null) {
                sql.append(" teacher.avatarpath = :avatarpath");
            }
            if (teacher.getGender()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null)) {
                sql.append(" and");
            }
            
            //teacher.gender=? 
            if (teacher.getGender()!= null) {
                sql.append(" teacher.gender = :gender");
            }
            if (teacher.getBirth()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null)) {
                sql.append(" and");
            }
            
            //teacher.birth=? 
            if (teacher.getBirth()!= null) {
                sql.append(" teacher.birth = :birth");
            }
            if (teacher.getAddress()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null)) {
                sql.append(" and");
            }
            
            //teacher.address=? 
            if (teacher.getAddress()!= null) {
                sql.append(" teacher.address = :address");
            }
            if (teacher.getSalary()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null
                    || teacher.getAddress()!= null)) {
                sql.append(" and");
            }
            
            //teacher.salary=?
            if (teacher.getSalary()!= null) {
                sql.append(" teacher.salary = :salary");
            }
            if (teacher.getSubject()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null
                    || teacher.getAddress()!= null
                    || teacher.getSalary()!= null)) {
                sql.append(" and");
            }
            
            //teacher.subject.id=? 
            if (teacher.getSubject()!= null) {
                sql.append(" teacher.subject.id = :subjectID");
            }
            if (teacher.getAccount()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null
                    || teacher.getAddress()!= null
                    || teacher.getSalary()!= null
                    || teacher.getSubject()!= null)) {
                sql.append(" and");
            }
            
            //teacher.account.id=? 
            if (teacher.getAccount()!= null) {
                sql.append(" teacher.account.id = :accountID");
            }
            if (teacher.getIsDeleted()!= null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null
                    || teacher.getAddress()!= null
                    || teacher.getSalary()!= null
                    || teacher.getSubject()!= null
                    || teacher.getAccount()!= null)) {
                sql.append(" and");
            }
            
            //teacher.isDeleted=?
            if (teacher.getIsDeleted()!= null) {
                sql.append(" teacher.isDeleted = :isDeleted");
            }
            if (searchKey != null
                    && (teacher.getCode() != null
                    || teacher.getName() != null
                    || teacher.getPhone() != null
                    || teacher.getAvatarpath()!= null
                    || teacher.getGender()!= null
                    || teacher.getBirth()!= null
                    || teacher.getAddress()!= null
                    || teacher.getSalary()!= null
                    || teacher.getSubject()!= null
                    || teacher.getAccount()!= null
                    || teacher.getIsDeleted()!= null)) {
                sql.append(" and");
            }
        }

//        AND (teacher.code LIKE ? OR teacher.name LIKE ? OR teacher.phone LIKE ? OR teacher.avatarpath LIKE ?
//              OR teacher.gender LIKE ? OR teacher.birth LIKE ? OR teacher.address LIKE ? OR teacher.salary LIKE ?
//              OR teacher.subject.id LIKE ? OR teacher.account.id LIKE ?)
        if (searchKey != null) {
            sql.append(" (teacher.code like :searchKey");
            sql.append(" or teacher.name like :searchKey");
            sql.append(" or teacher.phone like :searchKey");
            sql.append(" or teacher.avatarpath like :searchKey");
            sql.append(" or teacher.gender like :searchKey");
            sql.append(" or teacher.birth like :searchKey");
            sql.append(" or teacher.address like :searchKey");
            sql.append(" or teacher.salary like :searchKey");
            sql.append(" or teacher.subject.id like :searchKey");
            sql.append(" or teacher.account.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<Teacher> pageable){
//        SELECT * FROM Teacher teacher
//        WHERE teacher.code=? AND teacher.name=? AND teacher.phone=? AND teacher.avatarpath=?
//              AND teacher.gender=? AND teacher.birth=? AND teacher.address=? AND teacher.salary=?
//              AND teacher.subject.id=? AND teacher.account.id=? AND teacher.isDeleted=?
//        AND (teacher.code LIKE ? OR teacher.name LIKE ? OR teacher.phone LIKE ? OR teacher.avatarpath LIKE ?
//              OR teacher.gender LIKE ? OR teacher.birth LIKE ? OR teacher.address LIKE ? OR teacher.salary LIKE ?
//              OR teacher.subject.id LIKE ? OR teacher.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //Dòng 1: SELECT * FROM Teacher teacher    
        String hql = "from Teacher teacher";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<Teacher> pageable){
//        SELECT COUNT(*) FROM Teacher teacher
//        WHERE teacher.code=? AND teacher.name=? AND teacher.phone=? AND teacher.avatarpath=?
//              AND teacher.gender=? AND teacher.birth=? AND teacher.address=? AND teacher.salary=?
//              AND teacher.subject.id=? AND teacher.account.id=? AND teacher.isDeleted=?
//        AND (teacher.code LIKE ? OR teacher.name LIKE ? OR teacher.phone LIKE ? OR teacher.avatarpath LIKE ?
//              OR teacher.gender LIKE ? OR teacher.birth LIKE ? OR teacher.address LIKE ? OR teacher.salary LIKE ?
//              OR teacher.subject.id LIKE ? OR teacher.account.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //Dòng 1: SELECT COUNT(*) FROM Teacher teacher    
        String hql = "select count(*) from Teacher teacher";
        
        //Các dòng còn lại
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<Teacher> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        Teacher teacher = pageable.getFilterModel();
        
        if(teacher != null){
            if(teacher.getCode() != null) {
                query.setParameter("code", teacher.getCode(), StringType.INSTANCE);
            }
            if(teacher.getName() != null){
                query.setParameter("name", teacher.getName(), StringNVarcharType.INSTANCE);
            }
            if(teacher.getPhone()!= null){
                query.setParameter("phone", teacher.getPhone(), StringType.INSTANCE);
            }
            if(teacher.getAvatarpath()!= null){
                query.setParameter("avatarpath", teacher.getAvatarpath(), StringNVarcharType.INSTANCE);
            }
            if(teacher.getGender()!= null){
                query.setParameter("gender", teacher.getGender(), StringNVarcharType.INSTANCE);
            }
            if(teacher.getBirth()!= null){
                query.setParameter("birth", teacher.getBirth());
            }
            if(teacher.getAddress()!= null){
                query.setParameter("address", teacher.getAddress(), StringNVarcharType.INSTANCE);
            }
            if(teacher.getSalary()!= null){
                query.setParameter("salary", teacher.getSalary());
            }
            if(teacher.getSubject()!= null){
                query.setParameter("subjectID", teacher.getSubject().getId());
            }
            if(teacher.getAccount()!= null){
                query.setParameter("accountID", teacher.getAccount().getId());
            }
            if(teacher.getIsDeleted()!= null){
                query.setParameter("isDeleted", teacher.getIsDeleted());
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
