package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Account;
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
    private static String ACCOUNT_TYPE = "Moderator";
    private static String DEFAULT_PASWORD = "giaovu";
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
    public static void addModerator(Moderator moderator){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
//            if (getModerator(moderator.getModeratorId()) != null) {
//                JOptionPane.showMessageDialog(new JFrame(),"Số chứng minh thư đã tồn tại",
//                        "Unexpected error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            if (layThongTinSinhVienByMSSV(sinhVien.getMaSinhVien()) != null) {
//                JOptionPane.showMessageDialog(new JFrame(),"Sinh viên đã tồn tại",
//                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            // Add new account corresponding to new student
            Account account = new Account(ACCOUNT_TYPE, moderator.getModeratorId(), DEFAULT_PASWORD);
            AccountDAO.addAccount(account);

            moderator.setAccount(account);

            session.save(moderator);
            session.getTransaction().commit();

        } catch (HibernateException ex) {
//            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi thêm Sinh Viên",
//                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }
    public static void deleteModerator(String modId){

    }
}
