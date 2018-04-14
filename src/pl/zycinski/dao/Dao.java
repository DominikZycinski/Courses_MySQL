package pl.zycinski.dao;

import pl.zycinski.model.Courses;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Dominik Zycinski
 * @version 1.01, 27/03/2018
 */


public interface Dao {

    void open() throws SQLException;

    void saveCourses(Courses... courses) throws SQLException;

    List<Courses> getCourses() throws SQLException;

    void close() throws SQLException;

}