package matc.madjava.persistence;

import matc.madjava.entity.ShootingGuard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ShootingGuardDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all shootingGuards
     *
     * @return shootingGuards
     */
    public List<ShootingGuard> getAllShootingGuards() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShootingGuard> query = builder.createQuery( ShootingGuard.class );
        Root<ShootingGuard> root = query.from( ShootingGuard.class );
        List<ShootingGuard> shootingGuards = session.createQuery( query ).getResultList();

        log.debug("The list of shootingGuards " + shootingGuards);
        session.close();

        return shootingGuards;
    }

    /**
     * gets shootingGuard by shootingGuard ID
     *
     * @param shootingGuardId
     * @return shootingGuard
     */
    public ShootingGuard getShootingGuardByID(int shootingGuardId) {
        Session session = sessionFactory.openSession();
        ShootingGuard shootingGuard = session.get( ShootingGuard.class, shootingGuardId );
        session.close();
        return shootingGuard;
    }

    /**
     * updates shootingGuard
     *
     * @param shootingGuard
     */
    public void updateShootingGuard(ShootingGuard shootingGuard) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(shootingGuard);
        session.close();
    }

    /**
     * deletes shootingGuard by ID
     *
     * @param shootingGuard
     */
    public void deleteShootingGuardByID(ShootingGuard shootingGuard) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(shootingGuard);
        transaction.commit();
        session.close();
    }

    /**
     * insert shootingGuard dao method
     *
     * @param shootingGuard
     * @return id
     */
    public int insertShootingGuard(ShootingGuard shootingGuard) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(shootingGuard);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get shootingGuard by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<ShootingGuard> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for shootingGuard with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShootingGuard> query = builder.createQuery( ShootingGuard.class );
        Root<ShootingGuard> root = query.from( ShootingGuard.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<ShootingGuard> shootingGuards = session.createQuery( query ).getResultList();

        session.close();
        return shootingGuards;
    }

    /**
     * Get shootingGuard by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<ShootingGuard> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for shootingGuard with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShootingGuard> query = builder.createQuery( ShootingGuard.class );
        Root<ShootingGuard> root = query.from( ShootingGuard.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<ShootingGuard> shootingGuards = session.createQuery( query ).getResultList();
        session.close();
        return shootingGuards;
    }
}
