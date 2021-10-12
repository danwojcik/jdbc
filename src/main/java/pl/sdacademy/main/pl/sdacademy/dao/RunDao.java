package pl.sdacademy.main.pl.sdacademy.dao;

import pl.sdacademy.main.pl.sdacademy.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {

    List<Run> findAll() throws SQLException;
    Run findById(int id) throws SQLException;
    void save (Run run) throws SQLException;
    void update(Run run) throws SQLException;
    void deleteById(int id) throws SQLException;
}
