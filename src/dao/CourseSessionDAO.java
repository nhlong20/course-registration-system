package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Semester;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/8/2021 - 3:48 PM
 * @Description
 */
public class CourseSessionDAO {
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
}
