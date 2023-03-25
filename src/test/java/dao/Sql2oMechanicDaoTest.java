package dao;

import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oMechanicDaoTest {
    private Connection conn;
    private Sql2oMechanicDao mechanicDao;
    private Sql2oClientDao clientDao;
    @Before
    public void setup() throws Exception{
        String connectionString = "jdbc:postgresql://localhost:5432/garage_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        mechanicDao = new Sql2oMechanicDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }
}
