package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Basket;
import pojo.Person;
import pojo.Product;
import util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;


public class BasketDao {
    public void addProductToBasket(int personId, int productId, int count) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, personId);
            if (person.getBaskets().stream().filter(s -> s.getProduct().getId() == productId).collect(Collectors.toList()).isEmpty()) {
                Product product = session.get(Product.class, productId);
                person.getBaskets().add(new Basket(person, count, product));
                session.save(person);
                session.getTransaction().commit();
            } else {
                Basket basket = person.getBaskets().stream().filter(s -> s.getProduct().getId() == productId).collect(Collectors.toList()).get(0);
                int firstQuantity = basket.getQuantity();
                basket.setQuantity(firstQuantity + count);
                session.update(person);
                session.getTransaction().commit();
            }
        }
    }

    public List<Basket> returnListOfProductsInBasket(Person person) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Basket where person = :p");
            query.setParameter("p", person);
            List<Basket> list = query.list();
            System.out.println(list);
            session.getTransaction().commit();
            return list;
        }
    }
}
