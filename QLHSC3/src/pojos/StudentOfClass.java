package pojos;
// Generated Aug 5, 2020 10:41:27 PM by Hibernate Tools 4.3.1

import java.util.Date;
import qlhsc3.Generic;

/**
 * StudentOfClass generated by hbm2java
 */
public class StudentOfClass extends Generic implements java.io.Serializable {

    private SchoolClass schoolClass;
    private SchoolYear schoolYear;
    private Semester semester;
    private Student student;
    private Integer absence;
    private Integer absenceWithoutLeave;

    public StudentOfClass() {
    }

    public StudentOfClass(SchoolClass schoolClass, SchoolYear schoolYear, Semester semester, Student student, Integer absence, Integer absenceWithoutLeave, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.schoolClass = schoolClass;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.student = student;
        this.absence = absence;
        this.absenceWithoutLeave = absenceWithoutLeave;
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

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getAbsence() {
        return this.absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public Integer getAbsenceWithoutLeave() {
        return this.absenceWithoutLeave;
    }

    public void setAbsenceWithoutLeave(Integer absenceWithoutLeave) {
        this.absenceWithoutLeave = absenceWithoutLeave;
    }

}
