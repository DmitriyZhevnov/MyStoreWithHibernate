package dao;

import org.hibernate.Session;
import pojo.Role;
import util.HibernateUtil;

public class RoleDao {
    public Role returnUserValue(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Role roleUser = session.get(Role.class, 2);
            session.getTransaction().commit();
            //System.out.println(roleUser);
            return roleUser;
        }
    }
    public void addNewRole(String name){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Role roleUser = new Role(name);
            session.save(roleUser);
            session.getTransaction().commit();
            System.out.println(roleUser);
        }
    }
}
