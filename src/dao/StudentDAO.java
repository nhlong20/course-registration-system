package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Student;
import util.HibernateUtil;

import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:50 AM
 * @Description
 */
public class StudentDAO {
    public static List<Student> getStudentList(){
        List<Student> students = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            String hql = "select sv from Student sv";
            Query query = session.createQuery(hql);
            students = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return students;
    }
    public static Student getStudent(String studentID){
        Student student = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from Student sv where sv.studentId = :studentID";
            Query query = session.createQuery(hql);
            query.setParameter("studentID", studentID);
            List<Student> l = query.list();
            student = l.get(0);
            return student;
        } catch (HibernateException ex) {
        //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return student;
    }

}
