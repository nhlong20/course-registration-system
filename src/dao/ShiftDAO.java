package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Shift;
import pojo.Teacher;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/9/2021 - 11:02 PM
 * @Description
 */
public class ShiftDAO {
    public static List<Shift> getAll(){
    List<Shift> shifts = null;
    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        String hql = "select st from Shift st";
        Query query = session.createQuery(hql);
        shifts = query.list();
    } catch (HibernateException ex) {
        System.err.println(ex);
    }
    return shifts;
}
}
