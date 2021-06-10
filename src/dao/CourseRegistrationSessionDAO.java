package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.CourseRegistrationSession;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 3:48 PM
 * @Description
 */
public class CourseRegistrationSessionDAO {
    public static List<CourseRegistrationSession> getAll() {
        List<CourseRegistrationSession> courseRegistrationSessions = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select regsession from CourseRegistrationSession regsession";
            Query query = session.createQuery(hql);
            courseRegistrationSessions = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return courseRegistrationSessions;
    }
    public static List<CourseRegistrationSession> getAll(int semesterId) {
        List<CourseRegistrationSession> courseRegistrationSessions = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select regsession from CourseRegistrationSession regsession where regsession.semester.id = :semesterId";
            Query query = session.createQuery(hql);
            query.setParameter("semesterId", semesterId);
            courseRegistrationSessions = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return courseRegistrationSessions;
    }
    public static CourseRegistrationSession get(int regsessionId) {
        CourseRegistrationSession courseRegistrationSession = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select regsession from CourseRegistrationSession regsession where regsession.id = :regressionId";
            Query query = session.createQuery(hql);
            query.setParameter("regressionId", regsessionId);
            List<CourseRegistrationSession> l = query.list();
            if(l.size()>0){
                courseRegistrationSession = l.get(0);
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return courseRegistrationSession;
    }
    public static Boolean add(CourseRegistrationSession regsession){
        if (get(regsession.getRegistrationSessionId()) != null) {
            JOptionPane.showMessageDialog(null, "Bản ghi đã tồn tại, vui lòng thử lại",
                    "Duplicate value error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(regsession);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi khi thêm bản ghi mới",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
