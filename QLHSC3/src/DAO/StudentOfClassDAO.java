/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import pagination.Pageable;
import pojos.StudentOfClass;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 */
public class StudentOfClassDAO extends AbstractHibernateDAO<StudentOfClass>{
    public StudentOfClassDAO(){
        setClazz(StudentOfClass.class);
    }
    
    @Override
    public String generateHQL(Pageable<StudentOfClass> pageable){
        Sorter sorter = pageable.getSorter();
        String searchKey = pageable.getSearchKey();
        StudentOfClass studentOfClass = pageable.getFilterModel();
        StringBuilder sql = new StringBuilder();
        
//      WHERE studentOfClass.absence=? AND studentOfClass.absenceWithoutLeave=? 
//              AND studentOfClass.semester.id=? AND studentOfClass.schoolYear.id=?
//              AND studentOfClass.student.id=? AND studentOfClass.schoolClass.id=? 
//              AND studentOfClass.isDeleted=?
        if (studentOfClass != null || searchKey != null) {
            sql.append(" where");
        }
        if(studentOfClass != null){
            // studentOfClass.absence=? 
            if(studentOfClass.getAbsence()!= null){
                sql.append(" studentOfClass.absence=:absence");
            }
            if(studentOfClass.getAbsenceWithoutLeave()!= null 
                    && studentOfClass.getAbsence()!= null){
                sql.append(" and");
            }
            
            // studentOfClass.absenceWithoutLeave=? 
            if(studentOfClass.getAbsenceWithoutLeave()!= null){
                sql.append(" studentOfClass.absenceWithoutLeave=:absenceWithoutLeave");
            }
            if(studentOfClass.getSemester()!= null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null)){
                sql.append(" and");
            }
            
            // studentOfClass.semester.id=? 
            if(studentOfClass.getSemester()!= null){
                sql.append(" studentOfClass.semester.id=:semesterID");
            }
            if(studentOfClass.getSchoolYear()!= null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null
                    || studentOfClass.getSemester()!= null )){
                sql.append(" and");
            }
            
            // studentOfClass.schoolYear.id=?
            if(studentOfClass.getSchoolYear()!= null){
                sql.append(" studentOfClass.schoolYear.id=:schoolYearID");
            }
            if(studentOfClass.getStudent()!= null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null
                    || studentOfClass.getSemester()!= null
                    || studentOfClass.getSchoolYear()!= null )){
                sql.append(" and");
            }
            
            // studentOfClass.student.id=? 
            if(studentOfClass.getStudent()!= null){
                sql.append(" studentOfClass.student.id=:studentID");
            }
            if(studentOfClass.getSchoolClass()!= null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null
                    || studentOfClass.getSemester()!= null
                    || studentOfClass.getSchoolYear()!= null
                    || studentOfClass.getStudent()!= null )){
                sql.append(" and");
            }
            
            // studentOfClass.schoolClass.id=? 
            if(studentOfClass.getSchoolClass()!= null){
                sql.append(" studentOfClass.schoolClass.id=:schoolClassID");
            }
            if(studentOfClass.getIsDeleted()!= null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null
                    || studentOfClass.getSemester()!= null
                    || studentOfClass.getSchoolYear()!= null
                    || studentOfClass.getStudent()!= null
                    || studentOfClass.getSchoolClass()!= null )){
                sql.append(" and");
            }
            
            // studentOfClass.isDeleted=?
            if(studentOfClass.getIsDeleted()!= null){
                sql.append(" studentOfClass.isDeleted=:isDeleted");
            }
            if(searchKey != null 
                    && (studentOfClass.getAbsence()!= null
                    || studentOfClass.getAbsenceWithoutLeave()!= null
                    || studentOfClass.getSemester()!= null
                    || studentOfClass.getSchoolYear()!= null
                    || studentOfClass.getStudent()!= null
                    || studentOfClass.getSchoolClass()!= null 
                    || studentOfClass.getIsDeleted()!= null )){
                sql.append(" and");
            }
        }

//        AND (studentOfClass.absence LIKE ? OR studentOfClass.absenceWithoutLeave LIKE ? 
//              OR studentOfClass.semester.id LIKE ? OR studentOfClass.schoolYear.id LIKE ?
//              OR studentOfClass.student.id LIKE ? OR studentOfClass.schoolClass.id LIKE ?)    
        if(searchKey != null){
            sql.append(" (studentOfClass.absence like :searchKey");
            sql.append(" or studentOfClass.absenceWithoutLeave like :searchKey");
            sql.append(" or studentOfClass.semester.id like :searchKey");
            sql.append(" or studentOfClass.schoolYear.id like :searchKey");
            sql.append(" or studentOfClass.student.id like :searchKey");
            sql.append(" or studentOfClass.schoolClass.id like :searchKey)");
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
    public String generateHQLForFind(Pageable<StudentOfClass> pageable){
//        SELECT * FROM StudentOfClass studentOfClass
//        WHERE studentOfClass.absence=? AND studentOfClass.absenceWithoutLeave=? 
//              AND studentOfClass.semester.id=? AND studentOfClass.schoolYear.id=?
//              AND studentOfClass.student.id=? AND studentOfClass.schoolClass.id=? 
//              AND studentOfClass.isDeleted=?
//        AND (studentOfClass.absence LIKE ? OR studentOfClass.absenceWithoutLeave LIKE ? 
//              OR studentOfClass.semester.id LIKE ? OR studentOfClass.schoolYear.id LIKE ?
//              OR studentOfClass.student.id LIKE ? OR studentOfClass.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT * FROM StudentOfClass studentOfClass
        String hql = "from StudentOfClass studentOfClass";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public String generateHQLForCount(Pageable<StudentOfClass> pageable){
//        SELECT COUNT(*) FROM StudentOfClass studentOfClass
//        WHERE studentOfClass.absence=? AND studentOfClass.absenceWithoutLeave=? 
//              AND studentOfClass.semester.id=? AND studentOfClass.schoolYear.id=?
//              AND studentOfClass.student.id=? AND studentOfClass.schoolClass.id=? 
//              AND studentOfClass.isDeleted=?
//        AND (studentOfClass.absence LIKE ? OR studentOfClass.absenceWithoutLeave LIKE ? 
//              OR studentOfClass.semester.id LIKE ? OR studentOfClass.schoolYear.id LIKE ?
//              OR studentOfClass.student.id LIKE ? OR studentOfClass.schoolClass.id LIKE ?)
//        ORDER BY ? ?
//        LIMIT ?, ?

        //SELECT COUNT(*) FROM StudentOfClass studentOfClass
        String hql = " select count(*) from StudentOfClass studentOfClass";
        
        //Cac dong con lai
        hql += generateHQL(pageable);
        
        //test
        //System.out.println("\n" + hql);
        
        return hql;
    }
    
    @Override
    public Query setValueForHQL(Pageable<StudentOfClass> pageable, Session session, String hql){
        Query query = session.createQuery(hql);
        StudentOfClass studentOfClass = pageable.getFilterModel();
        
        if(studentOfClass != null){
            if(studentOfClass.getAbsence()!= null){
                 query.setParameter("absence", studentOfClass.getAbsence());
            }
            if(studentOfClass.getAbsenceWithoutLeave()!= null){
                 query.setParameter("absenceWithoutLeave", studentOfClass.getAbsenceWithoutLeave());
            }
            if(studentOfClass.getSemester()!= null){
                 query.setParameter("semesterID", studentOfClass.getSemester().getId());
            }
            if(studentOfClass.getSchoolYear()!= null){
                 query.setParameter("schoolYearID", studentOfClass.getSchoolYear().getId());
            }
            if(studentOfClass.getStudent()!= null){
                 query.setParameter("studentID", studentOfClass.getStudent().getId());
            }
            if(studentOfClass.getSchoolClass()!= null){
                 query.setParameter("schoolClassID", studentOfClass.getSchoolClass().getId());
            }
            if(studentOfClass.getIsDeleted()!= null){
                 query.setParameter("isDeleted", studentOfClass.getIsDeleted());
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
