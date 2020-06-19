package scs.mpp.exam.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import scs.mpp.exam.model.Game;

public class GameRepository implements IGameRepository{
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

    public GameRepository() {
        initialiseSessionFactory();
        session = sessionFactory.openSession();
    }

    public void saveGame(Game game) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(game);
        session.getTransaction().commit();
        session.close();
    }

}
