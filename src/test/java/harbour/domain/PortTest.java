package harbour.domain;

import harbour.config.factory.PortFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

//import org.testng.annotations.*;

/**
 * Author P.Qhu  on 2015/08/15.
 */
public class PortTest {

    //@Test
    public void testCreate() throws Exception {

        Map<String, String> portdetail = new HashMap<String, String>();
        portdetail.put("code","PACT700" );
        portdetail.put("name", "Cape Town Port");
        portdetail.put("type", "General");


        Port port = PortFactory.createPort(portdetail, null,
                null, null, null, null);

        Assert.assertEquals("PACT700", port.getPortCode());
    }

    //@Test
    public void testUpdate() throws Exception {
        Map<String, String> portdetail = new HashMap<String, String>();
        portdetail.put("code","PACT700" );
        portdetail.put("name", "Cape Town Port");
        portdetail.put("type", "General");

        Port port = PortFactory.createPort(portdetail, null,
                null, null, null, null);

        Port newPort = new Port.Builder(port.getPortCode())
                .copy(port)
                .portType("Shipping")
                .name("Durban")
                .build();

        Assert.assertEquals("General", port.getPortType());
        Assert.assertEquals("Shipping", newPort.getPortType());

        Assert.assertEquals("Durban", newPort.getName() );
    }
}
