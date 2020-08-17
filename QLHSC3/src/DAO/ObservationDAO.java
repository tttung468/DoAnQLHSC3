/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringNVarcharType;
import pagination.Pageable;
import pojos.Observation;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class ObservationDAO extends AbstractHibernateDAO<Observation>{
    public ObservationDAO(){
        setClazz(Observation.class);
    }
    
    @Override
    public String generateHQL(Pageable<Observation> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Observation observation = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
      
//      WHERE observation.content=? AND observation.schoolYear.id=?
//              AND observation.teacher.id=? AND observation.student.id=?
//              AND observation.schoolClass.id=? AND observation.isDeleted=?
        if (observation != null || searchKey != null) {
            sql.append(" where");
        }
        if (observation != null) {
            //observation.content=? 
            if(observation.getContent() != null){
                sql.append(" observation.content = :content");
            }
            if(observation.getSchoolYear() != null
                    && observation.getContent() != null){
                sql.append(" and");
            }
            
            //observation.schoolYear.id=?
            if(observation.getSchoolYear()!= null){
                sql.append(" observation.schoolYear.id = :schoolYearID");
            }
            if(observation.getTeacher()!= null
                    && (observation.getContent() != null
                    || observation.getSchoolYear() != null)){
                sql.append(" and");
            }
            
            //observation.teacher.id=? 
            if(observation.getTeacher()!= null){
                sql.append(" observation.teacher.id = :teacherID");
            }
            if(observation.getStudent()!= null
                    && (observation.getContent() != null
                    || observation.getSchoolYear() != null
                    || observation.getTeacher()!= null)){
                sql.append(" and");
            }
            
            //observation.student.id=?
            if(observation.getStudent()!= null){
                sql.append(" observation.student.id = :studentID");
            }
            if(observation.getSchoolClass()!= null
                    && (observation.getContent() != null
                    || observation.getSchoolYear() != null
                    || observation.getTeacher()!= null
                    || observation.getStudent()!= null)){
                sql.append(" and");
            }
            
            //observation.schoolClass.id=?
            if(observation.getSchoolClass()!= null){
                sql.append(" observation.schoolClass.id = :schoolClassID");
            }
            if(observation.getIsDeleted()!= null
                    && (observation.getContent() != null
                    || observation.getSchoolYear() != null
                    || observation.getTeacher()!= null
                    || observation.getStudent()!= null
                    || observation.getSchoolClass()!= null)){
                sql.append(" and");
            }
            
            //observation.isDeleted=?
            if(observation.getIsDeleted()!= null){
                sql.append(" observation.isDeleted = :isDeleted");
            }
            if(searchKey != null
                    && (observation.getContent() != null
                    || observation.getSchoolYear() != null
                    || observation.getTeacher()!= null
                    || observation.getStudent()!= null
                    || observation.getSchoolClass()!= null
                    || observation.getIsDeleted()!= null)){
                sql.append(" and");
            }
        }

//      AND (observation.content LIKE ? OR observation.schoolYear.id LIKE ?
//              OR observation.teacher.id LIKE ? OR observation.student.id LIKE ? 
//              OR observation.schoolClass.id LIKE ?)
        if (searchKey != null) {
            sql.append(" (observation.content like :searchKey");
            sql.append(" or observation.schoolYear.id like :searchKey");
            sql.append(" or observation.teacher.id like :searchKey");
            sql.append(" or observation.student.id like :searchKey");
            sql.append(" or observation.schoolClass.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<Observation> pageable){
//        SELECT * FROM Observation observation 
//        WHERE observation.content=? AND observation.schoolYear.id=?
//              AND observation.teacher.id=? AND observation.student.id=?
//              AND observation.schoolClass.id=? AND observation.isDeleted=?
//        AND (observation.content LIKE ? OR observation.schoolYear.id LIKE ?
//              OR observation.teacher.id LIKE ? OR observation.student.id LIKE ? 
//              OR observation.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM Observation observation    
        String hql = "from Observation observation";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<Observation> pageable){
//        SELECT COUNT(*) FROM Observation observation 
//        WHERE observation.content=? AND observation.schoolYear.id=?
//              AND observation.teacher.id=? AND observation.student.id=?
//              AND observation.schoolClass.id=? AND observation.isDeleted=?
//        AND (observation.content LIKE ? OR observation.schoolYear.id LIKE ?
//              OR observation.teacher.id LIKE ? OR observation.student.id LIKE ? 
//              OR observation.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM Observation observation    
        String hql = "select count(*) from Observation observation";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<Observation> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        Observation observation = pageable.getFilterModel();
        
        if (observation != null) {
            if(observation.getContent()!= null){
                query.setParameter("content", observation.getContent(), StringNVarcharType.INSTANCE);
            }
            if(observation.getSchoolYear()!= null){
                query.setParameter("schoolYearID", observation.getSchoolYear().getId());
            }
            if(observation.getTeacher()!= null){
                query.setParameter("teacherID", observation.getTeacher().getId());
            }
            if(observation.getStudent()!= null){
                query.setParameter("studentID", observation.getStudent().getId());
            }
            if(observation.getSchoolClass()!= null){
                query.setParameter("schoolClassID", observation.getSchoolClass().getId());
            }
            if(observation.getIsDeleted() != null){
                query.setParameter("isDeleted", observation.getIsDeleted());
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
