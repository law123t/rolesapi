package matc.madjava.persistence;

import matc.madjava.entity.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRolesDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all roles
     *
     * @return roles
     */
    public List<UserRoles> getAllUserRoles() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        List<UserRoles> roles = session.createQuery( query ).getResultList();

        log.debug("The list of roles " + roles);
        session.close();

        return roles;
    }

    /**
     * gets role by role ID
     *
     * @param userRoleID
     * @return role
     */
    public UserRoles getUserRoleByID(int userRoleID) {
        Session session = sessionFactory.openSession();
        UserRoles role = session.get( UserRoles.class, userRoleID );
        session.close();
        return role;
    }

    /**
     * updates role
     *
     * @param userRoles
     */
    public void updateUserRoles(UserRoles userRoles) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(userRoles);
        session.close();
    }

    /**
     * deletes role by ID
     *
     * @param role
     */
    public void deleteUserRolesByID(UserRoles role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    /**
     * insert role dao method
     *
     * @param role
     * @return id
     */
    public int insertUserRoles(UserRoles role) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(role);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Get user by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<UserRoles> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for role with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<UserRoles> roles = session.createQuery( query ).getResultList();

        session.close();
        return roles;
    }

    /**
     * Get user by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<UserRoles> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for role with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<UserRoles> roles = session.createQuery( query ).getResultList();
        session.close();
        return roles;
    }
}
