package com.toanhuuvuong.model;

import java.util.Date;

public class Conduct extends Generic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ConductType conductType;
	private SchoolClass schoolClass;
	private SchoolYear schoolYear;
	private Semester semester;
	private Student student;
	private Teacher teacher;

	public Conduct() {}

	public Conduct(ConductType conductType, SchoolClass schoolClass, SchoolYear schoolYear, Semester semester,
			Student student, Teacher teacher, Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate,
			String modifiedBy) {
    	super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);

		this.conductType = conductType;
		this.schoolClass = schoolClass;
		this.schoolYear = schoolYear;
		this.semester = semester;
		this.student = student;
		this.teacher = teacher;
	}

	public ConductType getConductType() {
		return this.conductType;
	}

	public void setConductType(ConductType conductType) {
		this.conductType = conductType;
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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
