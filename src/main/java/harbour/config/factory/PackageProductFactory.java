package harbour.config.factory;

import harbour.domain.PackageProduct;

import java.util.Date;
import java.util.Map;

/**
 * Created by student on 2015/08/10.
 */
public class PackageProductFactory{

    public static PackageProduct createPackageProduct(
            Map<String, String> detail, Date date, int qty)
    {
        PackageProduct product = new PackageProduct.Builder(detail.get("code"))
                .description(detail.get("descr"))
                .itemType(detail.get("type"))
                .packageDate(date)
                .quantity(qty)
                .build();

        return product;
    }
}
