package matc.madjava.persistence;

import matc.madjava.entity.Team;
import matc.madjava.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamDAOTest {
    TeamDAO teamDAO;
    GenericDAO genericDAO;

    @BeforeEach
    void setUp() {
        Database database = matc.madjava.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        teamDAO = new TeamDAO();
        genericDAO = new GenericDAO(Team.class);
    }

    @Test
    void getAllTeams() {
    }

    @Test
    void getTeamByID() {
    }

    @Test
    void updateTeam() {
    }

    @Test
    void deleteTeamByID() {
    }

    @Test
    void insertTeam() {
    }

    @Test
    void getByPropertyEqual() {
    }

    @Test
    void getByPropertyLike() {
    }
}