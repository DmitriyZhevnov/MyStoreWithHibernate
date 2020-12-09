package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Person;
import pojo.Product;
import util.HibernateUtil;

import java.util.List;

public class ProductDao {
    public List<Product> returnAllProducts(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Product");
            List<Product> list = query.list();
            session.getTransaction().commit();
            return list;
        }
    }

    public Product returnProductById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

}
