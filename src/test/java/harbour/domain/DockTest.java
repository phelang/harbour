package harbour.domain;

import harbour.config.factory.CargoFactory;
import harbour.config.factory.DockFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

//import org.testng.annotations.*;


/**
 * Author P.Qhu  on 2015/08/15.
 */
public class DockTest {
    //@Test
    public void testCreate() throws Exception {

        Dock dock  = DockFactory.createDock("DC001", 400, 100, null);
        Assert.assertEquals("DC001", dock.getDockCode());
        Assert.assertEquals(null, dock.getCargo());
    }
    //@Test
    public void testUpdate() throws Exception {

        Map<String, Integer> size = new HashMap<String, Integer>();
        Map<String, String> detail = new HashMap<String, String>();

        detail.put("code", "CS1001");
        detail.put("name", "Jet");

        size.put("breadth", 400);
        size.put("height", 900);
        size.put("length", 1100);
        size.put("load", 15000);

        Cargo cargo = CargoFactory.createCargo(size, detail, null);

        Dock dock  = DockFactory.createDock("DC001", 400, 100, null);
        Dock newDock  = new Dock.Builder(dock.getDockCode())
                .copy(dock)
                .cargo(cargo)   // update new Cargo
                .build();

        Assert.assertNotNull(newDock.getCargo());
    }
}
