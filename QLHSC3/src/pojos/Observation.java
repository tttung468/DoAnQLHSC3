package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * Observation generated by hbm2java
 */
public class Observation extends Generic implements java.io.Serializable {

    private SchoolClass schoolClass;
    private SchoolYear schoolYear;
    private Student student;
    private Teacher teacher;
    private String content;

    public Observation() {
    }

    public Observation(SchoolClass schoolclass, SchoolYear schoolyear, Student student, Teacher teacher, String content, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.schoolClass = schoolclass;
        this.schoolYear = schoolyear;
        this.student = student;
        this.teacher = teacher;
        this.content = content;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public SchoolClass getSchoolClass() {
        return this.schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolYear getSchoolYear() {
        return this.schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
