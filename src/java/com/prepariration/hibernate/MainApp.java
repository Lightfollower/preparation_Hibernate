package com.prepariration.hibernate;

import com.prepariration.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        SessionFactory factory = ClassWithStaticMethodThatReturnSessionFactory.getSessionFactoryWasOnMyDeskBeforeLunch();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

//            fillDB(session);
//
//            Покупатели, купившие определённый товар
            List<Student> students = session.createQuery("select p from Student p where name = 'Bob' ").getResultList();
            students.forEach(System.out::println);

//
////            Товары, купленые определённым юзверем
//            List<User> users = session.createQuery("select u from User u where name = 'Bob' ").getResultList();
//            for (User u :
//                    users) {
//                u.getPurchases().forEach(Product::print);
//            }
//
////            Удоление
//            User user = (User)session.createQuery("select u from User u where name = 'Dob'").getSingleResult();
//            session.delete(session.get(User.class, user.getId()));
//            session.getTransaction().commit();
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            users = session.createQuery("select u from User u  ").getResultList();
//            for (User u :
//                    users) {
//                u.print();
//            }
//
////            Детализация сделка- цена.
////            Все сделки.
//            List<Deal> deals  = session.createQuery("select d from Deal d  ").getResultList();
//            for (Deal d :
//                    deals) {
//                d.print();
//            }
////            Сделки определённого пользователя.
//            user = (User)session.createQuery("select u from User u where id = 1").getSingleResult();
//            user.getDeals().forEach(Deal::print);
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

//    private static void fillDB(Session session) {
//        List<Product> allProducts = session.createQuery("SELECT p FROM Product p").getResultList();
//        User u1 = session.get(User.class, 3L);
//        User u2 = session.get(User.class, 1L);
//
//        allProducts.stream().forEach(p -> {
//            if (p.getId() == 1) {
//                u1.getPurchases().add(p);
//            }
//        });
//        allProducts.stream().forEach(p -> {
//            if (p.getId() == 4 || p.getId() == 1) {
//                u2.getPurchases().add(p);
//            }
//        });
//        session.save(u1);
//        session.save(u2);
//    }
}