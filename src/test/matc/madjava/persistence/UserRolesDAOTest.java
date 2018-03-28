package matc.madjava.persistence;

import matc.madjava.entity.User;
import matc.madjava.entity.UserRoles;
import matc.madjava.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRolesDAOTest {

    private final Logger log = LogManager.getLogger(this.getClass());
    UserRolesDAO userRolesDAO;
    GenericDAO genericDAO;

    @BeforeEach
    void setUp() {
        Database database = matc.madjava.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        userRolesDAO = new UserRolesDAO();
        genericDAO = new GenericDAO(UserRoles.class);

        userRolesDAO = new UserRolesDAO();
    }

    /**
     * Verify successful retrieval of a user
     */
    @Test
    void getByIdSuccess() {
        UserRoles retrievedRole = (UserRoles)genericDAO.getByID(1);
        assertEquals("administrator", retrievedRole.getRoleName());
        assertEquals(1, retrievedRole.getUserRoleId());
        assertEquals("admin", retrievedRole.getUserName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("testname","testpass","Test","name","test@pass.com");
        UserRoles newRole = new UserRoles(1, "administrator", newUser, "testname");
        int id = userRolesDAO.insertUserRoles(newRole);
        assertNotEquals(0,id);
        UserRoles insertedRole = userRolesDAO.getUserRoleByID(id);
        assertEquals("administrator", insertedRole.getRoleName(), "role is not equal");
    }

    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        userRolesDAO.deleteUserRolesByID(userRolesDAO.getUserRoleByID(2));
        assertNull(userRolesDAO.getUserRoleByID(2));
    }

    /**
     * Verify successful retrieval of all users
     */
    @Test
    void getAllSuccess() {
        List<UserRoles> roles = userRolesDAO.getAllUserRoles();
        assertEquals(3, roles.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<UserRoles> roles = userRolesDAO.getByPropertyLike("userName", "admin");
        assertEquals(2, roles.size());
        assertEquals(1, roles.get(0).getUserRoleId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<UserRoles> roles = userRolesDAO.getByPropertyLike("userName", "a");
        for(UserRoles role : roles) {
            log.info(role.getUserName());
        }
        assertEquals(2, roles.size());
    }
}