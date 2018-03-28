package matc.madjava.persistence;

import matc.madjava.entity.PowerForward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class PowerForwardDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all power forwards
     *
     * @return powerForwards
     */
    public List<PowerForward> getAllPowerForwards() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PowerForward> query = builder.createQuery( PowerForward.class );
        Root<PowerForward> root = query.from( PowerForward.class );
        List<PowerForward> powerForwards = session.createQuery( query ).getResultList();

        log.debug("The list of power forwards " + powerForwards);
        session.close();

        return powerForwards;
    }

    /**
     * gets power forward by power forward ID
     *
     * @param powerForwardID
     * @return powerForward
     */
    public PowerForward getPowerForwardByID(int powerForwardID) {
        Session session = sessionFactory.openSession();
        PowerForward powerForward = session.get( PowerForward.class, powerForwardID );
        session.close();
        return powerForward;
    }

    /**
     * updates power forward
     *
     * @param powerForward
     */
    public void updatePowerForward(PowerForward powerForward) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(powerForward);
        session.close();
    }

    /**
     * deletes power forward by ID
     *
     * @param powerForward
     */
    public void deletePowerForwardByID(PowerForward powerForward) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(powerForward);
        transaction.commit();
        session.close();
    }

    /**
     * insert power forward dao method
     *
     * @param powerForward
     * @return id
     */
    public int insertPowerForward(PowerForward powerForward) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(powerForward);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get power forward by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<PowerForward> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for power forward with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PowerForward> query = builder.createQuery( PowerForward.class );
        Root<PowerForward> root = query.from( PowerForward.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<PowerForward> powerForwards = session.createQuery( query ).getResultList();

        session.close();
        return powerForwards;
    }

    /**
     * Get power forward by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<PowerForward> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for power forward with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PowerForward> query = builder.createQuery( PowerForward.class );
        Root<PowerForward> root = query.from( PowerForward.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<PowerForward> powerForwards = session.createQuery( query ).getResultList();
        session.close();
        return powerForwards;
    }
}
