package matc.madjava.rolesAPI;

import matc.madjava.entity.UserRoles;
import matc.madjava.persistence.UserRolesDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("roles")
public class RoleAPI {

        // The Java method will process HTTP GET requests
        @GET
        @Produces("text/plain")
        @Path("/all")
        public Response getRoles() {
            List<UserRoles> userRolesList = new ArrayList<UserRoles>();
            UserRolesDAO userRolesDAO = new UserRolesDAO();
            userRolesList = userRolesDAO.getAllUserRoles();
            String output = "";

            if(userRolesList.size() > 0) {
                for (UserRoles role : userRolesList) {
                    output += role.toString();
                }
                return Response.status(200).entity(output).build();
            } else {
                output = "Status 404: Roles Not Found";
                return Response.status(404).entity(output).build();
            }

        }

}

