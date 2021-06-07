package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Clazz;
import pojo.Moderator;
import pojo.Subject;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 7:46 PM
 * @Description
 */
public class SubjectDAO {
    public static List<Subject> getAll() {
        List<Subject> subjects = null;
        try(Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select subject from Subject subject";
            Query query = session.createQuery(hql);
            subjects = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return subjects;
    }
    public static Subject get(String subjectId) {
        Subject subject = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select subject from Subject subject where subject.id = :subjectId";
            Query query = session.createQuery(hql);
            query.setParameter("subjectId", subjectId);
            List<Subject> l = query.list();
            if(l.size()>0){
                subject = l.get(0);
            }
            return subject;
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return subject;
    }
    public static Boolean add(Subject subject){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (get(subject.getSubjectId()) != null) {
                JOptionPane.showMessageDialog(null, "Bản ghi đã tồn tại, vui lòng thử lại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            session.save(subject);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi khi thêm bản ghi mới",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static Boolean update(Subject subject){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (subject == null) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            session.update(subject);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi khi cập nhật",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static boolean delete(String curId) {
        Subject subject = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            subject = get(curId);
            if (subject == null) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // Remove moderator from db first
            session.remove(subject);
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
