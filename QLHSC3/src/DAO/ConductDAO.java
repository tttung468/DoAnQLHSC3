/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import pagination.Pageable;
import pojos.Conduct;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class ConductDAO extends AbstractHibernateDAO<Conduct>{
    public ConductDAO(){
        setClazz(Conduct.class);
    }
    
    @Override
    public String generateHQL(Pageable<Conduct> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        Conduct conduct = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
        
//        WHERE conduct.conductType.id=? AND conduct.semester.id=?
//              AND conduct.schoolYear.id=? AND conduct.teacher.id=?
//              AND conduct.student.id=? AND conduct.schoolClass.id=? AND conduct.isDeleted=?
        if (conduct != null || searchKey != null) {
            sql.append(" where");
        }
        if (conduct != null) {
            // conduct.conductType.id=? 
            if(conduct.getConductType()!= null){
                sql.append(" conduct.conductType.id = :conductTypeID");
            }
            if(conduct.getSemester()!= null
                    && conduct.getConductType()!= null){
                sql.append(" and");
            }
            
            // conduct.semester.id=?
            if(conduct.getSemester()!= null){
                sql.append(" conduct.semester.id = :semesterID");
            }
            if(conduct.getSchoolYear()!= null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null)){
                sql.append(" and");
            }
            
            // conduct.schoolYear.id=? 
            if(conduct.getSchoolYear()!= null){
                sql.append(" conduct.schoolYear.id = :schoolYearID");
            }
            if(conduct.getTeacher()!= null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null
                    || conduct.getSchoolYear()!= null)){
                sql.append(" and");
            }
            
            // conduct.teacher.id=?
            if(conduct.getTeacher()!= null){
                sql.append(" conduct.teacher.id = :teacherID");
            }
            if(conduct.getStudent()!= null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null
                    || conduct.getSchoolYear()!= null
                    || conduct.getTeacher()!= null)){
                sql.append(" and");
            }
            
            // conduct.student.id=? 
            if(conduct.getStudent()!= null){
                sql.append(" conduct.student.id = :studentID");
            }
            if(conduct.getSchoolClass()!= null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null
                    || conduct.getSchoolYear()!= null
                    || conduct.getTeacher()!= null
                    || conduct.getStudent()!= null)){
                sql.append(" and");
            }
            
            // conduct.schoolClass.id=? 
            if(conduct.getSchoolClass()!= null){
                sql.append(" conduct.schoolClass.id = :schoolClassID");
            }
            if(conduct.getIsDeleted()!= null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null
                    || conduct.getSchoolYear()!= null
                    || conduct.getTeacher()!= null
                    || conduct.getStudent()!= null
                    || conduct.getSchoolClass()!= null)){
                sql.append(" and");
            }
            
            // conduct.isDeleted=?
            if(conduct.getIsDeleted()!= null){
                sql.append(" conduct.isDeleted = :isDeleted");
            }
            if(searchKey != null
                    && (conduct.getConductType()!= null
                    || conduct.getSemester()!= null
                    || conduct.getSchoolYear()!= null
                    || conduct.getTeacher()!= null
                    || conduct.getStudent()!= null
                    || conduct.getSchoolClass()!= null
                    || conduct.getIsDeleted()!= null)){
                sql.append(" and");
            }
        }
        
//        AND (conduct.conductType.id LIKE ? OR conduct.semester.id LIKE ?
//              OR conduct.schoolYear.id LIKE ? OR conduct.teacher.id LIKE ? 
//              OR conduct.student.id LIKE ? OR conduct.schoolClass.id LIKE ? )
        if (searchKey != null) {
            sql.append(" (conduct.conductType.id like :searchKey");
            sql.append(" or conduct.semester.id like :searchKey");
            sql.append(" or conduct.schoolYear.id like :searchKey");
            sql.append(" or conduct.teacher.id like :searchKey");
            sql.append(" or conduct.student.id like :searchKey");
            sql.append(" or conduct.schoolClass.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<Conduct> pageable){
//        SELECT * FROM Conduct conduct 
//        WHERE conduct.conductType.id=? AND conduct.semester.id=?
//              AND conduct.schoolYear.id=? AND conduct.teacher.id=?
//              AND conduct.student.id=? AND conduct.schoolClass.id=? AND conduct.isDeleted=?
//        AND (conduct.conductType.id LIKE ? OR conduct.semester.id LIKE ?
//              OR conduct.schoolYear.id LIKE ? OR conduct.teacher.id LIKE ? 
//              OR conduct.student.id LIKE ? OR conduct.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM Conduct conduct    
        String hql = "from Conduct conduct";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<Conduct> pageable){
//        SELECT COUNT(*) FROM Conduct conduct 
//        WHERE conduct.conductType.id=? AND conduct.semester.id=?
//              AND conduct.schoolYear.id=? AND conduct.teacher.id=?
//              AND conduct.student.id=? AND conduct.schoolClass.id=? AND conduct.isDeleted=?
//        AND (conduct.conductType.id LIKE ? OR conduct.semester.id LIKE ?
//              OR conduct.schoolYear.id LIKE ? OR conduct.teacher.id LIKE ? 
//              OR conduct.student.id LIKE ? OR conduct.schoolClass.id LIKE ? )
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM Conduct conduct    
        String hql = "select count(*) from Conduct conduct";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<Conduct> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        Conduct conduct = pageable.getFilterModel();
        
        if (conduct != null) {
            if(conduct.getConductType()!= null){
                query.setParameter("conductTypeID", conduct.getConductType().getId());
            }
            if(conduct.getSemester()!= null){
                query.setParameter("semesterID", conduct.getSemester().getId());
            }
            if(conduct.getSchoolYear()!= null){
                query.setParameter("schoolYearID", conduct.getSchoolYear().getId());
            }
            if(conduct.getTeacher()!= null){
                query.setParameter("teacherID", conduct.getTeacher().getId());
            }
            if(conduct.getStudent()!= null){
                query.setParameter("studentID", conduct.getStudent().getId());
            }
            if(conduct.getSchoolClass()!= null){
                query.setParameter("schoolClassID", conduct.getSchoolClass().getId());
            }
            if(conduct.getIsDeleted() != null){
                query.setParameter("isDeleted", conduct.getIsDeleted());
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
