package harbour.repository;

import harbour.config.factory.*;
import harbour.domain.*;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/*import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import harbour.App;*/

/**
 * Author P.Qhu  on 2015/08/11.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudPort {//extends AbstractTestNGSpringContextTests {
    private Long id;
    //@Autowired
    PortRepository repository;

    //@Test
    public void create() throws Exception {

        List<Container> containers = new ArrayList<Container> ();
        List<Terminal> terminals = new ArrayList<Terminal>();
        List<Warehouse> warehouses = new ArrayList<Warehouse>();
        List<LogisticCompany> logisticCompanies = new ArrayList<LogisticCompany>();
        List<Dock> docks = new ArrayList<Dock>();
        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();

        /*  CREATE LOCATION EMBEDDED OBJECT */
        Map<String, String> address = new HashMap<String, String>();
        address.put("tel", "+270218393600");
        address.put("continent", "AFR");
        address.put("country", "SA");
        address.put("city", "CT");
        address.put("code", "7000");


        Location location = LocationFactory.createLocation(address);
        /*------------------------------*/

        /*  CREATE CONTAINER ENTITY OBJECTS OBJECT */
        Map<String, Integer> sides = new HashMap<String, Integer>();

        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1001", null);
        Container container2 = ContainerFactory.createContainer(sides, "CO1002", null);

        containers.add(container);
        containers.add(container2);
        /*------------------------------*/

        /*  CREATE TERMINAL ENTITY OBJECTS */
        Terminal terminal = TerminalFactory.createTerminal("TSE01", "SE", 200, containers);
        terminals.add(terminal);
        /*------------------------------*/

        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P101");
        detail.put("descr", "2015 BMW`s");
        detail.put("type", "Cars");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct product = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        packageProd.add(product);
        /*------------------------------*/

        /*  CREATE WAREHOUSE ENTITY OBJECTS */
        Map<String, String> warehouseDetail = new HashMap<String, String>();

        warehouseDetail.put("code", "NE001");
        warehouseDetail.put("name", "MastersWarehouse");

        Warehouse w1 = WareHouseFactory.createWareHouse(warehouseDetail, 20000, packageProd);
        warehouses.add(w1);
        /*------------------------------*/

        /* CREATE LOGISTIC COMPANY OBJETCS */
        Map<String, String> companyDetail = new HashMap<String, String>();

        companyDetail.put("code", "LC001");
        companyDetail.put("name", "Frenzy Logistics");
        companyDetail.put("email", "frenzylogistics@frenzy.com");
        companyDetail.put("tel", "+27021800677");

        LogisticCompany company = LogisticCompanyFactory.createCompany(companyDetail, null);
        logisticCompanies.add(company);

        /*------------------------------*/

        /* CREATE DOCK OBJECTS */
        Dock dock1 = DockFactory.createDock("DC001", 400, 100, null);
        Dock dock2 = DockFactory.createDock("DC002", 400, 100, null);
        Dock dock3 = DockFactory.createDock("DC003", 400, 100, null);

        docks.add(dock1);
        docks.add(dock2);
        docks.add(dock3);

        /*------------------------------*/

        /*  CREATE PORT ENTITY OBJECT*/
        Map<String, String> portdetail = new HashMap<String, String>();
        portdetail.put("code", "PACT700");
        portdetail.put("name", "Cape Town Port");
        portdetail.put("type", "General");

        Port port = PortFactory.createPort(portdetail, location,
                terminals, docks, logisticCompanies, warehouses);

        this.repository.save(port);
        id = port.getId();
        Assert.assertNotNull(port.getId());
    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {

        Port port = repository.findOne(id);
        Assert.assertNotNull(port);

    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Port  port  = repository.findOne(id);

        Port  newPort = new Port.Builder(port.getPortCode())
                .copy(port)
                .portType("Shipping")
                .build();

        repository.save(newPort);

        Port updatedPort= repository.findOne(id);
        Assert.assertEquals("Shipping", updatedPort.getPortType());
    }
    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {

        Port port = repository.findOne(id);

        repository.delete(port);

        Port delPort = repository.findOne(id);
        Assert.assertNull(delPort);

    }*/


}
