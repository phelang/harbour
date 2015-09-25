package harbour.config.factory;

import harbour.domain.PackageProduct;
import harbour.domain.Warehouse;

import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/04/26.
 */

public class WareHouseFactory  {

    public static Warehouse createWareHouse(Map<String, String> value,
                                            int load, List<PackageProduct> packageProd)
    {

        Warehouse warehouse = new Warehouse.Builder(value.get("code"))
                .name(value.get("name"))
                .packageLoad(load)
                .packageProducts(packageProd)
                .build();

        return warehouse;
    }
}