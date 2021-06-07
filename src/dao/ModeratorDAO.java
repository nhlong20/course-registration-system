package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
import pojo.Moderator;
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
    private static String DEFAULT_PASWORD = "giaovu";

    public static List<Moderator> getModeratorList() {
        List<Moderator> moderators = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select moderator from Moderator moderator";
            Query query = session.createQuery(hql);
            moderators = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return moderators;
    }

    public static Moderator getModerator(String modId) {
        Moderator moderator = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select moderator from Moderator moderator where moderator.id = :modId";
            Query query = session.createQuery(hql);
            query.setParameter("modId", modId);
            List<Moderator> l = query.list();
            if(l.size()>0){
                moderator = l.get(0);
            }
            return moderator;
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return moderator;
    }

    public static Boolean addModerator(Moderator moderator) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            if (getModerator(moderator.getModeratorId()) != null) {
                JOptionPane.showMessageDialog(null, "Giáo vụ đã tồn tại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Add new account corresponding to new student
            Account account = new Account(ACCOUNT_TYPE, moderator.getModeratorId(), DEFAULT_PASWORD);
            AccountDAO.addAccount(account);

            moderator.setAccount(account);

            session.save(moderator);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi thêm Thêm giáo vụ",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
    }

    public static boolean deleteModerator(String modId) {
        Moderator moderator = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            moderator = getModerator(modId);
            if (moderator == null) {
                JOptionPane.showMessageDialog(null, "Giáo vụ không tồn tại",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // Remove moderator from db first
            session.remove(moderator);
            session.getTransaction().commit();

            // Then remove account from db
            AccountDAO.removeAccount(modId);
            return true;
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Có lỗi khi xoá giáo vụ",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
    }
}
