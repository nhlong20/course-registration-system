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
                                                  start_at timestamp with time zone NOT NULL,
                                                  end_at timestamp with time zone NOT NULL,
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
                               course_id SERIAL PRIMARY KEY,
                               subject_id varchar(10) NOT NULL,
                               teacher_id integer NOT NULL,
                               day_of_week varchar(10) NOT NULL CHECK (day_of_week in ('Thứ 2','Thứ 3','Thứ 4','Thứ 5','Thứ 6','Thứ 7','Chủ Nhật')),
                               shift_id integer NOT NULL,
                               semester_id integer NOT NULL,
                               maximum_slots integer NOT NULL CHECK (maximum_slots > 0)
);


CREATE TABLE public.course_student (
                                       id integer PRIMARY KEY,
                                       student_id VARCHAR(20) NOT NULL,
                                       course_id integer NOT NULL
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
    ADD CONSTRAINT FK_course_student__student_id FOREIGN KEY (student_id) REFERENCES public.subject(subject_id);
ALTER TABLE public.course_student
    ADD CONSTRAINT FK_course_student__course_id FOREIGN KEY (course_id) REFERENCES public.course(course_id);
ALTER TABLE public.teacher
    ADD CONSTRAINT FK_teacher__account_id FOREIGN KEY (account_id) REFERENCES public.account(account_id);

COMMIT;

INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120449', 'sinhvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120460', 'sinhvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Student', '18120461', 'sinhvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Moderator', 'MOD001', 'giaovu', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV001', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV002', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV003', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV004', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Teacher', 'GV005', 'giangvien', default);
INSERT INTO account (account_id, acc_type, username, passwd, created_at) VALUES (default, 'Moderator', 'MOD002', 'giaovu', default);

INSERT INTO moderator(moderator_id, fullname, gender,dob, mod_address, phone,  account_id)
    VALUES ('MOD001', 'Văn Chí Nam', 'Nam', '1984-07-06', '0942020222', 'Hồ Chí Minh', 4);
INSERT INTO moderator(moderator_id, fullname, gender,dob, mod_address, phone,  account_id)
    VALUES ('MOD002', 'Thái Lê Vinh', 'Nam', '1987-07-06', '0564859274', 'Hồ Chí Minh', 10);

INSERT INTO public.subject VALUES ('OOP', 'Lập trình hướng đối tượng', 4);
INSERT INTO public.subject VALUES ('CTDLGT', 'Cấu trúc dữ liệu và giải thuật', 4);
INSERT INTO public.subject VALUES ('CSDL', 'Cơ sở dữ liệu', 4);
INSERT INTO public.subject VALUES ('HDH', 'Hệ điều hành', 4);
INSERT INTO public.subject VALUES ('MMT', 'Mạng máy tính', 4);

INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK1', 2017, '2017-08-20', '2018-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2017, '2018-02-03', '2018-06-28');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default,'HK1', 2018, '2018-08-20', '2019-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2018, '2019-02-03', '2019-06-28');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK1', 2019, '2019-08-20', '2020-01-20');
INSERT INTO semester(semester_id, sem_name, sem_year, startdate, enddate) VALUES (default, 'HK2', 2019, '2020-02-03', '2020-06-28');

INSERT INTO class(class_year, class_code) VALUES (2018, '18CNTN');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT1');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT2');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT3');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT4');
INSERT INTO class(class_year, class_code) VALUES (2018, '18CTT5');
INSERT INTO class(class_year, class_code) VALUES (2019, '19CNTN');
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

INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, maximum_slots) VALUES (default, 'OOP', 1, 'Thứ 4', 3, 3, 100);
INSERT INTO course(course_id, subject_id, teacher_id, day_of_week, shift_id, semester_id, maximum_slots) VALUES (default, 'CSDL', 3, 'Thứ 5', 3, 2, 100);

INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120449', 'Nguyễn Hoàng Long', 'Nam', '2000-04-01', 'Nghệ An', '18CTT4', 1);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120460', 'Lê Danh Lưu', 'Nam', '2000-09-06', 'Đắk Lắk','18CTT4', 2);
INSERT INTO student(id, student_id, fullname, gender, dob, stu_address, class_code, account_id) VALUES (default, '18120461', 'Nguyễn Khắc Luân', 'Nam', '2000-05-21', 'Bình Thuận','18CTT4', 3);

END;