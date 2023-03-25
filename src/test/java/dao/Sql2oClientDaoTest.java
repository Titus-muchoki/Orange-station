package dao;

import models.Client;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("closing database");
    }
    @Test
    public void addingClientsSetsId() throws Exception{
        Client client = setupClient();
        assertEquals(0, client.getId());

    }
    @Test
    public void addedClientsAreReturnedFromGetAll() throws Exception{
    Client client = setupClient();
    assertNotEquals(client, clientDao.getAll().size());
    }
    @Test
    public void noClientsReturnsEmptyList() throws Exception{
        assertEquals(0, clientDao.getAll().size());
    }

        //HELPERS
    public Client setupClient() throws Exception{
        Client client = new Client("kajela","titoyut56@gmail.com","0717553340","x6",1);
        return client;
    }
}
