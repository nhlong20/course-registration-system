package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Moderator;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:49 AM
 * @Description
 */
public class ModeratorDAO {
    public static List<Moderator> getModeratorList(){
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
    public static Moderator getModerator(String modId){
        Moderator moderator = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select moderator from Moderator moderator where moderator.id = :modId";
            Query query = session.createQuery(hql);
            query.setParameter("modId", modId);
            List<Moderator> l = query.list();
            moderator = l.get(0);
            return moderator;
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return moderator;
    }
    public static void addModerator(Moderator mod){

    }
    public static void deleteModerator(String modId){

    }
}
