package harbour.config.factory;

import harbour.domain.Container;
import harbour.domain.PackageProduct;

import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/04/26.
 */
public class ContainerFactory {

    public static Container createContainer(Map<String, Integer> value,
                                            String containerCode,
                                            List<PackageProduct> packageProd)
    {

        Container container = new Container.Builder(containerCode)
                .width(value.get("width"))
                .height(value.get("height"))
                .packageProducts(packageProd)
               .build();
        return container;
    }

}
