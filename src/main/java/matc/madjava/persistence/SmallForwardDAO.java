package matc.madjava.persistence;

import matc.madjava.entity.SmallForward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class SmallForwardDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all smallForwards
     *
     * @return smallForwards
     */
    public List<SmallForward> getAllSmallForwards() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SmallForward> query = builder.createQuery( SmallForward.class );
        Root<SmallForward> root = query.from( SmallForward.class );
        List<SmallForward> smallForwards = session.createQuery( query ).getResultList();

        log.debug("The list of smallForwards " + smallForwards);
        session.close();

        return smallForwards;
    }

    /**
     * gets smallForward by smallForward ID
     *
     * @param smallForwardId
     * @return smallForward
     */
    public SmallForward getSmallForwardByID(int smallForwardId) {
        Session session = sessionFactory.openSession();
        SmallForward smallForward = session.get( SmallForward.class, smallForwardId );
        session.close();
        return smallForward;
    }

    /**
     * updates smallForward
     *
     * @param smallForward
     */
    public void updateSmallForward(SmallForward smallForward) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(smallForward);
        session.close();
    }

    /**
     * deletes smallForward by ID
     *
     * @param smallForward
     */
    public void deleteSmallForwardByID(SmallForward smallForward) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(smallForward);
        transaction.commit();
        session.close();
    }

    /**
     * insert smallForward dao method
     *
     * @param smallForward
     * @return id
     */
    public int insertSmallForward(SmallForward smallForward) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(smallForward);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get smallForward by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<SmallForward> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for smallForward with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SmallForward> query = builder.createQuery( SmallForward.class );
        Root<SmallForward> root = query.from( SmallForward.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<SmallForward> smallForwards = session.createQuery( query ).getResultList();

        session.close();
        return smallForwards;
    }

    /**
     * Get smallForward by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<SmallForward> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for smallForward with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SmallForward> query = builder.createQuery( SmallForward.class );
        Root<SmallForward> root = query.from( SmallForward.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<SmallForward> smallForwards = session.createQuery( query ).getResultList();
        session.close();
        return smallForwards;
    }
}
