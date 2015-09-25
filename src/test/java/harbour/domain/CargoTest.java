package harbour.domain;

import harbour.config.factory.CargoFactory;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

//import org.junit.Test;
//import javax.validation.constraints.AssertFalse;

/**
 * Author P.Qhu on 2015/08/15.
 */
public class CargoTest {
    //@Test
    public void testCreate() throws Exception {
        Map<String, Integer> size = new HashMap<String, Integer>();

        size.put("breadth", 200);
        size.put("height", 200);
        size.put("length", 200);
        size.put("load", 300);

        Map<String, String> detail= new HashMap<String, String>();
        detail.put("name", "Waves");
        detail.put("code", "CX101");

        Cargo cargo = CargoFactory.createCargo(size, detail, null);

        Assert.assertEquals("CX101", cargo.getCargoCode());
        Assert.assertEquals("Waves", cargo.getName());

    }
     //   @Test
    public void testUpdate() throws Exception {
        Map<String, Integer> size = new HashMap<String, Integer>();

        size.put("breadth", 200);
        size.put("height", 200);
        size.put("length", 200);
        size.put("load", 300);

        Map<String, String> detail= new HashMap<String, String>();
        detail.put("name", "Waves");
        detail.put("code", "CX101");

        Cargo cargo = CargoFactory.createCargo(size, detail, null);
        Cargo newCargo = new Cargo.Builder(cargo.getCargoCode())
                .copy(cargo)
                .load(6000)
                .build();

        Assert.assertEquals("CX101", newCargo.getCargoCode());
            Assert.assertEquals(6000, newCargo.getContainerLoad());
            Assert.assertEquals(300, cargo.getContainerLoad());

        }
}
