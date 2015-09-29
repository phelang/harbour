package harbour.domain;

import harbour.config.factory.WareHouseFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

//import org.testng.annotations.*;

/**
 * Author P.Qhu  on 2015/08/15.
 */
public class WarehouseTest {
    //@Test
    public void testCreate() throws Exception {

        Map<String, String> warehouseDetail = new HashMap<String, String>();

        warehouseDetail.put("code", "WE001");
        warehouseDetail.put("name", "Frenzy Warehouse");

        Warehouse warehouse = WareHouseFactory.createWareHouse(warehouseDetail, 20000, null);
        Assert.assertEquals("WE001", warehouse.getWarehouseCode());
        Assert.assertEquals("Frenzy Warehouse", warehouse.getName());
    }


    //@Test
    public void testUpdate() throws Exception {

        Map<String, String> warehouseDetail = new HashMap<String, String>();

        warehouseDetail.put("code", "WE001");
        warehouseDetail.put("name", "Frenzy Warehouse");

        Warehouse warehouse = WareHouseFactory.createWareHouse(warehouseDetail, 20000, null);


        Warehouse newWarehouse = new Warehouse.Builder(warehouse.getWarehouseCode())
                .copy(warehouse)
                .name("Frenzy Solutions")
                .build();

        Assert.assertEquals("Frenzy Warehouse", warehouse.getName());
        Assert.assertEquals("WE001", newWarehouse.getWarehouseCode());
        Assert.assertEquals("Frenzy Solutions", newWarehouse.getName());

    }

    /*
     @Test
    public void testUpdate() throws Exception {

    }
     @Test
    public void testCreate() throws Exception {
    }
     */

}
