package harbour.services;

import harbour.config.factory.ContainerFactory;
import harbour.config.factory.PackageProductFactory;
import harbour.domain.Container;
import harbour.domain.PackageProduct;
import harbour.repository.ContainerRepository;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/*import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;import harborManagementSystem.App;*/

/**
 * Author P.Qhu on 2015/08/28.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestContainerService{ // extends AbstractTestNGSpringContextTests {
    //@Autowired
    private ContainerService service;
    //@Autowired
    private ContainerRepository repository;

    private Long id;

    //@Test
    public void create() throws Exception {

        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();;
        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> pkgProdDetail1 = new HashMap<String, String>();
        pkgProdDetail1.put("code", "PC0001");
        pkgProdDetail1.put("descr", "Art");
        pkgProdDetail1.put("type", "Gallery");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct pkgproduct1 = PackageProductFactory.createPackageProduct(
                pkgProdDetail1, currDate, 5);          // PackageProduct 1

        Map<String, String> pkgProdDetail2 = new HashMap<String, String>();
        pkgProdDetail2.put("code", "PC0002");
        pkgProdDetail2.put("descr", "Books");
        pkgProdDetail2.put("type", "Books");

        SimpleDateFormat fomatter2 = new SimpleDateFormat("dd/mm/yy");
        Date currDate2 = fomatter2.parse("01/01/2016");
        PackageProduct pkgproduct2 = PackageProductFactory.createPackageProduct(
                pkgProdDetail2, currDate2, 5);     // PackageProduct 2

        packageProd.add(pkgproduct1);
        packageProd.add(pkgproduct2);


        /*------------------------------*/

        Map<String, Integer> sides = new HashMap<String, Integer>();
        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1003", packageProd);
        this.repository.save(container);
        id = container.getId();
        Assert.assertNotNull(container.getId());

    }

    /*
    The services has been refactored, MAKE CHANGES AS NECESSARY
     */

    /*@Test(dependsOnMethods = "create")
    public void testGetContainers() throws Exception {
        List<Container> containers = service.getAllContainers();
        Assert.assertEquals(1, containers.size());
    }

    @Test(dependsOnMethods = "create")
    public void testGetPckgProducts() throws Exception {
        List<PackageProduct> pkgproduct = service.getPkgProducts(id);
        Assert.assertEquals("PC0001", pkgproduct.get(0).getPackageCode());
    }

    @Test(dependsOnMethods = {"testGetContainers","testGetPckgProducts"} )
    public void delete() throws Exception {
        repository.delete(id);
        Container deleteContainer = repository.findOne(id);
        Assert.assertNull(deleteContainer);

    }*/
}
