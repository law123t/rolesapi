package matc.madjava.persistence;

import matc.madjava.entity.PointGuard;
import matc.madjava.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class PointGuardDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all pointGuards
     *
     * @return pointGuards
     */
    public List<PointGuard> getAllPointGuards() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PointGuard> query = builder.createQuery( PointGuard.class );
        Root<PointGuard> root = query.from( PointGuard.class );
        List<PointGuard> pointGuards = session.createQuery( query ).getResultList();

        log.debug("The list of pointGuards " + pointGuards);
        session.close();

        return pointGuards;
    }

    /**
     * gets pointGuard by pointGuard ID
     *
     * @param pointGuardId
     * @return pointGuard
     */
    public PointGuard getPointGuardByID(int pointGuardId) {
        Session session = sessionFactory.openSession();
        PointGuard pointGuard = session.get( PointGuard.class, pointGuardId );
        session.close();
        return pointGuard;
    }

    /**
     * updates pointGuard
     *
     * @param pointGuard
     */
    public void updatePointGuard(PointGuard pointGuard) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(pointGuard);
        session.close();
    }

    /**
     * deletes pointGuard by ID
     *
     * @param pointGuard
     */
    public void deletePointGuardByID(PointGuard pointGuard) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(pointGuard);
        transaction.commit();
        session.close();
    }

    /**
     * insert pointGuard dao method
     *
     * @param pointGuard
     * @return id
     */
    public int insertPointGuard(PointGuard pointGuard) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(pointGuard);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get pointGuard by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<PointGuard> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for pointGuard with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PointGuard> query = builder.createQuery( PointGuard.class );
        Root<PointGuard> root = query.from( PointGuard.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<PointGuard> pointGuards = session.createQuery( query ).getResultList();

        session.close();
        return pointGuards;
    }

    /**
     * Get pointGuard by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<PointGuard> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for pointGuard with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PointGuard> query = builder.createQuery( PointGuard.class );
        Root<PointGuard> root = query.from( PointGuard.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<PointGuard> pointGuards = session.createQuery( query ).getResultList();
        session.close();
        return pointGuards;
    }
}
