package matc.madjava.persistence;

import matc.madjava.entity.Center;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class CenterDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all centers
     *
     * @return centers
     */
    public List<Center> getAllCenters() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Center> query = builder.createQuery( Center.class );
        Root<Center> root = query.from( Center.class );
        List<Center> centers = session.createQuery( query ).getResultList();

        log.debug("The list of centers " + centers);
        session.close();

        return centers;
    }

    /**
     * gets center by center ID
     *
     * @param centerId
     * @return center
     */
    public Center getCenterByID(int centerId) {
        Session session = sessionFactory.openSession();
        Center center = session.get( Center.class, centerId );
        session.close();
        return center;
    }

    /**
     * updates center
     *
     * @param center
     */
    public void updateCenter(Center center) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(center);
        session.close();
    }

    /**
     * deletes center by ID
     *
     * @param center
     */
    public void deleteCenterByID(Center center) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(center);
        transaction.commit();
        session.close();
    }

    /**
     * insert center dao method
     *
     * @param center
     * @return id
     */
    public int insertCenter(Center center) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(center);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get center by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Center> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for center with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Center> query = builder.createQuery( Center.class );
        Root<Center> root = query.from( Center.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Center> centers = session.createQuery( query ).getResultList();

        session.close();
        return centers;
    }

    /**
     * Get center by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Center> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for center with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Center> query = builder.createQuery( Center.class );
        Root<Center> root = query.from( Center.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Center> centers = session.createQuery( query ).getResultList();
        session.close();
        return centers;
    }
}
