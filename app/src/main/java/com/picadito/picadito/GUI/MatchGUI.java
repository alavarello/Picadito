package com.picadito.picadito.GUI;

import com.picadito.picadito.Constants.StatusConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MatchGUI implements Comparable<MatchGUI> , Serializable{

    private Date date;
    private TeamGUI team1;
    private TeamGUI team2;
    private UserGUI user;
    private String status;
    private String name;

    public MatchGUI(Date date, TeamGUI team1, TeamGUI team2, UserGUI user, String status, String name) {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.user = user;
        this.status = status;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public TeamGUI getTeam1() {
        return team1;
    }

    public TeamGUI getTeam2() {
        return team2;
    }

    public UserGUI getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(MatchGUI o) {
        return date.compareTo(o.getDate());
    }

    public static class TeamGUI implements Serializable{

        private int teamSize;
        private UserGUI captain;
        private LinkedList<UserGUI> players = new LinkedList<UserGUI>();

        public TeamGUI(int teamSize, UserGUI captain, LinkedList<UserGUI> players) {
            this.teamSize = teamSize;
            this.captain = captain;
            this.players = players;
        }

        public int getTeamSize() {
            return teamSize;
        }

        public UserGUI getCaptain() {
            return captain;
        }

        public LinkedList<UserGUI> getPlayers() {
            return players;
        }
    }

}
