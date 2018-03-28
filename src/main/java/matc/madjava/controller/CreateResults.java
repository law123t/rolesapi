package matc.madjava.controller;


import matc.madjava.entity.*;
import matc.madjava.persistence.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for team
 * @author lthao
 */

@WebServlet(
        urlPatterns = {"/createResults"},
        name = "createResults"
)

public class CreateResults extends HttpServlet {

    private Center center;
    private PointGuard pointGuard;
    private ShootingGuard shootingGuard;
    private SmallForward smallForward;
    private PowerForward powerForward;
    private Team team;
    private TeamDAO teamDAO;
    private CenterDAO centerDAO;
    private PointGuardDAO pointGuardDAO;
    private ShootingGuardDAO shootingGuardDAO;
    private SmallForwardDAO smallForwardDAO;
    private PowerForwardDAO powerForwardDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        resp.sendRedirect("basketballApp/createTeam.jsp");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
