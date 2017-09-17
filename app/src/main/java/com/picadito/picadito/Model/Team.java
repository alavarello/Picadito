package com.picadito.picadito.Model;

import com.picadito.picadito.Constants.TeamConstants;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by agust on 8/31/2017.
 */

public class Team implements Serializable {

        private Double teamSize;
        private String captain;
        private String teamID;
        private List<String> players = new LinkedList<String>();

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public Team(String captain, Double size) {
            this.captain = captain;
            if (size != TeamConstants.SixTeam && size != TeamConstants.NineTeam) {
                throw new IllegalArgumentException();
            }
            this.teamSize = size;
            players.add(captain);
        }

        public Team(Double size) {
            if (size != TeamConstants.SixTeam || size != TeamConstants.NineTeam) {
                throw new IllegalArgumentException();
            }
            this.teamSize = size;
        }

        public boolean addPlayer(String player) {
            if (players.size() >= teamSize || players.contains(player)) {
                return false;
            }
            if (players.isEmpty()) {
                captain = player;
            }
            players.add(player);
            return true;
        }

        public void remove(String player) {
            if (captain.equals(player)) {
                this.captain = players.get(0);
            }
            players.remove(player);
        }

        public void setTeamSize(Double teamSize) {
            this.teamSize = teamSize;
        }

        public void setPlayers(List<String> players) {
            this.players = players;
        }

        public boolean isComplete() {
            if (teamSize == players.size()) {
                return true;
            }
            return false;
        }

        public Double getTeamSize() {
            return teamSize;
        }

        public String getCaptain() {
            return captain;
        }

        public List<String> getPlayers() {
            return players;
        }

        public boolean setCaptain(String player) {
            if (!players.contains(player)) {
                return false;
            }
            captain = player;
            return true;
        }
    }
