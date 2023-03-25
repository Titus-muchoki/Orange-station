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
import static org.junit.Assert.assertNotEquals;

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
        assertEquals(0, service.getId());
    }
    @Test
    public void getAllServicesReturnsAll()throws Exception{
        Service service = setupService();
        Service otherService = setupService();
        assertEquals(0, serviceDao.getAll().size());
        assertEquals(0, serviceDao.getAll().size());
    }
    @Test
    public void noServiceReturnsEmptyList() throws Exception{
        assertEquals(0, serviceDao.getAll().size());
    }
    //HELPERS
    public Service setupService() throws Exception{
        return new Service("dashboard");
    }
    @Test
    public void existingServicesCanBeFoundById()throws Exception{
        Service service = setupService();
        Service otherService = setupService();
        serviceDao.findById(service.getId());
        serviceDao.findById(otherService.getId());
        assertNotEquals(service, serviceDao.getAll().size());
        assertNotEquals(otherService, serviceDao.getAll().size());
    }
    @Test
    public void updateChangesServiceContent()throws Exception{
        String initialService = "redpill";
        Service service = new Service(initialService);
        serviceDao.add(service);
        serviceDao.update(service.getId(),"kajela");
        Service updatedService = serviceDao.findById(service.getId());
        assertNotEquals(initialService, updatedService.getName());
    }
    @Test
    public void deleteByIdDeletesCorrectly() throws Exception{
            Service service = setupService();
            Service otherService = setupService();
            serviceDao.deleteById(service.getId());
            serviceDao.deleteById(otherService.getId());
            assertEquals(service, serviceDao.getAll().size());
            assertEquals(service, serviceDao.getAll().size());
    }
    public Client setupClient() throws Exception{
        Client client = new Client("kajela","titoyut56@gmail.com","0717553340","x6",1);
        clientDao.add(client);
        return client;
    }
}
