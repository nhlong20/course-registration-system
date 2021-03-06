package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.*;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/10/2021 - 9:47 PM
 * @Description
 */
public class CourseStudentDAO {
    public static List<Student> getAllStudents(String courseId) {
        List<Student> students = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select cs.student from CourseStudent cs where cs.course.id = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("courseId", courseId);
            students = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return students;
    }
    public static List<CourseStudent> get(String courseId) {
        List<CourseStudent> courseStudents = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select cs from CourseStudent cs where cs.course.id = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("courseId", courseId);
            courseStudents = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return courseStudents;
    }
    public static List<Course> getAllCourses(String studentId) {
        List<Course> courses = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select cs.course from CourseStudent cs where cs.student.studentId = :studentId and cs.course.semester = :semId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", studentId);
            courses = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return courses;
    }
    public static List<Course> getAllCourses(String studentId, int semesterId) {
        List<Course> courses = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select cs.course from CourseStudent cs where cs.student.studentId = :studentId and cs.course.semester.semesterId = :semesterId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", studentId);
            query.setParameter("semesterId", semesterId);
            courses = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return courses;
    }
    public static CourseStudent get(String studentId, String courseId){
        CourseStudent courseStudent = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select cs from CourseStudent cs where cs.student.studentId = :studentId and cs.course.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", studentId);
            query.setParameter("courseId", courseId);
            List<CourseStudent> l = query.list();
            if(l.size() == 0) return null;
            courseStudent = l.get(0);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i l???y d??? li???u",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
        }
        return courseStudent;
    }
    public static boolean add(CourseStudent courseStudent){
        int currentSemId = SemesterDAO.getCurrent().getSemesterId();
        String curStudentId = courseStudent.getStudent().getStudentId();
        List<Course> registedCourses = getAllCourses(curStudentId, currentSemId);
        if (registedCourses.size() >=8) {
            JOptionPane.showMessageDialog(null, "B???n kh??ng ???????c ph??p ????ng k?? v?????t qu?? 8 kho?? h???c trong 1 h???c k???");
            return false;
        }

        String dayOfWeek = courseStudent.getCourse().getDayOfWeek();
        int shift = courseStudent.getCourse().getShift().getShiftId();
        String courseId = courseStudent.getCourse().getCourseId();

        for(Course course : registedCourses){
            String registedDayOfWeek = course.getDayOfWeek();
            int registedShift = course.getShift().getShiftId();
            String registedCourseId = course.getCourseId();
            if(courseId.equals(registedCourseId)){
                JOptionPane.showMessageDialog(null, "B???n ???? ????ng k?? kho?? h???c n??y", "????ng k?? th???t b???i", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(registedDayOfWeek.equals(dayOfWeek) && registedShift == shift){
                JOptionPane.showMessageDialog(null, "B???n kho?? h???c b???n ????ng k?? b??? tr??ng gi???","????ng k?? th???t b???i", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        // Proceed to regist new course after pass all above conditions
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(courseStudent);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i khi th??m Th??m gi??o v???",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
        return true;
    }

    public static boolean delete(CourseStudent courseStudent) {
        if (courseStudent == null) {
            JOptionPane.showMessageDialog(null, "D??? li???u kh??ng t???n t???i",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(courseStudent);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i khi th???c hi???n xo??",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public static boolean deleteByCourse(String courseId){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "delete from CourseStudent cs where cs.course.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("courseId", courseId);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i khi th???c hi???n xo??",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
