package pl.sdacademy.main.pl.sdacademy.daoimplements;

import pl.sdacademy.main.pl.sdacademy.dao.RunDao;
import pl.sdacademy.main.pl.sdacademy.entity.Run;
import pl.sdacademy.main.pl.sdacademy.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImplements implements RunDao {

    @Override
    public void save(Run run) throws SQLException {

        PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                prepareStatement("insert into RUNS (id, name, members_limit) values (?, ?, ?)");

        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setInt(3, run.getMembersLimit());
        statement.execute();
        statement.close();
    }

    @Override
    public List<Run> findAll() throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from RUNS");

        List<Run> list = new ArrayList<>();

        while (resultSet.next()) {
           Run run = new Run();
           run.setId(resultSet.getInt("id"));
           run.setMembersLimit(resultSet.getInt("members_limit"));
           run.setName(resultSet.getString("name"));
           list.add(run);
        }
        return list;
    }

    @Override
    public Run findById(int id) throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from RUNS where id=" +id);

        if (resultSet.next()) {
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
            return run;
        }
        return null;
    }

    @Override
    public void update(Run run) throws SQLException {

        PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                prepareStatement("update RUNS set MEMBERS_LIMIT=? where ID=?");

        statement.setInt(1, run.getMembersLimit());
        statement.setInt(2, run.getId());
    }

    @Override
    public void deleteById(int id) throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();
            statement.executeUpdate("delete from runs where id=" + id);
        }
    }
