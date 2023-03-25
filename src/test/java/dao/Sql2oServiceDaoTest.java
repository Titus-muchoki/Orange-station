package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oServiceDaoTest {
    private Connection con;
    private Sql2oServiceDao serviceDao;
    @Before
    public void setup() throws Exception{
        String connectionString = "jdbc:postgresql://localhost:5432/garage_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        serviceDao = new Sql2oServiceDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception{
        serviceDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public void shutDown() throws Exception{
        con.close();
        System.out.println("closing database");
    }
}
