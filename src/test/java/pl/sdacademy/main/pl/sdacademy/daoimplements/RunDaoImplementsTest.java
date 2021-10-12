package pl.sdacademy.main.pl.sdacademy.daoimplements;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import pl.sdacademy.main.pl.sdacademy.dao.RunDao;
import pl.sdacademy.main.pl.sdacademy.entity.Run;
import pl.sdacademy.main.pl.sdacademy.jdbc.utils.JdbcUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.RuleBasedCollator;
import java.util.List;

class RunDaoImplementsTest {

    private RunDao runDao = new RunDaoImplements();

    @BeforeEach
    void setUp(){
        Statement statement = null;
        try {
            statement = JdbcUtils.getINSTANCE().getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate("delete from RUNS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void save() {

        try {
            Run run = new Run();
            run.setId(1);
            run.setName("Bieg testowy");
            run.setMembersLimit(999);
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());

        }catch (SQLException e) {
            fail(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findAll() {
        try{
        Run run1 = new Run();
        run1.setId(1);
        run1.setName("bieg 1");
        run1.setMembersLimit(1);

        Run run2 = new Run();
        run2.setId(2);
        run2.setName("bieg 2");
        run2.setMembersLimit(2);

        runDao.save(run1);
        runDao.save(run2);

            List<Run> list = runDao.findAll();

            assertNotNull(list);
            assertEquals(2, list.size());

            Run foundRun1 = null;
            if (list.get(0).getId() == run1.getId()) {
                foundRun1 = list.get(0);
            }else {
                foundRun1 = list.get(0);
            }
            assertNotNull(foundRun1);
            assertEquals(run1.getId(), foundRun1.getId());
            assertEquals(run1.getName(), foundRun1.getName());
            assertEquals(run1.getMembersLimit(), foundRun1.getMembersLimit());
            }catch (SQLException e){
            fail(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findById() {
        fail();
    }

    @org.junit.jupiter.api.Test
    void update() {
        try {
            Run run = new Run();
            run.setId(100);
            run.setName("cos");
            run.setMembersLimit(999);

            runDao.save(run);
            run.setName("zmiana");
            run.setMembersLimit(99);
            runDao.update(run);

            Run updated = runDao.findById(run.getId());
            assertNotNull(updated);
            assertEquals(run.getName(), updated.getName());
            assertEquals(run.getId(), updated.getId());
            assertEquals(run.getMembersLimit(), updated.getMembersLimit());
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void deleteById() {
        try {
            Run run = new Run();
            run.setId(1);
            run.setName("test name");
            run.setMembersLimit(100);

            runDao.save(run);
            runDao.deleteById(run.getId());

            Run deleted = runDao.findById(run.getId());
            assertNull(deleted);
        }catch (SQLException e) {
            fail(e);
        }
    }
}