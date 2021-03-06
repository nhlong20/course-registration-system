-- Database: coursesDB
-- Author: Nguyen_Hoang_Long
DROP TABLE IF EXISTS public.student CASCADE;
DROP TABLE IF EXISTS public.moderator CASCADE;
DROP TABLE IF EXISTS public.subject CASCADE;
DROP TABLE IF EXISTS public.semester CASCADE;
DROP TABLE IF EXISTS public.class CASCADE;
DROP TABLE IF EXISTS public.courseregistrationsession CASCADE;
DROP TABLE IF EXISTS public.course CASCADE;
DROP TABLE IF EXISTS public.shift CASCADE;
DROP TABLE IF EXISTS public.course_student CASCADE;
DROP TABLE IF EXISTS public.teacher CASCADE;
DROP TABLE IF EXISTS public.account CASCADE;

BEGIN;
CREATE TABLE public.account (
                                account_id SERIAL PRIMARY KEY,
                                acc_type varchar(10) NOT NULL,
                                username varchar(30) UNIQUE NOT NULL,
                                passwd varchar(100) NOT NULL,
                                created_at timestamp without time zone default CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE public.moderator (
                                  moderator_id varchar(10) PRIMARY KEY,
                                  fullname varchar(50) NOT NULL,
                                  gender varchar(3) NOT NULL CHECK (gender in ('Nam', 'Nữ')),
                                  dob date NOT NULL CHECK (dob > '1900-01-01'),
                                  phone varchar(20),
                                  mod_address  varchar(100),
                                  account_id integer NOT NULL
);

CREATE TABLE public.subject (
                                subject_id varchar(10) PRIMARY KEY,
                                subject_name varchar(100) NOT NULL,
                                credits integer NOT NULL CHECK (credits > 0)
);


CREATE TABLE public.semester (
                                 semester_id SERIAL PRIMARY KEY,
                                 sem_name varchar(3) NOT NULL,
                                 sem_year integer NOT NULL,
                                 is_current_sem boolean NOT NULL default false,
                                 startdate date NOT NULL CHECK (startdate > '1900-01-01'),
                                 enddate date NOT NULL CHECK (enddate > startdate)

);
CREATE TABLE public.class (
                              class_code varchar(10) PRIMARY KEY,
                              class_year integer NOT NULL
);

CREATE TABLE public.student (
                                id SERIAL PRIMARY KEY,
                                student_id VARCHAR(20) UNIQUE NOT NULL,
                                fullname varchar(100) NOT NULL,
                                gender varchar(10) NOT NULL CHECK (gender in ('Nam', 'Nữ')),
                                dob date NOT NULL CHECK (dob > '1900-01-01'),
                                stu_address varchar(100),
                                class_code varchar(10),
                                account_id integer
);

CREATE TABLE public.courseregistrationsession (
                                                  registration_session_id SERIAL PRIMARY KEY,
                                                  start_date date NOT NULL CHECK (start_date > '1900-01-01'),
                                                  end_date date NOT NULL CHECK (end_date >= start_date),
                                                  semester_id integer NOT NULL
);

CREATE TABLE public.shift (
                              shift_id integer PRIMARY KEY,
                              start_at TIME NOT NULL,
                              end_at TIME NOT NULL
);

CREATE TABLE public.teacher (
                                teacher_id SERIAL PRIMARY KEY,
                                fullname varchar(50) NOT NULL,
                                gender varchar(10) NOT NULL CHECK (gender in ('Nam', 'Nữ')),
                                account_id integer NOT NULL
);

CREATE TABLE public.course (
                               course_id varchar(20) PRIMARY KEY,
                               subject_id varchar(10) NOT NULL,
                               teacher_id integer NOT NULL,
                               day_of_week varchar(10) NOT NULL CHECK (day_of_week in ('Thứ 2','Thứ 3','Thứ 4','Thứ 5','Thứ 6','Thứ 7','Chủ Nhật')),
                               shift_id integer NOT NULL,
                               semester_id integer NOT NULL,
                               room varchar(10),
                               maximum_slots integer NOT NULL CHECK (maximum_slots > 0)
);


CREATE TABLE public.course_student (
                                       id SERIAL PRIMARY KEY,
                                       student_id integer NOT NULL,
                                       course_id varchar(20) NOT NULL,
                                       created_at timestamp without time zone default CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.student
    ADD CONSTRAINT FK_student__class_code FOREIGN KEY (class_code) REFERENCES public.class(class_code);
ALTER TABLE public.student
    ADD CONSTRAINT FK_student__account_id FOREIGN KEY (account_id) REFERENCES public.account(account_id);
ALTER TABLE public.moderator
    ADD CONSTRAINT FK_moderator__account_id FOREIGN KEY (account_id) REFERENCES public.account(account_id);
ALTER TABLE public.courseregistrationsession
    ADD CONSTRAINT FK_courseregistrationsession__semester_id FOREIGN KEY (semester_id) REFERENCES public.semester(semester_id);

ALTER TABLE public.course
    ADD CONSTRAINT FK_course__subject_id FOREIGN KEY (subject_id) REFERENCES public.subject(subject_id);
ALTER TABLE public.course
    ADD CONSTRAINT FK_course__teacher_id FOREIGN KEY (teacher_id) REFERENCES public.teacher(teacher_id);
ALTER TABLE public.course
    ADD CONSTRAINT FK_course__shift_id FOREIGN KEY (shift_id) REFERENCES public.shift(shift_id);
ALTER TABLE public.course
    ADD CONSTRAINT FK_course__semester_id FOREIGN KEY (semester_id) REFERENCES public.semester(semester_id);

ALTER TABLE public.course_student
    ADD CONSTRAINT FK_course_student__student_id FOREIGN KEY (student_id) REFERENCES public.student(id);
ALTER TABLE public.course_student
    ADD CONSTRAINT FK_course_student__course_id FOREIGN KEY (course_id) REFERENCES public.course(course_id);

ALTER TABLE public.teacher
    ADD CONSTRAINT FK_teacher__account_id FOREIGN KEY (account_id) REFERENCES public.account(account_id);

COMMIT;

INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120449', '18120449', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120460', '18120460', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120461', '18120461', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Moderator', 'mod001', 'mod001', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV001', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV002', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV003', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV004', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV005', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Moderator', 'mod002', 'mod002', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120462', '18120462', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120463', '18120463', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120464', '18120464', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120500', '18120500', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120020', '18120020', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120023', '18120023', default);

INSERT INTO moderator(moderator_id, fullname, gender,dob, phone, mod_address,  account_id)
VALUES ('mod001', 'Văn Chí Nam', 'Nam', '1984-07-06', '0942020222', 'Hồ Chí Minh', 4);
INSERT INTO moderator(moderator_id, fullname, gender,dob, phone, mod_address,  account_id)
VALUES ('mod002', 'Thái Lê Vinh', 'Nam', '1987-07-06', '0564859274', 'Hồ Chí Minh', 10);

INSERT INTO public.subject VALUES ('OOP', 'Lập trình hướng đối tượng', 4);
INSERT INTO public.subject VALUES ('CTDLGT', 'Cấu trúc dữ liệu và giải thuật', 4);
INSERT INTO public.subject VALUES ('CSDL', 'Cơ sở dữ liệu', 4);
INSERT INTO public.subject VALUES ('HDH', 'Hệ điều hành', 4);
INSERT INTO public.subject VALUES ('MMT', 'Mạng máy tính', 4);
INSERT INTO public.subject VALUES ('DAAS', 'Thiết kế và phân tích phần mềm', 4);
INSERT INTO public.subject VALUES ('NMHM', 'Nhập môn học máy', 4);
INSERT INTO public.subject VALUES ('XLA', 'Xử lý ảnh', 4);
INSERT INTO public.subject VALUES ('LTUDW', 'Lập trình ứng dụng Web', 4);

INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK1', 2017, '2017-08-20', '2018-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2017, '2018-02-03', '2018-06-28');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default,'HK1', 2018, '2018-08-20', '2019-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2018, '2019-02-03', '2019-06-28');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK1', 2019, '2019-08-20', '2020-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2019, '2020-02-03', '2020-06-28');

INSERT INTO courseregistrationsession(registration_session_id,start_date, end_date, semester_id)
VALUES (default, '2017-08-24', '2017-08-28', 1);
INSERT INTO courseregistrationsession(registration_session_id,start_date, end_date, semester_id)
VALUES (default, '2018-08-24', '2018-08-28', 2);
INSERT INTO courseregistrationsession(registration_session_id,start_date, end_date, semester_id)
VALUES (default, '2019-08-24', '2019-08-28', 3);

INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT4');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT5');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CNTN');
INSERT INTO class(class_year, class_code) VALUES (2019, '19CTT1');

INSERT INTO shift(shift_id, start_at, end_at) VALUES (1, '07:30:00', '09:30:00');
INSERT INTO shift(shift_id, start_at, end_at) VALUES (2, '09:30:00', '11:30:00');
INSERT INTO shift(shift_id, start_at, end_at) VALUES (3, '13:30:00', '15:30:00');
INSERT INTO shift(shift_id, start_at, end_at) VALUES (4, '15:30:00', '17:30:00');

INSERT INTO teacher(teacher_id, fullname, gender, account_id) VALUES (default, 'Hồ Tuấn Thanh', 'Nam', 5);
INSERT INTO teacher(teacher_id, fullname, gender, account_id) VALUES (default, 'Phạm Nguyễn Sơn Tùng', 'Nam', 6);
INSERT INTO teacher(teacher_id, fullname, gender, account_id) VALUES (default, 'Thái Lê Vinh', 'Nam', 7);
INSERT INTO teacher(teacher_id, fullname, gender, account_id) VALUES (default, 'Nguyễn Anh Thi', 'Nữ', 8);
INSERT INTO teacher(teacher_id, fullname, gender, account_id) VALUES (default, 'Nguyễn Thị Hồng Nhung', 'Nữ', 9);

INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2011', 'OOP', 1, 'Thứ 4', 3, 3, 'F102',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2021', 'CSDL', 3, 'Thứ 5', 1, 2,'F106', 100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2012', 'OOP', 2, 'Thứ 4', 3, 3, 'F103',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2031', 'HDH', 4, 'Thứ 2', 3, 1, 'E104',120);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2041', 'MMT', 5, 'Thứ 7', 1, 2, 'F202',110);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2051', 'CTDLGT', 1, 'Thứ 3', 4, 2, 'E305',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('SE2111', 'DAAS', 2, 'Thứ 4', 2, 2, 'E204',120);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS2121', 'NMHM', 4, 'Thứ 6', 3, 2, 'G102',100);

INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3001', 'OOP', 1, 'Thứ 2', 1, 6, 'F102',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3002', 'CTDLGT', 2, 'Thứ 3', 2, 6, 'E102',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3003', 'HDH', 3, 'Thứ 4', 3, 6, 'G102',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3004', 'OOP', 4, 'Thứ 5', 4, 6, 'H102',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3005', 'DAAS', 5, 'Thứ 6', 1, 6, 'E302',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3006', 'CSDL', 1, 'Thứ 7', 2, 6, 'G302',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3007', 'CTDLGT', 2, 'Thứ 2', 3, 6, 'G101',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3008', 'XLA', 3, 'Thứ 6', 4, 6, 'G105',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3009', 'LTUDW', 4, 'Thứ 2', 2, 6, 'E202',100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, room, maximum_slots) VALUES ('CS3010', 'DAAS', 5, 'Thứ 2', 2, 6, 'C102',100);

INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120449', 'Nguyễn Hoàng Long', 'Nam', '2000-04-01', 'Nghệ An', '18CTT4', 1);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120460', 'Lê Danh Lưu', 'Nam', '2000-09-06', 'Đắk Lắk','18CTT4', 2);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120461', 'Trần Nhật Quang', 'Nam', '2000-05-21', 'Bình Thuận','18CTT5', 3);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120462', 'Trần Hoàng Minh', 'Nam', '2000-04-01', 'Nghệ An', '18CTT5', 11);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120463', 'Lê Phan Công Minh', 'Nam', '2000-09-06', 'Đắk Lắk','18CTT4', 12);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120464', 'Nguyễn Hữu Trí', 'Nam', '2000-07-11', 'Hồ Chí Minh','18CNTN', 13);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120500', 'Lô Thị Mỹ Nương', 'Nữ', '2000-04-01', 'Lâm Đồng', '18CTT5', 14);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120020', 'Phan Thái Dương', 'Nam', '2000-09-06', 'Vũng Tàu','18CNTN', 15);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120023', 'Nguyễn Thị Hằng', 'Nữ', '2000-03-29', 'Bình Phước','18CNTN', 16);

INSERT INTO course_student(id, student_id, course_id) VALUES (default, 1, 'CS2031');



END;