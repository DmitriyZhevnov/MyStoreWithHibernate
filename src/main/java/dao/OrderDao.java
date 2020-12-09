package dao;

import org.hibernate.Session;
import util.HibernateUtil;

public class OrderDao {
    public void saveNewOrder(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            session.getTransaction().commit();
        }
    }
}
