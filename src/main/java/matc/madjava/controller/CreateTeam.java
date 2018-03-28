package matc.madjava.controller;

import matc.madjava.entity.*;
import matc.madjava.persistence.*;

import javax.servlet.RequestDispatcher;
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
        urlPatterns = {"/createTeam"},
        name = "createTeam"
)

public class CreateTeam extends HttpServlet {

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

        teamDAO = new TeamDAO();
        powerForwardDAO = new PowerForwardDAO();
        pointGuardDAO = new PointGuardDAO();
        shootingGuardDAO = new ShootingGuardDAO();
        smallForwardDAO = new SmallForwardDAO();
        centerDAO = new CenterDAO();

        List<PointGuard> pointGuards = pointGuardDAO.getAllPointGuards();
        List<ShootingGuard> shootingGuards = shootingGuardDAO.getAllShootingGuards();
        List<SmallForward> smallForwards = smallForwardDAO.getAllSmallForwards();
        List<PowerForward> powerForwards = powerForwardDAO.getAllPowerForwards();
        List<Center> centers = centerDAO.getAllCenters();

        List<String> pgByName = new ArrayList<>();
        List<String> sgByName = new ArrayList<>();
        List<String> sfByName = new ArrayList<>();
        List<String> pfByName = new ArrayList<>();
        List<String> cByName = new ArrayList<>();

        for (PointGuard pg : pointGuards) {
            pgByName.add(pg.toString());
        }

        for (ShootingGuard sg : shootingGuards) {
            sgByName.add(sg.toString());
        }
        for (SmallForward sf : smallForwards) {
            sfByName.add(sf.toString());
        }
        for (PowerForward pf : powerForwards) {
            pfByName.add(pf.toString());
        }

        for (Center c : centers) {
            cByName.add(c.toString());
        }
        httpSession.setAttribute("pg_data", pointGuards);
        httpSession.setAttribute("sg_data", shootingGuards);
        httpSession.setAttribute("sf_data", smallForwards);
        httpSession.setAttribute("pf_data", powerForwards);
        httpSession.setAttribute("c_data", centers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("basketballApp/createTeam.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        teamDAO = new TeamDAO();
        team = new Team();

        pointGuard = new PointGuard();
        shootingGuard = new ShootingGuard();
        smallForward = new SmallForward();
        powerForward = new PowerForward();
        center = new Center();

        teamDAO = new TeamDAO();
        powerForwardDAO = new PowerForwardDAO();
        pointGuardDAO = new PointGuardDAO();
        shootingGuardDAO = new ShootingGuardDAO();
        smallForwardDAO = new SmallForwardDAO();
        centerDAO = new CenterDAO();

        int idPG = Integer.parseInt(req.getParameter("pointGuard"));
        int idSG = Integer.parseInt(req.getParameter("shootingGuard"));
        int idSF = Integer.parseInt(req.getParameter("smallForward"));
        int idPF = Integer.parseInt(req.getParameter("powerForward"));
        int idC = Integer.parseInt(req.getParameter("center"));

        String teamName = req.getParameter("teamName");
        PointGuard playerOnePG = pointGuardDAO.getPointGuardByID(idPG);
        ShootingGuard playerTwoSG = shootingGuardDAO.getShootingGuardByID(idSG);
        SmallForward playerThreeSF = smallForwardDAO.getSmallForwardByID(idSF);
        PowerForward playerFourPF = powerForwardDAO.getPowerForwardByID(idPF);
        Center playerFiveC = centerDAO.getCenterByID(idC);

        team.setTeamName(teamName);
        team.setPointGuard(playerOnePG);
        team.setShootingGuard(playerTwoSG);
        team.setSmallForward(playerThreeSF);
        team.setPowerForward(playerFourPF);
        team.setCenter(playerFiveC);


        teamDAO.insertTeam(team);

        httpSession.setAttribute("addTeamMessage", teamName + " was added");

        resp.sendRedirect("/createResults");

    }
}
