package pl.sdacademy.main.pl.sdacademy.daoimplements;

import pl.sdacademy.main.pl.sdacademy.dao.MemberDao;
import pl.sdacademy.main.pl.sdacademy.entity.Member;
import pl.sdacademy.main.pl.sdacademy.entity.Run;
import pl.sdacademy.main.pl.sdacademy.jdbc.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImplements implements MemberDao {

    @Override
    public void save(Member member) throws SQLException {

        PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                prepareStatement("insert into MEMBERS (id, name, start_number, run_id) values (?, ?, ?, ?)");

        statement.setInt(1, member.getId());
        statement.setString(2, member.getName());
        statement.setInt(3, member.getStart_number());
        statement.setInt(4, member.getRun_id());
        statement.execute();
        statement.close();
    }

    @Override
    public List<Member> findAll() throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from MEMBERS");

        List<Member> list = new ArrayList<>();

        while (resultSet.next()) {

            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            member.setStart_number(resultSet.getInt("start_number"));
            member.setRun_id(resultSet.getInt("run_id"));
            list.add(member);
        }
        return list;
    }

    @Override
    public Member findById(int id) throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from MEMBERS where id=" +id);

        if (resultSet.next()) {
            Member member = new Member();
            member.setId(resultSet.getInt("id"));
            member.setName(resultSet.getString("name"));
            member.setStart_number(resultSet.getInt("start_number"));
            member.setRun_id(resultSet.getInt("run_id"));
            return member;
        }
        return null;
    }

    @Override
    public void update(Member member) throws SQLException {

        PreparedStatement statement = JdbcUtils.getINSTANCE().getConnection().
                prepareStatement("update MEMBERS set RUN_ID=?,  START_NUMBER=?, NAME=? where ID=?");

        statement.setInt(1, member.getRun_id());
        statement.setInt(2, member.getStart_number());
        statement.setString(3, member.getName());
        statement.setInt(4, member.getId());
        statement.execute();
    }

    @Override
    public void deleteById(int id) throws SQLException {

        Statement statement = JdbcUtils.getINSTANCE().getConnection().createStatement();
        statement.executeUpdate("delete from MEMBERS where id=" + id);
    }
}
