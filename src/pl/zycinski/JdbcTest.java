package pl.zycinski;

import pl.zycinski.dao.Dao;
import pl.zycinski.dao.DaoImpl;
import pl.zycinski.model.Courses;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Dominik Zycinski
 * @version 1.01, 27/03/2018
 */


public class JdbcTest {

    static {
        try {
            System.out.println("--- REJESTROWANIE STEROWNIKA...");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Niezarejestrowany sterownik");
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        Courses c1 = new Courses("AJ","Akademia Javy", 30, LocalDate.of(2018,2,12));
        Courses c2 = new Courses("JPR01","Podstawy Javy - poziom 1", 5, LocalDate.of(2018,3,5));
        Courses c3 = new Courses("JPR01","Podstawy Javy - poziom 2", 5, LocalDate.of(2018,3,12));



        Dao dao = new DaoImpl();
        try {

            dao.open();
            dao.saveCourses(c1, c2, c3);
            List<Courses> courses = dao.getCourses();
            System.out.println(courses);
            dao.close();
        } catch (SQLException e) {
            System.out.print("Błąd bazy danych: " + e.getMessage());
        }
    }

}

