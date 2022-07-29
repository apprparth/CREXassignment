package com.example.crex;

public class Match {


    //    private String m;
    private String t1;
    private String t2;
    private String t1_flag;
    private String t2_flag;

    public String getClubbeddate() {
        return clubbeddate;
    }

    public void setClubbeddate(String clubbeddate) {
        this.clubbeddate = clubbeddate;
    }

    private String clubbeddate;

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT1_flag() {
        return t1_flag;
    }

    public void setT1_flag(String t1_flag) {
        this.t1_flag = t1_flag;
    }

    public String getT2_flag() {
        return t2_flag;
    }

    public void setT2_flag(String t2_flag) {
        this.t2_flag = t2_flag;
    }

    public String getMatch_no() {
        return match_no;
    }

    public void setMatch_no(String match_no) {
        this.match_no = match_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getOvers1() {
        return overs1;
    }

    public void setOvers1(String overs1) {
        this.overs1 = overs1;
    }

    public String getOvers2() {
        return overs2;
    }

    public void setOvers2(String overs2) {
        this.overs2 = overs2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRate_team() {
        return rate_team;
    }

    public void setRate_team(String rate_team) {
        this.rate_team = rate_team;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public Match(String clubbeddate,String t1, String t2, String t1_flag, String t2_flag, String match_no, String date, String t, String rate, String rate1, String rate2, String score1, String score2, String overs1, String overs2, String winner, String result, String rate_team) {
        this.t1 = t1;
        this.t2 = t2;
        this.clubbeddate=clubbeddate;
        this.t1_flag = t1_flag;
        this.t2_flag = t2_flag;
        this.match_no = match_no;
        this.date = date;
        this.t = t;
        this.rate = rate;
        this.rate1 = rate1;
        this.rate2 = rate2;
        this.score1 = score1;
        this.score2 = score2;
        this.overs1 = overs1;
        this.overs2 = overs2;
        this.winner = winner;
        this.result = result;
        this.rate_team = rate_team;
    }

    private String match_no;
    private String date;
    private String t;
    private String rate;
    private String rate1;
    private String rate2;
    private String score1;
    private String score2;
    private String overs1;
    private String overs2;
    private String winner;
    private String result;
    private String rate_team;


    @Override
    public String toString() {
        return "Match{" +
                "t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", t1_flag='" + t1_flag + '\'' +
                ", t2_flag='" + t2_flag + '\'' +
                ", clubbeddate='" + clubbeddate + '\'' +
                ", match_no='" + match_no + '\'' +
                ", date='" + date + '\'' +
                ", t='" + t + '\'' +
                ", rate='" + rate + '\'' +
                ", rate1='" + rate1 + '\'' +
                ", rate2='" + rate2 + '\'' +
                ", score1='" + score1 + '\'' +
                ", score2='" + score2 + '\'' +
                ", overs1='" + overs1 + '\'' +
                ", overs2='" + overs2 + '\'' +
                ", winner='" + winner + '\'' +
                ", result='" + result + '\'' +
                ", rate_team='" + rate_team + '\'' +
                '}';
    }
}
