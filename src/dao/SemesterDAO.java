package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Semester;
import pojo.Semester;
import pojo.Subject;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 3:38 PM
 * @Description
 */
public class SemesterDAO {
    public static List<Semester> getAll() {
        List<Semester> semesters = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select semester from Semester semester";
            Query query = session.createQuery(hql);
            semesters = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return semesters;
    }
    public static Semester get(int semesterId) {
        Semester semester = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select semester from Semester semester where semester.id = :semesterId";
            Query query = session.createQuery(hql);
            query.setParameter("semesterId", semesterId);
            List<Semester> l = query.list();
            if(l.size()>0){
                semester = l.get(0);
            }
            return semester;
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return semester;
    }
    public static boolean delete(int curId) {
        Semester semester = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            semester = SemesterDAO.get(curId);
            if (semester == null) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // TODO - Remove all course of this semester

            // Remove semester
            session.remove(semester);

            session.getTransaction().commit();

            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi thực hiện xoá",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
    }
}
