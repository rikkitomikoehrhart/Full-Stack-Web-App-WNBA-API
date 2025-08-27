package com.rikkitomikoehrhart.league_standard.model;

public class Scoring {
    private int q1Points;
    private int q2Points;
    private int q3Points;
    private int q4Points;

    public Scoring() {};
    public Scoring(int q1Points, int q2Points, int q3Points, int q4Points) {
        this.q1Points = q1Points;
        this.q2Points = q2Points;
        this.q3Points = q3Points;
        this.q4Points = q4Points;
    }

    public int getQ1Points() {
        return q1Points;
    }

    public void setQ1Points(int q1Points) {
        this.q1Points = q1Points;
    }

    public int getQ2Points() {
        return q2Points;
    }

    public void setQ2Points(int q2Points) {
        this.q2Points = q2Points;
    }

    public int getQ3Points() {
        return q3Points;
    }

    public void setQ3Points(int q3Points) {
        this.q3Points = q3Points;
    }

    public int getQ4Points() {
        return q4Points;
    }

    public void setQ4Points(int q4Points) {
        this.q4Points = q4Points;
    }
}
