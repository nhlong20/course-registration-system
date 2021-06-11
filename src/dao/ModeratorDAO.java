package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
import pojo.Moderator;
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
public class ModeratorDAO {
    private static String ACCOUNT_TYPE = "Moderator";

    public static List<Moderator> getAll() {
        List<Moderator> moderators = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            String hql = "select moderator from Moderator moderator order by moderator.id asc ";
            Query query = session.createQuery(hql);
            moderators = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return moderators;
    }

    public static Moderator get(String modId) {
        Moderator moderator = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select moderator from Moderator moderator where moderator.id = :modId";
            Query query = session.createQuery(hql);
            query.setParameter("modId", modId);
            List<Moderator> l = query.list();
            if (l.size() > 0) {
                moderator = l.get(0);
            }
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return moderator;
    }

    public static Boolean add(Moderator moderator) {
        if (get(moderator.getModeratorId()) != null) {
            JOptionPane.showMessageDialog(null, "Mã giáo vụ đã tồn tại",
                    "Duplicate value error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Add new account corresponding to new student
        Account account = new Account(ACCOUNT_TYPE, moderator.getModeratorId(), moderator.getModeratorId());
        AccountDAO.addAccount(account);
        moderator.setAccount(account);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(moderator);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            AccountDAO.delete(moderator.getModeratorId());
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi thêm Thêm giáo vụ",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean update(Moderator moderator) {
        if (get(moderator.getModeratorId()) == null) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại",
                    "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(moderator);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi khi cập nhật",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean delete(String modId) {
        Moderator moderator = get(modId);
        if (moderator == null) {
            JOptionPane.showMessageDialog(null, "Giáo vụ không tồn tại",
                    "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Remove moderator from db first
            session.remove(moderator);
            session.getTransaction().commit();

            // Then remove account from db
            AccountDAO.delete(modId);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi xoá giáo vụ",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
