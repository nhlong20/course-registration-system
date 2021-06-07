package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.postgresql.util.PSQLException;
import pojo.Account;
import pojo.Clazz;
import pojo.Moderator;
import pojo.Student;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/7/2021 - 10:06 PM
 * @Description
 */
public class ClazzDAO {
    public static List<Clazz> getAll(){
        List<Clazz> clazzes = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select clazz from Clazz clazz";
            Query query = session.createQuery(hql);
            clazzes = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return clazzes;
    }
    public static Clazz get(String classCode){
        Clazz clazz = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select clazz from Clazz clazz where clazz.classCode = :classCode";
            Query query = session.createQuery(hql);
            query.setParameter("classCode", classCode);
            List<Clazz> l = query.list();
            if(l.size() >0){
                clazz = l.get(0);
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return clazz;
    }

    public static Boolean add(Clazz clazz){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (get(clazz.getClassCode()) != null) {
                JOptionPane.showMessageDialog(null, "Bản ghi đã tồn tại, vui lòng thử lại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            session.save(clazz);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi khi thêm bản ghi mới",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
//            System.err.println(ex);
            return false;
        }
    }
    public static boolean delete(String classCode) {
        Clazz clazz = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            clazz = get(classCode);
            if (clazz == null) {
                JOptionPane.showMessageDialog(null, "Bản ghi không tồn tại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            session.remove(clazz);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi xoá bản ghi",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
        return true;
    }
}
