package scs.mpp.exam.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import scs.mpp.exam.model.Word;

import java.util.ArrayList;
import java.util.List;

public class WordRepository implements IWordRepository {
    private static SessionFactory sessionFactory;
    private static Session session;

    private static void initialiseSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }

    public WordRepository() {
        initialiseSessionFactory();
        session = sessionFactory.openSession();
    }

    public List<Word> getWords() {
        try
        {
            return session.createQuery("SELECT a FROM Word a", Word.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
