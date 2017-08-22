package com.picadito.picadito.Model;

import android.provider.Settings;
import android.provider.SyncStateContract;

import com.picadito.picadito.Constants.StatusConstants;
import com.picadito.picadito.Constants.TeamConstants;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.GUI.UserGUI;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Agustin Lavarello on 6/27/2017.
 */
public class Match implements Comparable<Match> , Serializable{

    private Date date;
    private Team team1;
    private Team team2;
    private UserGUI user;
    private String status;
    private String name;
    private double price;
    private boolean isPrivate; //if it's a private match or a public one

    public Match(UserGUI user,Date date, String name, int numberOfPlayers, double price){
        this.user = user;
        this.date = date;
        this.status = StatusConstants.PENDING;
        this.name = name;
        this.team1 = new Team(user,numberOfPlayers);
        this.team2 = new Team(user, numberOfPlayers);
        this.price = price;
        
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    @Override
    /*
    A user can't have at the same time  match
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (!date.equals(match.date)) return false;
        return user.equals(match.user);

    }

    public double getPrice(){return price;}
    @Override
    //hay que escribir uno mejor
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    public Date getDate() {
        return date;
    }

    /*
    If the date is privious than the current date, this method
    throws IllegalArgumenException
     */
    public void setDate(Date date) {
        Calendar c = Calendar.getInstance();
        if(date.compareTo(c.getTime())< 0){
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    /*
    Only the user can modify the first team
     */
    public boolean addPlayerToTeam1(UserGUI player) {
        boolean rta = this.team1.addPlayer(player);
        if(rta && team1.isComplete() && team2.isComplete()){
            this.status = StatusConstants.COMPLETE;
        }
        return rta;
    }

    public Team getTeam2() {
        return team2;
    }

    /*
    only the user can modify the second team, with aporval of the second team
     */
    public boolean addPlayerToTeam2(UserGUI player) {
       boolean rta = team2.addPlayer(player);
        if(rta && team1.isComplete() && team2.isComplete()){
            this.status = StatusConstants.COMPLETE;
        }
        return rta;
    }

    public boolean removeTeam(){
        if(team2 == null){
            return false;
        }
        changeStatus();
        team2 = null;
        return true;
    }

    public UserGUI getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    private void changeStatus() {
        if( this.status.equals(StatusConstants.PENDING)){
            this.status = StatusConstants.COMPLETE;
        }
        else {
            this.status = StatusConstants.PENDING;
        }
    }

    public boolean isPrivate(){
        return isPrivate;
    }

    public void setPrivacy(boolean isPrivate){
        this.isPrivate = isPrivate;
    }

    @Override
    public String toString() {
        return "date=" + date.toString() + "  " + status;
    }

    @Override
    public int compareTo(Match o) {
        return o.getDate().compareTo(this.date);
    }

    public MatchGUI getGUI(){
        return new MatchGUI(date,team1.getGUI(),team2.getGUI(),user,status,name);
    }
    /*
    The teams only exits in the context of a match
     */
    private class Team implements Serializable{

        private int teamSize;
        private UserGUI captain;
        private LinkedList<UserGUI> players = new LinkedList<UserGUI>();

        public Team(UserGUI captain, int size){
            this.captain = captain;
            if(size != TeamConstants.SixTeam && size != TeamConstants.NineTeam){
                throw new IllegalArgumentException();
            }
            this.teamSize = size;
            players.add(captain);
        }

        public Team(int size){
            if(size != TeamConstants.SixTeam || size != TeamConstants.NineTeam){
                throw new IllegalArgumentException();
            }
            this.teamSize = size;
        }

        public boolean addPlayer(UserGUI player){
            if(players.size() >= teamSize || players.contains(player)){
                return false;
            }
            if(players.isEmpty()){
                captain = player;
            }
            players.add(player);
            return true;
        }

        public void remove(UserGUI player){
            if(captain.equals(player)) {
                this.captain = players.getFirst();
            }
            players.remove(player);
        }

        public boolean isComplete(){
            if(teamSize == players.size()){
                return true;
            }
            return false;
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

        public boolean setCaptain(UserGUI player){
            if(!players.contains(player)){
                return false;
            }
            captain = player;
            return true;
        }

        public MatchGUI.TeamGUI getGUI(){
            return new MatchGUI.TeamGUI(teamSize,captain,players);
        }


    }


}
