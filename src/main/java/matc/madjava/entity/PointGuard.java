package matc.madjava.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "PointGuard")
@Table(name = "point_guard")
public class PointGuard {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "pointguard_id")
    private int pointGuardID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "points")
    private int points;

    @Column(name = "assists")
    private int assists;

    @Column(name = "rebounds")
    private int rebounds;

    @Column(name = "steals")
    private int steals;

    @Column(name = "blocks")
    private int blocks;

    @Column(name = "turnovers")
    private int turnovers;


    @OneToMany(mappedBy = "pointGuard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Team> teams = new HashSet<>();

    //private Status status;

    public PointGuard(int pointGuardID, String firstName, String lastName, int points, int assists,
                        int rebounds, int steals, int blocks, int turnovers){
        this.pointGuardID = pointGuardID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
    }

    public PointGuard() {

    }

    public int getPointGuardID() {
        return pointGuardID;
    }

    public void setPointGuardID(int pointGuardID) {
        this.pointGuardID = pointGuardID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    /**
     * Gets teams.
     *
     * @return the teams
     */
    public Set<Team> getTeams() {
        return teams;
    }

    /**
     * Sets teams.
     *
     * @param teams the teams
     */
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }


    @Override
    public String toString(){
        return "Player{" +
                "playerID='" + pointGuardID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String toStringFull(){
        return "Player{" +
                "playerID='" + pointGuardID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", playerType=" +
                '}';
    }

}