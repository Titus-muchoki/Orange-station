package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Before
    public void setup() throws Exception{

    }
    @After
    public void tearDown() throws Exception{

    }
    @Test
    public void getNameReturnsNameCorrectly() throws Exception{
        Client client = setupClient();
        assertEquals("gathoni", client.getName());
    }
    @Test
    public void getEmailReturnsEmailCorrectly() throws Exception{
        Client client = setupClient();
        assertEquals("titoyut56@gmail.com", client.getEmail());
    }
    @Test
    public void getTelReturnsTheCorrectTel() throws Exception{
        Client client = setupClient();
        assertEquals("0717553340", client.getTel());
    }
    @Test
    public void getCarReturnsCarCorrectly() throws Exception{
        Client client = setupClient();
        assertEquals("bmw", client.getCar());
    }
    @Test
    public void getServiceIdReturnsCorrectly() throws Exception{
        Client client = setupClient();
        assertEquals(1, client.getServiceId());
    }
    @Test
    public void setNameSetNameCorrectly() throws Exception{
        Client client = setupClient();
        client.setName("tito");
        assertEquals("tito", client.getName());
    }
    @Test
    public void setEmailSetsCorrectly() throws Exception{
        Client client = setupClient();
        client.setEmail("wambuchiri12@gmail.com");
        assertEquals("wambuchiri12@gmail.com", client.getEmail());
    }
    @Test
    public void setTelSetsTelCorrectly() throws Exception{
        Client client = setupClient();
        client.setTel("0776509158");
        assertEquals("0776509158", client.getTel());
    }
    @Test
    public void setCarSetsCorrectly() throws Exception{
        Client client = setupClient();
        client.setCar("bently");
        assertEquals("bently", client.getCar());
    }
    @Test
    public void setServiceIdSetsCorrectly() throws Exception{
        Client client = setupClient();
        client.setServiceId(1);
        assertEquals(1, client.getServiceId());
    }
    //HELPER
    public Client setupClient()throws Exception{
        return new Client("gathoni","titoyut56@gmail.com","0717553340","bmw",1);
    }
}
