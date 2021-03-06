package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Clazz;
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
            System.err.println(ex);
        }
        return clazzes;
    }
    public static Clazz get(String classCode){
        Clazz clazz = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            clazz = session.get(Clazz.class, classCode);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
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
            return false;
        }
    }
    public static boolean delete(String classCode) {
        Clazz clazz = get(classCode);
        if (clazz == null) {
            JOptionPane.showMessageDialog(null, "Bản ghi không tồn tại",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.remove(clazz);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi xoá bản ghi",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
