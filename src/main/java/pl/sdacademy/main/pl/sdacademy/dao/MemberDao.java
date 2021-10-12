package pl.sdacademy.main.pl.sdacademy.dao;

import pl.sdacademy.main.pl.sdacademy.entity.Member;
import pl.sdacademy.main.pl.sdacademy.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

        List<Member> findAll() throws SQLException;
        Member findById(int id) throws SQLException;
        void save (Member member) throws SQLException;
        void update(Member member) throws SQLException;
        void deleteById(int id) throws SQLException;
}
