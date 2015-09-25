package harbour.domain;

import harbour.config.factory.PackageProductFactory;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.junit.Test;

/**
 *Author P.Qhu on 2015/08/15.
 */
public class PackageProductTest {

    //@Test
    public void testCreate() throws Exception {
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P1001");
        detail.put("descr", "2015 Range Rover");
        detail.put("type", "Cars");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");

        PackageProduct product = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        Assert.assertEquals("P1001", product.getPackageCode());
        Assert.assertEquals("2015 Range Rover", product.getDescription());

    }

   // @Test
    public void testUpdate() throws Exception {
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "P1001");
        detail.put("descr", "2015 Range Rover");
        detail.put("type", "Cars");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");

        PackageProduct product = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);

        PackageProduct newProduct= new PackageProduct.Builder(product.getPackageCode())
                .copy(product)
                .build();

        Assert.assertEquals("P1001", newProduct.getPackageCode());
        Assert.assertEquals("Cars", newProduct.getItemType());

    }
}
