package models;

import org.junit.After;
import org.junit.Before;

public class ServiceTest {
    @Before
    public void setup() throws Exception{

    }
    @After
    public void tearDown() throws Exception{

    }
    //
    public Service setupService() throws Exception{
        return new Service("maintenance");
    }
}
