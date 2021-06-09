package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Student;
import pojo.Teacher;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 6/9/2021 - 10:59 PM
 * @Description
 */
public class TeacherDAO {
    public static List<Teacher> getAll(){
        List<Teacher> teachers = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select gv from Teacher gv";
            Query query = session.createQuery(hql);
            teachers = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return teachers;
    }


}
