package com.toanhuuvuong.model;

import java.util.Date;

public class FormTeacherAssignment extends Generic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private SchoolClass schoolClass;
	private SchoolYear schoolYear;
	private Teacher teacher;
	private Integer capacity;

	public FormTeacherAssignment() {}
	public FormTeacherAssignment(SchoolClass schoolClass, SchoolYear schoolYear, Teacher teacher, Integer capacity,
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.schoolClass = schoolClass;
		this.schoolYear = schoolYear;
		this.teacher = teacher;
		this.capacity = capacity;
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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

}
