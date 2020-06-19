package scs.mpp.exam.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import scs.mpp.exam.model.Round;

import java.util.List;

public class RoundRepository implements IRoundRepository{
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

    public RoundRepository() {
        initialiseSessionFactory();
        session = sessionFactory.openSession();
    }

    public void saveRound(Round round) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(round);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Round> getByGameId(String gameId) {
        session = sessionFactory.openSession();
        String hql = "FROM Round E WHERE E.gameId = :gagemId";
        Query query = session.createQuery(hql);
        query.setParameter("gagemId",gameId);
        List result = query.list();

        return (List<Round>) result;
    }

}
