package com.example.alviraputri.smartrecipe;

public class Discover {
    private String judul;
    private String user;
    private String level;
    private int id;
    private int cate;
    private String ing;
    private String ins;

    public Discover() {
    }

    public Discover(int id, String judul, String user, String level, String ing, String ins, int cate) {
        this.judul = judul;
        this.user = user;
        this.level = level;
        this.id = id;
        this.ing = ing;
        this.ins = ins;
        this.cate = cate;
    }

    //Getter
    public Integer getID() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getIns() {
        return ins;
    }

    public String getIng() {
        return ing;
    }

    public String getUser() {
        return user;
    }

    public int getCate() {
        return cate;
    }

    public String getLevel() {
        return level;
    }

    //Setter
    public void setID(int id) {
        this.id = id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public void setCate(int ins) {
        this.cate = cate;
    }
}