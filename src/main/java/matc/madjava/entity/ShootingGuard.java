package matc.madjava.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "ShootingGuard")
@Table(name = "shooting_guard")
public class ShootingGuard {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "shootingguard_id")
    private int shootingGuardID;

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


    @OneToMany(mappedBy = "shootingGuard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Team> teams = new HashSet<>();

    //private Status status;

    public ShootingGuard(int shootingGuardID, String firstName, String lastName, int points, int assists,
                        int rebounds, int steals, int blocks, int turnovers){
        this.shootingGuardID = shootingGuardID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
    }

    public ShootingGuard() {

    }

    public int getShootingGuardID() {
        return shootingGuardID;
    }

    public void setShootingGuardID(int shootingGuardID) {
        this.shootingGuardID = shootingGuardID;
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
                "playerID='" + shootingGuardID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String toStringFull(){
        return "Player{" +
                "playerID='" + shootingGuardID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", playerType=" +
                '}';
    }

}