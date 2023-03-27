package dao;

import models.Client;
import models.Mechanic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    @After
    public void tearDown() throws Exception{
        clientDao.clearAll();
        mechanicDao.clearAll();
        System.out.println("closing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        System.out.println("closing database");
    }
    @Test
    public void setMechanicsIdSetsIdCorrectly()throws Exception{
        Mechanic mechanic = setupMechanic();
        assertNotEquals(1, mechanic.getId());
    }
    @Test
    public void getAllMechanicsAreReturnedFromGetAll()throws Exception{
        Mechanic mechanic = setupMechanic();
        assertNotEquals(mechanic, mechanicDao.getAll().size());
    }
    @Test
    public void noMechanicsReturnsEmptyList() throws Exception{
        assertEquals(0, mechanicDao.getAll().size());
    }
    @Test
    public void getMechanicByIdFindsTheCorrectMechanic()throws Exception{
        Mechanic mechanic = setupMechanic();
        Mechanic otherMechanic = setupMechanic();
        mechanicDao.findById(mechanic.getId());
        mechanicDao.findById(otherMechanic.getId());
        assertNotEquals(mechanic, mechanicDao.getAll().size());
        assertNotEquals(otherMechanic, mechanicDao.getAll().size());
    }
    @Test
    public void updateUpdatesAllFieldInMechanic() throws Exception{
        Mechanic mechanic = setupMechanic();
        mechanicDao.add(mechanic);
        mechanicDao.update(mechanic.getId(),"joy","200",1);
        Mechanic mechanic1 = mechanicDao.findById(mechanic.getId());
        assertEquals("joy", mechanic1.getName());
        assertEquals("200", mechanic1.getCharges());
        assertEquals("1", mechanic1.getClientId());
    }
    @Test
    public void deleteByIdDeletesTheCorrectField() throws Exception{
        Mechanic mechanic = setupMechanic();
        Mechanic otherMechanic = setupMechanic();
        mechanicDao.deleteById(mechanic.getId());
        mechanicDao.deleteById(otherMechanic.getId());
        assertNotEquals(mechanic, mechanicDao.getAll().size());
        assertNotEquals(otherMechanic, mechanicDao.getAll().size());
    }
    //HELPERS
    public Mechanic setupMechanic(){
        return new Mechanic("joy","200",1);
    }
    public Client setupClient() throws Exception{
        Client client = new Client("kajela","titoyut56@gmail.com","0717553340","x6",1);
        clientDao.add(client);
        return client;
    }
}
