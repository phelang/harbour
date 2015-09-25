package harbour.repository;

import harbour.config.factory.PackageProductFactory;
import harbour.domain.PackageProduct;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import harborManagementSystem.App;*/

/**
 *Author P.Qhu  on 2015/08/12.
 */

/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudPackageProd{//} extends AbstractTestNGSpringContextTests{
    private Long id;
    //@Autowired
    PackageProductRepository repository;

    //@Test
    public void create() throws Exception {
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P1001");
        detail.put("descr", "2015 BMW`s");
        detail.put("type", "Cars");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct pkgProduct = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        this.repository.save(pkgProduct);
        id = pkgProduct.getId();
        Assert.assertNotNull(pkgProduct.getId());
    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        PackageProduct pkgProduct = repository.findOne(id);
        Assert.assertNotNull(pkgProduct);
    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {
        PackageProduct pkgProduct = repository.findOne(id);

        PackageProduct newPkgProduct = new PackageProduct.Builder(pkgProduct.getPackageCode())
                .copy(pkgProduct)
                .description("Rolls Royce")
                .itemType("Wealth Cars")
                .build();

        repository.save(newPkgProduct);

        PackageProduct updatepkgProduct = repository.findOne(id);

        Assert.assertEquals("P1001", updatepkgProduct.getPackageCode());
        Assert.assertEquals("Rolls Royce", updatepkgProduct.getDescription());

    }

    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        PackageProduct pkgProduct = repository.findOne(id);
        repository.delete(pkgProduct);
        PackageProduct deletepkgProduct = repository.findOne(id);
        Assert.assertNull(deletepkgProduct );
    }*/
}
