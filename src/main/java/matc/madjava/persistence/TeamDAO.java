package matc.madjava.persistence;

import matc.madjava.entity.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamDAO {

    private final Logger log = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * returns an array of all teams
     *
     * @return teams
     */
    public List<Team> getAllTeams() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Team> query = builder.createQuery( Team.class );
        Root<Team> root = query.from( Team.class );
        List<Team> teams = session.createQuery( query ).getResultList();

        log.debug("The list of teams " + teams);
        session.close();

        return teams;
    }

    /**
     * gets team by team ID
     *
     * @param teamId
     * @return team
     */
    public Team getTeamByID(int teamId) {
        Session session = sessionFactory.openSession();
        Team team = session.get( Team.class, teamId );
        session.close();
        return team;
    }

    /**
     * updates team
     *
     * @param team
     */
    public void updateTeam(Team team) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(team);
        session.close();
    }

    /**
     * deletes team by ID
     *
     * @param team
     */
    public void deleteTeamByID(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(team);
        transaction.commit();
        session.close();
    }

    /**
     * insert team dao method
     *
     * @param team
     * @return id
     */
    public int insertTeam(Team team) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Integer)session.save(team);
        transaction.commit();
        session.close();
        return id;
    }


    /**
     * Get team by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Team> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for team with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Team> query = builder.createQuery( Team.class );
        Root<Team> root = query.from( Team.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Team> teams = session.createQuery( query ).getResultList();

        session.close();
        return teams;
    }

    /**
     * Get team by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Team> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        log.debug("Searching for team with {} = {}, " + propertyName + ", " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Team> query = builder.createQuery( Team.class );
        Root<Team> root = query.from( Team.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Team> teams = session.createQuery( query ).getResultList();
        session.close();
        return teams;
    }
}
