package com.toanhuuvuong.model;

import java.util.Date;

public class Observation extends Generic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private SchoolClass schoolClass;
	private SchoolYear schoolYear;
	private Student student;
	private Teacher teacher;
	private String content;

	public Observation() {
	}

	public Observation(SchoolClass schoolclass, SchoolYear schoolyear, Student student, Teacher teacher, String content,
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.schoolClass = schoolclass;
		this.schoolYear = schoolyear;
		this.student = student;
		this.teacher = teacher;
		this.content = content;
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
