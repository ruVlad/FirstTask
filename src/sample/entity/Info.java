package sample.entity;

public class Info {
    private int id;
    private String date;
    private String symbolRus;
    private String symbolEng;
    private int number;
    private double fractional;

//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbolRus() {
        return symbolRus;
    }

    public void setSymbolRus(String symbolRus) {
        this.symbolRus = symbolRus;
    }

    public String getSymbolEng() {
        return symbolEng;
    }

    public void setSymbolEng(String symbolEng) {
        this.symbolEng = symbolEng;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getFractional() {
        return fractional;
    }

    public void setFractional(double fractional) {
        this.fractional = fractional;
    }
}
