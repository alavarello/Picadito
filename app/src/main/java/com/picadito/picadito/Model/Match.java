package com.picadito.picadito.Model;

import com.picadito.picadito.Constants.StatusConstants;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/27/2017.
 */
public class Match implements Comparable<Match> , Serializable{

    private String date;
    private String team1;
    private String team2;
    private String user;
    private String status;
    private String name;
    private String matchID;
    private double price;
    private double numberOfPlayers;
    private boolean isPrivate; //if it's a private match or a public one


    public double getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(double numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }


    public Match(){}
    public Match(String user,String date, String name, double numberOfPlayers, double price){
        this.user = user;
        this.date = date;
        this.status = StatusConstants.PENDING;
        this.name = name;
        this.price = price;
        this.numberOfPlayers = numberOfPlayers;
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

    public String getDate() {
        return date;
    }

    /*
    If the date is privious than the current date, this method
    throws IllegalArgumenException
     */
    public void setDate(String sDate) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date.compareTo(c.getTime())< 0){
            throw new IllegalArgumentException();
        }
        this.date = sDate;
    }

    public String getTeam1() {
        return team1;
    }

    /*
    Only the user can modify the first team
     */
    public boolean addPlayerToTeam1(String player) {
//        boolean rta = this.team1.addPlayer(player);
//        if(rta && team1.isComplete() && team2.isComplete()){
//            this.status = StatusConstants.COMPLETE;
//        }
        return true;
    }

    public String getTeam2() {
        return team2;
    }

    /*
    only the user can modify the second team, with aporval of the second team
     */
    public boolean addPlayerToTeam2(String player) {
//       boolean rta = team2.addPlayer(player);
//        if(rta && team1.isComplete() && team2.isComplete()){
//            this.status = StatusConstants.COMPLETE;
//        }
        return true;
    }

    public boolean removeTeam(){
        if(team2 == null){
            return false;
        }
        changeStatus();
        team2 = null;
        return true;
    }

    public String getUser() {
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


}
