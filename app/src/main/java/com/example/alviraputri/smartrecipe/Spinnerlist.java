package com.example.alviraputri.smartrecipe;

public class Spinnerlist {
    private String sname;
    private int sval;


    public Spinnerlist(String sname, int sval) {
        this.sname = sname;
        this.sval = sval;
    }

    public String getSname() {
        return sname;
    }

    public int getSval() {
        return sval;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSval(int sval) {
        this.sval = sval;
    }

    public String toString() {
        return sname;
    }
}
