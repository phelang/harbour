package harbour.repository;
import harbour.config.factory.ContainerFactory;
import harbour.config.factory.PackageProductFactory;
import harbour.domain.Container;
import harbour.domain.PackageProduct;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/*import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import harborManagementSystem.App;*/

/**
 * Author P.Qhu  on 2015/05/03.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudContainer {//extends AbstractTestNGSpringContextTests{
    private Long id;
    //@Autowired
    ContainerRepository repository;

    //@Test
    public void create() throws Exception {
        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();
        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> pkgProdDetail1 = new HashMap<String, String>();
        pkgProdDetail1.put("code", "P301");
        pkgProdDetail1.put("descr", "2015 Apple Ipad");
        pkgProdDetail1.put("type", "IPad");

        SimpleDateFormat fomatter1 = new SimpleDateFormat("dd/mm/yy");
        Date currDate1 = fomatter1.parse("01/01/2016");
        PackageProduct pkgproduct1 = PackageProductFactory.createPackageProduct(
                pkgProdDetail1, currDate1, 14);


        Map<String, String> pkgProdDetail2 = new HashMap<String, String>();
        pkgProdDetail2.put("code", "P302");
        pkgProdDetail2.put("descr", "Lamborgini");
        pkgProdDetail2.put("type", "Car");

        SimpleDateFormat fomatter2 = new SimpleDateFormat("dd/mm/yy");
        Date currDate2 = fomatter2.parse("01/01/2016");
        PackageProduct pkgproduct2 = PackageProductFactory.createPackageProduct(
                pkgProdDetail2, currDate2, 14);

        packageProd.add(pkgproduct1);
        packageProd.add(pkgproduct2);
        /*------------------------------*/

        Map<String, Integer> sides = new HashMap<String, Integer>();

        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1001", packageProd);

        this.repository.save(container);
        id=container.getId();
        Assert.assertNotNull(container.getId());
    }
    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Container container = repository.findOne(id);
        Assert.assertNotNull(container);
        Assert.assertEquals(2, container.getPackagedProd().size());
    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Container container = repository.findOne(id);

        Container newContainer = new Container.Builder(container.getContainerCode())
                .copy(container)
                .width(500)
                .height(500)
                .build();

        repository.save(newContainer);
        Container updatedContainer = repository.findOne(id);

        Assert.assertEquals(500, updatedContainer.getWidth());
        Assert.assertEquals(200, container.getWidth());
    }

    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Container container = repository.findOne(id);
        repository.delete(container);
        Container newContainer = repository.findOne(id);
        Assert.assertNull(newContainer);
    }*/
}