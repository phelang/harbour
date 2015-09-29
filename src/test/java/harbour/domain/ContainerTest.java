package harbour.domain;

import harbour.config.factory.ContainerFactory;
import harbour.config.factory.PackageProductFactory;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

//import org.junit.Before;
//import org.junit.Test;

/**
 * Author P.Qhu on 2015/08/15.
 */
public class ContainerTest {

    private List<PackageProduct> packageProd;

    //@Before
    public void setUp() throws Exception {
        packageProd = new ArrayList<PackageProduct>();

    }
    //@Test
    public void testCreate() throws Exception {
        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P1001");
        detail.put("descr", "2015 Apple Ipad");
        detail.put("type", "IPad");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct product = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        packageProd.add(product);

        /*------------------------------*/

        Map<String, Integer> sides = new HashMap<String, Integer>();
        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1001", packageProd);
        Assert.assertEquals("CO1001", container.getContainerCode());
        Assert.assertNotNull(container.getPackagedProd());  // list of PackageProducts

    }

   // @Test
    public void testUpdate() throws Exception {
        Map<String, Integer> sides = new HashMap<String, Integer>();
        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1001",packageProd);

        Container newContainer = new Container.Builder(container.getContainerCode())
                .copy(container)
                .packageProducts(null)  // delete all Packages Within the container
                .build();

        Assert.assertEquals(200, newContainer.getHeight());
        Assert.assertEquals(200, newContainer.getWidth());
        Assert.assertEquals("CO1001", newContainer.getContainerCode());
        Assert.assertNull(newContainer.getPackagedProd());

    }
}
