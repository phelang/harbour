package harbour.services;

import harbour.config.factory.PackageProductFactory;
import harbour.domain.PackageProduct;
import harbour.repository.PackageProductRepository;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/*import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;import harborManagementSystem.App;
import org.springframework.boot.test.SpringApplicationConfiguration;*/

/**
 * Created by student on 2015/08/19.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/

public class TestPakgProdService {//extends AbstractTestNGSpringContextTests{
    private Long idpkgProduct1;
    private Long idpkgProduct2;
    //@Autowired
    private PackageProductRepository repository;
    //@Autowired
    private PackageProductService service;
    //@Test
    public void create() throws Exception {

        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();
        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "PT0001");
        detail.put("descr", "TeraByte CPU UL");
        detail.put("type", "Processor");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct pkgproduct1 = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);          // PackageProduct 1

        Map<String, String> pkgProdDetail2 = new HashMap<String, String>();
        pkgProdDetail2.put("code", "PT0002");
        pkgProdDetail2.put("descr", "Diamond");
        pkgProdDetail2.put("type", "Crystal");

        SimpleDateFormat fomatter2 = new SimpleDateFormat("dd/mm/yy");
        Date currDate2 = fomatter2.parse("01/01/2016");
        PackageProduct pkgproduct2 = PackageProductFactory.createPackageProduct(
                pkgProdDetail2, currDate2, 1);     // PackageProduct 2

        packageProd.add(pkgproduct1);
        packageProd.add(pkgproduct2);

        this.repository.save(pkgproduct1);
        this.repository.save(pkgproduct2);
        idpkgProduct1=pkgproduct1.getId();
        idpkgProduct2=pkgproduct2.getId();

        Assert.assertNotNull(pkgproduct1.getId());
        Assert.assertNotNull(pkgproduct2.getId());
    }

    /*
    The services has been refactored, MAKE CHANGES AS NECESSARY
     */
    /*@Test(dependsOnMethods = "create")
    public void testGetPackageProducts() throws Exception {
        List<PackageProduct> pkgProduct = service.getPackageProducts();
        Assert.assertEquals(2, pkgProduct.size());

    }

    @Test(dependsOnMethods = "testGetPackageProducts")
    public void delete() throws Exception {
        repository.delete(idpkgProduct1);
        repository.delete(idpkgProduct2);

        PackageProduct pkgProd1 = repository.findOne(idpkgProduct1);
        PackageProduct pkgProd2 = repository.findOne(idpkgProduct2);

        Assert.assertNull(pkgProd1);
        Assert.assertNull(pkgProd2);

    }*/

}
