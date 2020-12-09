package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Person;
import pojo.Product;
import pojo.Role;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

public class PersonDao {

    public void setPhoneNumberAndAddress(Person person,String phoneNumber, String address){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Person p =  session.get(Person.class, person.getId());
            p.setPhoneNumber(phoneNumber);
            p.setAddress(address);
            session.update(p);
            session.getTransaction().commit();
        }
    }

    public boolean checkLoginForExist(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select id from Person where login = :login");
            query.setParameter("login", login);
            List list = query.list();
            session.getTransaction().commit();
            if (list.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void registerNewPerson(String name, int age, String login, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            RoleDao roleDao = new RoleDao();
            Role roleForNewUser = roleDao.returnUserValue();
            session.save(new Person(name, age, login, password, roleForNewUser, "", ""));
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void addProductToBasket(int personId, int productId, int count){
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
////            session.beginTransaction();
////            Person person = session.get(Person.class, personId);
////            Product product = session.get(Product.class, productId);
////            person.getBasket().add(product);
////            product.setCount(count);
////            session.update(person);
//
////            Query query = session.createQuery("update Basket set count_of_product = :countValue " +
////                    "where id_person = :idPerson and id_product = :idProduct");
////            query.setParameter("countValue", count);
////            query.setParameter("idPerson", personId);
////            query.setParameter("idProduct", productId);
////            query.list();
//            session.getTransaction().commit();
//        }
//    }

    public List<Person> returnAllPersons() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Person");
            List<Person> listPerson = query.list();
            session.getTransaction().commit();
            return listPerson;
        }
    }

    public Person checkAndReturnPersonByLoginAndPassword(String login, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select id from Person where login = :login and password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List list = query.list();
            session.getTransaction().commit();
            if (list.isEmpty()) {
                return null;
            } else {
                return session.get(Person.class, (Integer.parseInt(list.get(0).toString())));
            }
        }
    }
}
