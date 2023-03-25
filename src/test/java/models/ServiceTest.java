package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Before
    public void setup() throws Exception{

    }
    @After
    public void tearDown() throws Exception{

    }
    @Test
    public void getNameReturnsCorrectly() throws Exception{
        Service service = setupService();
        assertEquals("maintenance", service.getName());
    }
    @Test
    public void setNameSetsCorrectly() throws Exception{
        Service service = setupService();
        service.setName("repair");
        assertEquals("repair", service.getName());
    }

    //
    public Service setupService() throws Exception{
        return new Service("maintenance");
    }
}
