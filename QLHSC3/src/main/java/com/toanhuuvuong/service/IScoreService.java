package com.toanhuuvuong.service;

import java.util.List;

import com.toanhuuvuong.model.Performance;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.Subject;

public interface IScoreService 
{
	public List<Score> findByScoreTypeOfStudent(ScoreType scoreType, Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass, Subject subject);
	public Float calculateSubjectAvg(Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass, Subject subject);
	public Float calculateAvg(Student student, Semester semester, 
			SchoolYear schoolYear, SchoolClass schoolClass);
	public Integer countByPerformanceOfSchoolClass(Performance performance, Semester semester, SchoolYear schoolYear, SchoolClass schoolClass);
	public List<Score> generateScoreGroupByStudentFrom(List<Score> scores, Score filterModel);
	public List<Score> generateScoreGroupBySubjectFrom(List<Score> scores, Score filterModel);
	public List<Score> generateScoreGroupBySchoolClassFrom(List<Score> scores, Score filterModel);
	public String generateScoreFrom(List<Score> scores);
}
