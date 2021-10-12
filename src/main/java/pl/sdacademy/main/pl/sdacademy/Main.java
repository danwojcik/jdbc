package pl.sdacademy.main.pl.sdacademy;

import pl.sdacademy.main.pl.sdacademy.jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

        printAllRecordsFromRun();
    }

    private static void runInsertTest() throws SQLException {

        PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                prepareStatement("insert into runs(id, name, members_limit) values (?, ?, ?)");

        statement.setInt(1, 3);
        statement.setString(2, "Bieg Niepodleglosci");
        statement.setInt(3, 150);
        statement.execute();
    }
        private static void updateRunNumbersLimit (int runId, int membersLimit) throws SQLException {

            PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                    prepareStatement("update RUNS set MEMBERS_LIMIT=? where ID=?");

            statement.setInt(1, membersLimit);
            statement.setInt(2, runId);
            statement.execute();
            statement.close();
        }

        private static void printAllRecordsFromRun () throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from RUNS");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int membersLimit = resultSet.getInt("members_limit");
            String runName = resultSet.getString("name");
            System.out.println(id + " " + runName + " " + membersLimit);
        }
        statement.close();
        }
    }