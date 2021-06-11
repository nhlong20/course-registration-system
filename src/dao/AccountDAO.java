package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:49 AM
 * @Description
 */
public class AccountDAO {
    public static Account getAccount(String username, String password){
        Account account = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select acc from Account acc where acc.username = :username and acc.passwd = :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Account> list = query.list();
            if (list.size() == 0) return null;
            account = list.get(0);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return account;
    }
    public static Account getAccount(String username) {
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "select acc from Account acc where acc.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            List<Account> list = query.list();
            if (list.size() == 0) return null;
            account = list.get(0);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return account;
    }
    public static void addAccount(Account account) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }
    public static void delete(String username) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "delete from Account where username= :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            int res = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }
    public static Boolean update(Account acc){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(acc);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(ex);
            return false;
        }
        return true;
    }
}
