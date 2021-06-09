package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Course;
import pojo.Student;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:49 AM
 * @Description
 */
public class CourseDAO {
    public static List<Course> getAll(){
        List<Course> courses = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select cs from Course cs";
            Query query = session.createQuery(hql);
            courses = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return courses;
    }
}
