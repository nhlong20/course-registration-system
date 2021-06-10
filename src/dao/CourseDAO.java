package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
import pojo.Course;
import pojo.Semester;
import pojo.Student;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:49 AM
 * @Description
 */
public class CourseDAO {
    public static List<Course> getAll(){
        List<Course> courses = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select cs from Course cs";
            Query query = session.createQuery(hql);
            courses = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return courses;
    }
    public static List<Course> getAll(int semesterId){
        List<Course> courses = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select cs from Course cs where cs.semester.id = :semesterId";
            Query query = session.createQuery(hql);
            query.setParameter("semesterId", semesterId);
            courses = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return courses;
    }
    public static Course get(String courseId){
        Course course = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select cs from Course cs where cs.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setParameter("courseId", courseId);
            List<Course> l = query.list();
            if(l.size() == 0) return null;
            course = l.get(0);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi lấy dữ liệu",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
        }
        return course;
    }
    public static boolean add(Course course){
        if (get(course.getCourseId()) != null) {
            JOptionPane.showMessageDialog(null, "Dữ liệu đã tồn tại",
                    "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi thêm Thêm giáo vụ",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
        return true;
    }
    public static boolean delete(String curId) {
        Course course = get(curId);
        if (course == null) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(course);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi thực hiện xoá",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
