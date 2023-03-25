package dao;

import models.Client;
import models.Service;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oServiceDaoTest {
    private static Connection con;
    private Sql2oServiceDao serviceDao;
    private  Sql2oClientDao clientDao;
    @Before
    public void setup() throws Exception{
        String connectionString = "jdbc:postgresql://localhost:5432/garage_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        serviceDao = new Sql2oServiceDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() throws Exception{
        serviceDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        con.close();
        System.out.println("closing database");
    }
    @Test
    public void setServiceIdSetsId() throws Exception{
        Service service= setupService();
        assertEquals(1, service.getId());
    }
    //HELPERS
    public Service setupService() throws Exception{
        return new Service("dashboard");
    }
    public Client setupClient() throws Exception{
        Client client = new Client("kajela","titoyut56@gmail.com","0717553340","x6",1);
        clientDao.add(client);
        return client;
    }
}
