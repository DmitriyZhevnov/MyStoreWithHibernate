package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Basket;
import pojo.BasketItem;
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
//
//            if (person.getBasket().getBasketItems().stream().forEach(basketItem -> basketItem.getProducts())11;
//                    filter(s -> s.getId() == productId).collect(Collectors.toList()).isEmpty()){
                Product product = session.get(Product.class, productId);
             //   product.setCount(count);
            product.setBasketItems(person.getBasket().getBasketItems());
//                person.getBasket().getBasketItems().add(new BasketItem()))
//                System.out.println(person.getBasket());
                session.update(product);
                session.getTransaction().commit();
//            } else {
//                Product product = person.getBasket().getProducts().stream().filter(s -> s.getId() == productId).collect(Collectors.toList()).get(0);
//                int firstQuantity = product.getCount();
//                product.setCount(firstQuantity + count);
//                session.update(person);
//                session.getTransaction().commit();
//            }
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
