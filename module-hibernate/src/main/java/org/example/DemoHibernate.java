package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class DemoHibernate {
    public static void main(String[] args) {

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory  = context.getBean(SessionFactory.class);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User();
        Address address = new Address();

        address.setStreet("Margaryan street");

        user.setName("Edgar");
        user.setEmail("vardedgar@mail.ru");

        user.setAddress(address);

        session.save(user);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Close the SessionFactory
        sessionFactory.close();

    }
}