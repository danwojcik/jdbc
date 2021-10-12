package pl.sdacademy.main.pl.sdacademy.daoimplements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.main.pl.sdacademy.dao.MemberDao;
import pl.sdacademy.main.pl.sdacademy.dao.RunDao;
import pl.sdacademy.main.pl.sdacademy.entity.Member;
import pl.sdacademy.main.pl.sdacademy.entity.Run;
import pl.sdacademy.main.pl.sdacademy.jdbc.utils.JdbcUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoImplementsTest {

    private MemberDao memberDao = new MemberDaoImplements();

    @BeforeEach
    void setUp(){
        Statement statement = null;
        try {
            statement = JdbcUtils.getINSTANCE().getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("delete from MEMBERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void save() {
        try {
            Member member = new Member();
            member.setId(1);
            member.setName("Bieg testowy");
            member.setStart_number(999);
            member.setRun_id(15);
            memberDao.save(member);
            Member saved = memberDao.findById(member.getId());

            assertNotNull(saved);
            assertEquals(member.getId(), saved.getId());
            assertEquals(member.getName(), saved.getName());
            assertEquals(member.getRun_id(), saved.getRun_id());
            assertEquals(member.getStart_number(), saved.getStart_number());


        }catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findAll() {
        try{
            Member member1 = new Member();
            member1.setId(1);
            member1.setName("bieg 1");
            member1.setStart_number(100);
            member1.setRun_id(13);


            Member member2 = new Member();
            member2.setId(2);
            member2.setName("bieg 2");
            member2.setStart_number(200);
            member2.setRun_id(15);

            memberDao.save(member1);
            memberDao.save(member2);

            List<Member> list = memberDao.findAll();

            assertNotNull(list);
            assertEquals(2, list.size());

            Member foundMember1 = null;
            if (list.get(0).getId() == member1.getId()) {
                foundMember1 = list.get(0);
            }else {
                foundMember1 = list.get(0);
            }
            assertNotNull(foundMember1);
            assertEquals(member1.getId(), foundMember1.getId());
            assertEquals(member1.getName(), foundMember1.getName());
            assertEquals(member1.getStart_number(), foundMember1.getStart_number());
            assertEquals(member1.getRun_id(), foundMember1.getRun_id());

        }catch (SQLException e){
            fail(e);
        }
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
        try {
            Member member = new Member();
            member.setId(100);
            member.setName("cos");
            member.setStart_number(123);
            member.setRun_id(45);


            memberDao.save(member);
            member.setName("zmiana");
            member.setRun_id(2);
            member.setStart_number(6);
            memberDao.update(member);

            Member updated = memberDao.findById(member.getId());
            assertNotNull(updated);
            assertEquals(member.getName(), updated.getName());
            assertEquals(member.getId(), updated.getId());
            assertEquals(member.getStart_number(), updated.getStart_number());
            assertEquals(member.getRun_id(), updated.getRun_id());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void deleteById() {
        try {
            Member member = new Member();
            member.setId(1);
            member.setName("test name");
            member.setStart_number(20);
            member.setRun_id(4);


            memberDao.save(member);
            memberDao.deleteById(member.getId());

            Member deleted = memberDao.findById(member.getId());
            assertNull(deleted);
        }catch (SQLException e) {
            fail(e);
        }
    }
}