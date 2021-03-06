package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
import pojo.Student;
import util.HibernateUtil;

import javax.swing.*;
import java.util.List;

/**
 * dao
 *
 * @created by ASUS - StudentID : 18120449
 * @Date 5/30/2021 - 2:50 AM
 * @Description
 */
public class StudentDAO {
    private static String ACCOUNT_TYPE = "Student";

    public static List<Student> getAll() {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select sv from Student sv";
            Query query = session.createQuery(hql);
            students = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return students;
    }

    public static List<Student> getAll(String classCode) {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select sv from Student sv where sv.clazz.classCode = :classCode order by  sv.studentId asc ";
            Query query = session.createQuery(hql);
            query.setParameter("classCode", classCode);
            students = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return students;
    }

    public static Student getByStudentId(String studentID) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select sv from Student sv where sv.studentId = :studentID";
            Query query = session.createQuery(hql);
            query.setParameter("studentID", studentID);
            List<Student> l = query.list();
            if (l.size() > 0)
                student = l.get(0);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }
        return student;
    }

    public static boolean add(Student student) {
        if (getByStudentId(student.getStudentId()) != null) {
            JOptionPane.showMessageDialog(null, "D??? li???u ???? t???n t???i",
                    "Tr??ng d??? li???u", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Add new account corresponding to new student
            Account account = new Account(ACCOUNT_TYPE, student.getStudentId(), student.getStudentId());
            AccountDAO.addAccount(account);
            student.setAccount(account);

            session.save(student);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i khi th??m Th??m gi??o v???",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
        return true;
    }

    public static boolean update(Student student) {
        if (getByStudentId(student.getStudentId()) == null) {
            JOptionPane.showMessageDialog(null, "D??? li???u kh??ng t???n t???i",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "C?? l???i khi c???p nh???t",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean delete(String curId) {
        Student student = getByStudentId(curId);
        if (student == null) {
            JOptionPane.showMessageDialog(null, "D??? li???u kh??ng t???n t???i",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(student);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "C?? l???i khi th???c hi???n xo??",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            return false;
        }
        return true;
    }

    public static int countStudentWithGender(String classCode, int gender) {
        int count = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select count(id)" +
                    "from Student s " +
                    "where s.clazz.classCode = :classCode and s.gender = :gender";
            Query query = session.createQuery(hql);
            query.setParameter("classCode", classCode);
            if (gender == 0) query.setParameter("gender", "N???");
            if (gender == 1) query.setParameter("gender", "Nam");
            List l = query.list();
            if (l.size() > 0) {
                Number res = (Number) l.get(0);
                count = res.intValue();
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return count;
    }
}
