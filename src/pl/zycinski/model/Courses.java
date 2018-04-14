package pl.zycinski.model;

import java.time.LocalDate;

/**
 * @author Dominik Zycinski
 * @version 1.01, 27/03/2018
 */

public class Courses {


    private int id; // jednoznaczny identyfikator szkolenia
    private String code; // kod szkolenia
    private String name; // tytuł szkolenia
    private int days; // ilość dni
    private LocalDate date; // data rozpoczęcia



    public Courses(){

    }
    public Courses( String code, String name, int days, LocalDate date){

        this.code = code;
        this.name = name;
        this.days = days;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nCourse [" + " id = "+id + ", code = "+code+ ", name= "+ name + ", days = " + days + " date= " + date + "]";
    }
}
