package dao;

import org.junit.After;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oClientDaoTest {
    private static Connection conn;
    private Sql2oClientDao clientDao;

    @Before
    public void setup()throws Exception{
        String connectionString = "jdbc:postgresql://localhost:5432/garage_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception{
        System.out.println("clearing database");
        clientDao.clearAll();
    }
}
