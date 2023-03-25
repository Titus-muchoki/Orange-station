package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MechanicTest {
    @Before
    public void setup()throws Exception{

    }
    @After
    public void tearDown()throws Exception{

    }
    @Test
    public void getNameReturnsCorrectly()throws Exception{
        Mechanic mechanic = setupMechanic();
        assertEquals("kajela", mechanic.getName());
    }
    @Test
    public void getChargesReturnsCorrectly()throws Exception{
        Mechanic mechanic = setupMechanic();
        assertEquals("200", mechanic.getCharges());
    }
    //HELPER
    public Mechanic setupMechanic() throws Exception{
        return new Mechanic("kajela","200",1);
    }
}
