package pl.zycinski.dao;


import pl.zycinski.model.Courses;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dominik Zycinski
 * @version 1.01, 27/03/2018
 */



public class DaoImpl implements Dao {
    private Connection conn;
    private Statement st;

    private void initConnection() throws SQLException {
        final String HOST = "localhost";
        final int PORT = 3306;
        final String DB_NAME = "aj";
        final String USER_NAME = "root";
        final String PASSWORD = "admin";
        String dburl = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        conn = DriverManager.getConnection(dburl, USER_NAME, PASSWORD);
        System.out.print("--- POŁĄCZENIE NAWIĄZANE... ");
        st = conn.createStatement();
    }

    private void createTable() throws SQLException {
        System.out.println("\n--- TWORZENIE TABELI...");
        final String SQL_CREATE = "CREATE TABLE courses ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "code VARCHAR(255),"
                + "name VARCHAR(255),"
                + "days INT,"
                + "date DATE,"
                + "PRIMARY KEY (id))";
        st.executeUpdate(SQL_CREATE);
    }


    @Override
    public void open() throws SQLException {
        initConnection();
         createTable();

    }


    @Override
    public void saveCourses(Courses... courses) throws SQLException {
        System.out.println("--- WSTAWIANIE DANYCH...");

        //licznik dla dodawanych rekordow
        int rowAdd = 0;

        for (Courses c : courses) {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO courses VALUES (?,?,?,?,?)");
            ps.setInt(1,c.getId());
            ps.setString(2,c.getCode());
            ps.setString(3,c.getName());
            ps.setInt(4,c.getDays());
            ps.setDate(5, Date.valueOf(c.getDate()));
            rowAdd+=ps.executeUpdate();
        }
        System.out.println("Liczba dodanych rekordow: "+rowAdd);
    }

    @Override
    public List<Courses> getCourses() throws SQLException {
        System.out.println("\n--- ODCZYT DANYCH Z TABELI...");
        final String SQL_SELECT = "SELECT * FROM courses";

        List<Courses> courses = new ArrayList<>();

        //licznik dla odczytanych rekordow
        int rowReaden=0;

        try (ResultSet rs = st.executeQuery(SQL_SELECT)) {


            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            System.out.printf("\nTabela 'courses' zawiera %d kolumn o nazwach: ", columns);
            for (int i = 1; i <= columns; i++) {
                System.out.print(rsmd.getColumnName(i) + " ");
            }


            while (rs.next()) {



                Courses c = new Courses();
                c.setId(rs.getInt(1));
                c.setCode(rs.getString(2));
                c.setName(rs.getString(3));
                c.setDays(rs.getInt(4));
                c.setDate(LocalDate.parse(rs.getString(5)));


                courses.add(c);

                rowReaden= rs.getRow();

            }
            System.out.println("\nLiczba odczytanych rekordow: "+rowReaden);

        }

        return courses;
    }


    private void dropTable() throws SQLException {
        System.out.println("\n--- USUNIĘCIE TABELI...");
        final String SQL_DROP = "DROP TABLE courses";
        st.executeUpdate(SQL_DROP);
    }

    private void closeConnection() {
        try {
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        dropTable();
        closeConnection();
        System.out.println("--- POŁĄCZENIE ZAMKNIĘTE...");
    }

}

