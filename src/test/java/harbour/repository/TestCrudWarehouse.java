package harbour.repository;

import harbour.config.factory.PackageProductFactory;
import harbour.config.factory.WareHouseFactory;
import harbour.domain.PackageProduct;
import harbour.domain.Warehouse;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
/*import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import harborManagementSystem.App;*/

/**
 * Author P.Qhu  on 2015/08/11.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudWarehouse { //extends AbstractTestNGSpringContextTests{
    private Long id;
    private List<PackageProduct> packageProd;

    //@Autowired
    WarehouseRepository repository;


   // @Test
    public void create() throws Exception {
        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();

        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P102");
        detail.put("descr", "2015 Mercedes");
        detail.put("type", "Cars");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct product = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        packageProd.add(product);
        /*------------------------------*/

        Map<String, String> warehouseDetail = new HashMap<String, String>();

        warehouseDetail.put("code", "WE001");
        warehouseDetail.put("name", "Frenzy Warehouse");

        Warehouse warehouse = WareHouseFactory.createWareHouse(warehouseDetail, 20000, packageProd);
        this.repository.save(warehouse);
        id = warehouse.getId();
        Assert.assertNotNull(warehouse.getId());
    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Warehouse warehouse = repository.findOne(id);
        Assert.assertNotNull(warehouse);

    }
   // @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Warehouse warehouse = repository.findOne(id);
        Warehouse newWareHouse = new Warehouse.Builder(warehouse.getWarehouseCode())
                .copy(warehouse)
                .packageLoad(40000)
                .build();
        repository.save(newWareHouse);
        Warehouse updatedWareHouse = repository.findOne(id);
        Assert.assertEquals(40000, updatedWareHouse.getPackageLoad());

    }
   // @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Warehouse warehouse = repository.findOne(id);
        repository.delete(warehouse);
        Warehouse delWarehouse = repository.findOne(id);
        Assert.assertNull(delWarehouse);

    }
}
