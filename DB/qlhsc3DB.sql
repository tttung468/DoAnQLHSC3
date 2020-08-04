-- 1/. Tạo database + Sử dụng database --------------------------------------------------------------------------------------------------------------------
DROP DATABASE qlhsc3DB;
CREATE DATABASE qlhsc3DB;
USE qlhsc3DB;
-- 2/. Tạo bảng + Tạo khóa chính --------------------------------------------------------------------------------------------------------------------------
-- BẢNG Role
CREATE TABLE Role
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
    	priority INT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Account
CREATE TABLE Account
(
	id BIGINT AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE,
	password VARCHAR(255),
	roleid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG VerificationToken
CREATE TABLE VerificationToken
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
	expirydate TIMESTAMP,
	accountid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Subject
CREATE TABLE Subject
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Headmaster
CREATE TABLE Headmaster
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
    name NVARCHAR(255),
	phone VARCHAR(255),
    avatarpath NVARCHAR(255),
	gender NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    salary INT,
    subjectid BIGINT,
	accountid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG HRStaff
CREATE TABLE HRStaff
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
    name NVARCHAR(255),
	phone VARCHAR(255),
    avatarpath NVARCHAR(255),
	gender NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    salary INT,
	accountid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG OfficeStaff
CREATE TABLE OfficeStaff
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
    name NVARCHAR(255),
	phone VARCHAR(255),
    avatarpath NVARCHAR(255),
	gender NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    salary INT,
	accountid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Teacher
CREATE TABLE Teacher
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
    name NVARCHAR(255),
	phone VARCHAR(255),
    avatarpath NVARCHAR(255),
	gender NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    salary INT,
    subjectid BIGINT,
    accountid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Semester
CREATE TABLE Semester
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
    factor INT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
); 
-- BẢNG SchoolYear
CREATE TABLE SchoolYear
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	lowerbound INT,
    upperbound INT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG SubjectOfSchoolYear
CREATE TABLE SubjectOfSchoolYear
(
	id BIGINT AUTO_INCREMENT,
	classhours INT,
    factor INT,
    schoolyearid BIGINT,
    subjectid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Ethnic
CREATE TABLE Ethnic
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Religion
CREATE TABLE Religion
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Nationality
CREATE TABLE Nationality
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Grade
CREATE TABLE Grade
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Class
CREATE TABLE Class
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
    gradeid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG FormTeacherAssignment
CREATE TABLE FormTeacherAssignment 
(
	id BIGINT AUTO_INCREMENT,
    size INT,
    schoolyearid BIGINT,
    formteacherid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Student
CREATE TABLE Student
(
	id BIGINT AUTO_INCREMENT,
	code VARCHAR(255) UNIQUE,
    name NVARCHAR(255),
	phone VARCHAR(255),
    identifycard VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    avatarpath NVARCHAR(255),
	gender NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    status NVARCHAR(255),
    ethnicid BIGINT,
    religionid BIGINT,
    nationalityid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG StudentOfClass
CREATE TABLE StudentOfClass 
(
	id BIGINT AUTO_INCREMENT,
    absence INT,
    absencewithoutleave INT,
    semesterid BIGINT,
    schoolyearid BIGINT,
    studentid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Observation
CREATE TABLE Observation 
(
	id BIGINT AUTO_INCREMENT,
    content INT,
    schoolyearid BIGINT,
    formteacherid BIGINT,
    studentid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG TeacherAssignment
CREATE TABLE TeacherAssignment 
(
	id BIGINT AUTO_INCREMENT,
    subjectid BIGINT,
    semesterid BIGINT,
    schoolyearid BIGINT,
    teacherid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
); 
-- BẢNG Relationship
CREATE TABLE Relationship
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Relatives
CREATE TABLE Relatives
(
	id BIGINT AUTO_INCREMENT,
    name NVARCHAR(255),
	phone VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    job NVARCHAR(255),
	birth DATE,
    address NVARCHAR(255),
    relationshipid BIGINT,
    studentid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Performance
CREATE TABLE Performance
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
    lowerbound FLOAT,
    upperbound FLOAT,
    control FLOAT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG ConductType
CREATE TABLE ConductType
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Conduct
CREATE TABLE Conduct
(
	id BIGINT AUTO_INCREMENT,
    conducttypeid BIGINT,
    semesterid BIGINT,
    schoolyearid BIGINT,
    formteacherid BIGINT,
    studentid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG ScoreType
CREATE TABLE ScoreType
(
	id BIGINT AUTO_INCREMENT,
    code VARCHAR(255) UNIQUE,
	name NVARCHAR(255),
    factor INT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Score
CREATE TABLE Score
(
	id BIGINT AUTO_INCREMENT,
    value FLOAT,
    ordinalnumber INT,
    scoretypeid BIGINT,
    subjectid BIGINT,
    semesterid BIGINT,
    schoolyearid BIGINT,
	studentid BIGINT,
    classid BIGINT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- BẢNG Regulation
CREATE TABLE Regulation
(
	id BIGINT AUTO_INCREMENT,
    	maximumscore FLOAT,
	minimumcapacity INT,
	maximumcapacity INT,
	minimumage INT,
	maximumage INT,
	isdeleted BOOLEAN,
	createddate TIMESTAMP,
	createdby NVARCHAR(255),
	modifieddate TIMESTAMP,
	modifiedby NVARCHAR(255),

	PRIMARY KEY(id)
);
-- 3/. Tạo khóa ngoại -----------------------------------------------------------------------------------------------------------------------------------
-- BẢNG Account
ALTER TABLE Account ADD 
	CONSTRAINT FK_Account_Role FOREIGN KEY(roleid) REFERENCES Role(id);
-- BẢNG VerificationToken
ALTER TABLE VerificationToken ADD 
	CONSTRAINT FK_VerificationToken_Account FOREIGN KEY(accountid) REFERENCES Account(id);
-- BẢNG Headmaster
ALTER TABLE Headmaster ADD 
	CONSTRAINT FK_Headmaster_Subject FOREIGN KEY(subjectid) REFERENCES Subject(id);
ALTER TABLE Headmaster ADD 
	CONSTRAINT FK_Headmaster_Account FOREIGN KEY(accountid) REFERENCES Account(id);
ALTER TABLE Headmaster ADD 
	CONSTRAINT CHK_Headmaster_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
-- BẢNG HRStaff
ALTER TABLE HRStaff ADD 
	CONSTRAINT FK_HRStaff_Account FOREIGN KEY(accountid) REFERENCES Account(id);
ALTER TABLE HRStaff ADD 
	CONSTRAINT CHK_HRStaff_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
-- BẢNG OfficeStaff
ALTER TABLE OfficeStaff ADD 
	CONSTRAINT FK_OfficeStaff_Account FOREIGN KEY(accountid) REFERENCES Account(id);
ALTER TABLE OfficeStaff ADD 
	CONSTRAINT CHK_OfficeStaff_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
-- BẢNG Teacher
ALTER TABLE Teacher ADD 
	CONSTRAINT FK_Teacher_Subject FOREIGN KEY(subjectid) REFERENCES Subject(id);
ALTER TABLE Teacher ADD 
	CONSTRAINT FK_Teacher_Account FOREIGN KEY(accountid) REFERENCES Account(id);
ALTER TABLE Teacher ADD 
	CONSTRAINT CHK_Teacher_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
-- BẢNG SubjectOfSchoolYear
ALTER TABLE SubjectOfSchoolYear ADD 
	CONSTRAINT FK_SubjectOfSchoolYear_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE SubjectOfSchoolYear ADD 
	CONSTRAINT FK_SubjectOfSchoolYear_Subject FOREIGN KEY(subjectid) REFERENCES Subject(id);
-- BẢNG Class
ALTER TABLE Class ADD 
	CONSTRAINT FK_Class_Grade FOREIGN KEY(gradeid) REFERENCES Grade(id);
-- BẢNG FormTeacherAssignment
ALTER TABLE FormTeacherAssignment ADD 
	CONSTRAINT FK_FormTeacherAssignment_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE FormTeacherAssignment ADD 
	CONSTRAINT FK_FormTeacherAssignment_Teacher FOREIGN KEY(formteacherid) REFERENCES Teacher(id);
ALTER TABLE FormTeacherAssignment ADD 
	CONSTRAINT FK_FormTeacherAssignment_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- BẢNG Student
ALTER TABLE Student ADD 
	CONSTRAINT FK_Student_Ethnic FOREIGN KEY(ethnicid) REFERENCES Ethnic(id);
ALTER TABLE Student ADD
	CONSTRAINT FK_Student_Religion FOREIGN KEY(religionid) REFERENCES Religion(id);
ALTER TABLE Student ADD
	CONSTRAINT FK_Student_Nationality FOREIGN KEY(nationalityid) REFERENCES Nationality(id);
ALTER TABLE Student ADD 
	CONSTRAINT CHK_Student_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
ALTER TABLE Student ADD 
	CONSTRAINT CHK_Student_status CHECK(status IN(N'Chuyển đến', N'Chuyển đi', N'Thôi học', N'Đang học'));
-- BẢNG StudentOfClass
ALTER TABLE StudentOfClass ADD 
	CONSTRAINT FK_StudentOfClass_Semester FOREIGN KEY(semesterid) REFERENCES Semester(id);
ALTER TABLE StudentOfClass ADD 
	CONSTRAINT FK_StudentOfClass_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE StudentOfClass ADD 
	CONSTRAINT FK_StudentOfClass_Student FOREIGN KEY(studentid) REFERENCES Student(id);
ALTER TABLE StudentOfClass ADD 
	CONSTRAINT FK_StudentOfClass_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- BẢNG Observation
ALTER TABLE Observation ADD 
	CONSTRAINT FK_Observation_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE Observation ADD 
	CONSTRAINT FK_Observation_Teacher FOREIGN KEY(formteacherid) REFERENCES Teacher(id);
ALTER TABLE Observation ADD 
	CONSTRAINT FK_Observation_Student FOREIGN KEY(studentid) REFERENCES Student(id);
ALTER TABLE Observation ADD 
	CONSTRAINT FK_Observation_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- BẢNG TeacherAssignment
ALTER TABLE TeacherAssignment ADD 
	CONSTRAINT FK_TeacherAssignment_Subject FOREIGN KEY(subjectid) REFERENCES Subject(id);
ALTER TABLE TeacherAssignment ADD 
	CONSTRAINT FK_TeacherAssignment_Semester FOREIGN KEY(semesterid) REFERENCES Semester(id);
ALTER TABLE TeacherAssignment ADD 
	CONSTRAINT FK_TeacherAssignment_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE TeacherAssignment ADD 
	CONSTRAINT FK_TeacherAssignment_Teacher FOREIGN KEY(teacherid) REFERENCES Teacher(id);
ALTER TABLE TeacherAssignment ADD 
	CONSTRAINT FK_TeacherAssignment_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- BẢNG Relatives
ALTER TABLE Relatives ADD 
	CONSTRAINT FK_Relatives_Relationship FOREIGN KEY(relationshipid) REFERENCES Relationship(id);
ALTER TABLE Relatives ADD 
	CONSTRAINT FK_Relatives_Student FOREIGN KEY(studentid) REFERENCES Student(id);
ALTER TABLE Relatives ADD 
	CONSTRAINT CHK_Relatives_gender CHECK(gender IN(N'Nam', N'Nữ', N'Khác'));
-- BẢNG Conduct
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_ConductType FOREIGN KEY(conducttypeid) REFERENCES ConductType(id);
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_Semester FOREIGN KEY(semesterid) REFERENCES Semester(id);
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_Teacher FOREIGN KEY(formteacherid) REFERENCES Teacher(id);
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_Student FOREIGN KEY(studentid) REFERENCES Student(id);
ALTER TABLE Conduct ADD 
	CONSTRAINT FK_Conduct_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- BẢNG Score
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_ScoreType FOREIGN KEY(scoretypeid) REFERENCES ScoreType(id);
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_Subject FOREIGN KEY(subjectid) REFERENCES Subject(id);
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_Semester FOREIGN KEY(semesterid) REFERENCES Semester(id);
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_SchoolYear FOREIGN KEY(schoolyearid) REFERENCES SchoolYear(id);
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_Student FOREIGN KEY(studentid) REFERENCES Student(id);
ALTER TABLE Score ADD 
	CONSTRAINT FK_Score_Class FOREIGN KEY(classid) REFERENCES Class(id);
-- 4/. Nhập dữ liệu -----------------------------------------------------------------------------------------------------------------------------------
DELETE FROM qlhsc3DB.Role;
-- BẢNG Role
INSERT INTO Role(code, name, priority)
VALUES('admin', N'Quản trị viên', 0);
INSERT INTO Role(code, name, priority)
VALUES('headmaster', N'Hiệu trưởng', 1);
INSERT INTO Role(code, name, priority)
VALUES('hr', N'Nhân sự', 2);
INSERT INTO Role(code, name, priority)
VALUES('office', N'Giáo vụ', 2);
INSERT INTO Role(code, name, priority)
VALUES('teacher', N'Giáo viên', 3);
-- BẢNG Account
INSERT INTO Account(username, password, roleid)
VALUES('admin@gmail.com', '123456', 0);
INSERT INTO Account(username, password, roleid)
VALUES('headmaster@gmail.com', '123456', 1);
INSERT INTO Account(username, password, roleid)
VALUES('hr@gmail.com', '123456', 2);
INSERT INTO Account(username, password, roleid)
VALUES('office@gmail.com', '123456', 3);
INSERT INTO Account(username, password, roleid)
VALUES('teacher1@gmail.com', '123456', 4);
INSERT INTO Account(username, password, roleid)
VALUES('teacher2@gmail.com', '123456', 4);
-- BẢNG Subject
INSERT INTO Subject(code, name, factor)
VALUES('math', 'Toán', 2);
INSERT INTO Subject(code, name, factor)
VALUES('literature', 'Ngữ Văn', 2);
INSERT INTO Subject(code, name, factor)
VALUES('english', 'Anh Văn', 1);
INSERT INTO Subject(code, name, factor)
VALUES('chemistry', 'Hóa Học', 1);
INSERT INTO Subject(code, name, factor)
VALUES('physics', 'Vật Lí', 1);
INSERT INTO Subject(code, name, factor)
VALUES('science', 'Sinh Học', 1);
INSERT INTO Subject(code, name, factor)
VALUES('history', 'Lịch Sử', 1);
INSERT INTO Subject(code, name, factor)
VALUES('geography', 'Địa Lí', 1);
INSERT INTO Subject(code, name, factor)
VALUES('technology', 'Công Nghệ', 1);
INSERT INTO Subject(code, name, factor)
VALUES('civic-education', 'Giáo Dục Công Dân', 1);
INSERT INTO Subject(code, name, factor)
VALUES('informatics', 'Tin Học', 1);
INSERT INTO Subject(code, name, factor)
VALUES('physical-education', 'Thể Dục', 1);
INSERT INTO Subject(code, name, factor)
VALUES('music', 'Âm Nhạc', 1);
INSERT INTO Subject(code, name, factor)
VALUES('act', 'Mỹ Thuật', 1);
-- BẢNG Headmaster
INSERT INTO Headmaster(code, name, phone, avatarpath, gender, birth, address, salary, subjectid, accountid)
VALUES('headmaster-001', 'Nguyễn Văn Hiệu Trưởng', '0111111111', null, 'Nam', '1999-01-01', 'KTX khu B, TP HCM', 30000000, null, 2);
-- BẢNG HRStaff
INSERT INTO HRStaff(code, name, phone, avatarpath, gender, birth, address, salary, accountid)
VALUES('hr-001', 'Trần Thị Nhân Sự', '0222222222', null, 'Nữ', '1999-01-01', 'Phước Hưng, Trà Vinh', 15000000, 3);
-- BẢNG OfficeStaff
INSERT INTO OfficeStaff(code, name, phone, avatarpath, gender, birth, address, salary, accountid)
VALUES('office-001', 'Trần Thị Giáo Vụ', '0333333333', null, 'Nữ', '1998-01-01', 'Tiểu Cần, Trà Vinh', 15000000, 4);
-- BẢNG Teacher
INSERT INTO Teacher(code, name, phone, avatarpath, gender, birth, address, salary, subjectid, accountid)
VALUES('teacher-001', 'Nguyễn Văn Giáo Viên 1', '0444444444', null, 'Nam', '1999-01-01', 'Quận 5, TP HCM', 12000000, null, 5);
INSERT INTO Teacher(code, name, phone, avatarpath, gender, birth, address, salary, subjectid, accountid)
VALUES('teacher-002', 'Nguyễn Văn Giáo Viên 2', '0555555555', null, 'Nam', '1999-05-05', 'Quận 3, TP HCM', 10000000, null, 5);
-- BẢNG Semester
INSERT INTO Semester(code, name, factor)
VALUES('hk1', 'Học Kỳ 1', 1);
INSERT INTO Semester(code, name, factor)
VALUES('hk2', 'Học Kỳ 2', 2);
INSERT INTO Semester(code, name, factor)
VALUES('hk3', 'Học Kỳ 3', 1);
-- BẢNG SchoolYear
INSERT INTO SchoolYear(code, lowerbound, upperbound)
VALUES('2017-2018', 2017, 2018);
INSERT INTO SchoolYear(code, lowerbound, upperbound)
VALUES('2018-2019', 2018, 2019);
INSERT INTO SchoolYear(code, lowerbound, upperbound)
VALUES('2019-2020', 2019, 2020);
-- BẢNG Ethnic
INSERT INTO Ethnic(code, name)
VALUES('kinh', 'Kinh');
INSERT INTO Ethnic(code, name)
VALUES('hoa', 'Hoa');
INSERT INTO Ethnic(code, name)
VALUES('khmer', 'Khmer');
INSERT INTO Ethnic(code, name)
VALUES('cham', 'Chăm');
-- BẢNG Religion
INSERT INTO Religion(code, name)
VALUES('buddhism', 'Phật Giáo');
INSERT INTO Religion(code, name)
VALUES('roman-catholicism', 'Thiên Chúa Giáo');
INSERT INTO Religion(code, name)
VALUES('islam', 'Hồi Giáo');
-- BẢNG Nationality
INSERT INTO Nationality(code, name)
VALUES('vietnam', 'Việt Nam');
INSERT INTO Nationality(code, name)
VALUES('china', 'Trung Quốc');
INSERT INTO Nationality(code, name)
VALUES('thailand', 'Thái Lan');
-- BẢNG Grade
INSERT INTO Grade(code, name)
VALUES('K10', 'Khối 10');
INSERT INTO Grade(code, name)
VALUES('K11', 'Khối 11');
INSERT INTO Grade(code, name)
VALUES('K12', 'Khối 12');
-- BẢNG Class
INSERT INTO Class(code, name, gradeid)
VALUES('10A1', 'Lớp 10A1', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A2', 'Lớp 10A2', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A3', 'Lớp 10A3', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A4', 'Lớp 10A4', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A5', 'Lớp 10A5', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A6', 'Lớp 10A6', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A7', 'Lớp 10A7', 1);
INSERT INTO Class(code, name, gradeid)
VALUES('10A8', 'Lớp 10A8', 1);

INSERT INTO Class(code, name, gradeid)
VALUES('11A1', 'Lớp 11A1', 2);
INSERT INTO Class(code, name, gradeid)
VALUES('11A2', 'Lớp 11A2', 2);
INSERT INTO Class(code, name, gradeid)
VALUES('11A3', 'Lớp 11A3', 2);
INSERT INTO Class(code, name, gradeid)
VALUES('11A4', 'Lớp 11A4', 2);
INSERT INTO Class(code, name, gradeid)
VALUES('11A5', 'Lớp 11A5', 2);

INSERT INTO Class(code, name, gradeid)
VALUES('12A1', 'Lớp 12A1', 3);
INSERT INTO Class(code, name, gradeid)
VALUES('12A2', 'Lớp 12A2', 3);
INSERT INTO Class(code, name, gradeid)
VALUES('12A3', 'Lớp 12A3', 3);
INSERT INTO Class(code, name, gradeid)
VALUES('12A4', 'Lớp 12A4', 3);
INSERT INTO Class(code, name, gradeid)
VALUES('12A5', 'Lớp 12A5', 3);
INSERT INTO Class(code, name, gradeid)
VALUES('12A6', 'Lớp 12A6', 3);
-- BẢNG Student
INSERT INTO Student(code, name, phone, identifycard, email, avatarpath, gender, birth, address, status, ethnicid, religionid, nationalityid)
VALUES('student-001', 'Nguyễn Văn Học Sinh 1', 0111111111, 335656898, 'student1@gmail.com', null, 'Nam', '1999-01-01', 'Ấp chợ, Tập Sơn', null, null, null, null);
-- BẢNG Relationship
INSERT INTO Relationship(code, name)
VALUES('grandfather', 'Ông');
INSERT INTO Relationship(code, name)
VALUES('grandmother', 'Bà');
INSERT INTO Relationship(code, name)
VALUES('father', 'Cha');
INSERT INTO Relationship(code, name)
VALUES('mother', 'Mẹ');
INSERT INTO Relationship(code, name)
VALUES('brother', 'Anh');
INSERT INTO Relationship(code, name)
VALUES('sister', 'Chị');
INSERT INTO Relationship(code, name)
VALUES('younger-brother', 'Em trai');
INSERT INTO Relationship(code, name)
VALUES('younger-sister', 'Em gái');
-- BẢNG Performance
INSERT INTO Performance(code, name, lowerbound, upperbound, control)
VALUES('excellent ', 'Giỏi', 8.0, 10.0, 6.5);
INSERT INTO Performance(code, name, lowerbound, upperbound, control)
VALUES('good', 'Khá', 6.5, 7.9, 5.0);
INSERT INTO Performance(code, name, lowerbound, upperbound, control)
VALUES('average', 'Trung Bình', 5.0, 6.4, 3.5);
INSERT INTO Performance(code, name, lowerbound, upperbound, control)
VALUES('below-average', 'Yếu', 3.5, 4.9, 2.0);
INSERT INTO Performance(code, name, lowerbound, upperbound, control)
VALUES('weak', 'Kém', 0.0, 3.4, 0.0);
-- BẢNG ConductType
INSERT INTO ConductType(code, name)
VALUES('excellent ', 'Giỏi');
INSERT INTO ConductType(code, name)
VALUES('good', 'Khá');
INSERT INTO ConductType(code, name)
VALUES('average', 'Trung Bình');
INSERT INTO ConductType(code, name)
VALUES('below-average', 'Yếu');
-- BẢNG ScoreType
INSERT INTO ScoreType(code, name, factor)
VALUES('mouth-test', 'Kiểm tra miệng', 1);
INSERT INTO ScoreType(code, name, factor)
VALUES('minute-test', 'Kiểm tra 15 phút', 1);
INSERT INTO ScoreType(code, name, factor)
VALUES('hour-test', 'Kiểm tra 1 tiết', 2);
INSERT INTO ScoreType(code, name, factor)
VALUES('semi-test', 'Kiểm giữa kỳ', 3);
INSERT INTO ScoreType(code, name, factor)
VALUES('final-test', 'Kiểm cuối kỳ', 4);
-- 5/. Update lại dữ liệu -----------------------------------------------------------------------------------------------------------------------------
-- 6/. Xem data ---------------------------------------------------------------------------------------------------------------------------------------
SELECT * FROM qlhsc3DB.Role;
SELECT * FROM qlhsc3DB.Account;
SELECT * FROM qlhsc3DB.VerificationToken;
SELECT * FROM qlhsc3DB.Subject;
SELECT * FROM qlhsc3DB.Headmaster;
SELECT * FROM qlhsc3DB.HRStaff;
SELECT * FROM qlhsc3DB.OfficeStaff;
SELECT * FROM qlhsc3DB.Teacher;
SELECT * FROM qlhsc3DB.Semester;
SELECT * FROM qlhsc3DB.SchoolYear;
SELECT * FROM qlhsc3DB.Ethnic;
SELECT * FROM qlhsc3DB.Religion;
SELECT * FROM qlhsc3DB.Nationality;
SELECT * FROM qlhsc3DB.Grade;
SELECT * FROM qlhsc3DB.Class;
SELECT * FROM qlhsc3DB.FormTeacherAssignment;
SELECT * FROM qlhsc3DB.Student;
SELECT * FROM qlhsc3DB.StudentOfClass;
SELECT * FROM qlhsc3DB.Observation;
SELECT * FROM qlhsc3DB.TeacherAssignment;
SELECT * FROM qlhsc3DB.Relationship;
SELECT * FROM qlhsc3DB.Relatives;
SELECT * FROM qlhsc3DB.Performance;
SELECT * FROM qlhsc3DB.Conduct;
SELECT * FROM qlhsc3DB.ConductOfStudent;
SELECT * FROM qlhsc3DB.ScoreType;
SELECT * FROM qlhsc3DB.Score;

DROP TABLE qlhsc3DB.Role;
DROP TABLE qlhsc3DB.Account;
DROP TABLE qlhsc3DB.VerificationToken;
DROP TABLE qlhsc3DB.Subject;
DROP TABLE qlhsc3DB.Headmaster;
DROP TABLE qlhsc3DB.HRStaff;
DROP TABLE qlhsc3DB.OfficeStaff;
DROP TABLE qlhsc3DB.Teacher;
DROP TABLE qlhsc3DB.Semester;
DROP TABLE qlhsc3DB.SchoolYear;
DROP TABLE qlhsc3DB.Ethnic;
DROP TABLE qlhsc3DB.Religion;
DROP TABLE qlhsc3DB.Nationality;
DROP TABLE qlhsc3DB.Grade;
DROP TABLE qlhsc3DB.Class;
DROP TABLE qlhsc3DB.FormTeacherAssignment;
DROP TABLE qlhsc3DB.Student;
DROP TABLE qlhsc3DB.StudentOfClass;
DROP TABLE qlhsc3DB.Observation;
DROP TABLE qlhsc3DB.TeacherAssignment;
DROP TABLE qlhsc3DB.Relationship;
DROP TABLE qlhsc3DB.Relatives;
DROP TABLE qlhsc3DB.Performance;
DROP TABLE qlhsc3DB.Conduct;
DROP TABLE qlhsc3DB.ConductOfStudent;
DROP TABLE qlhsc3DB.ScoreType;
DROP TABLE qlhsc3DB.Score;
