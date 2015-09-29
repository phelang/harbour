package harbour.config.factory;


import harbour.domain.Cargo;
import harbour.domain.Container;

import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/08/13.
 */
public class CargoFactory {
    public static Cargo createCargo(Map<String, Integer> size,
                                            Map<String, String> detail,
                                            List<Container> container)
    {

        Cargo cargo = new Cargo.Builder(detail.get("code"))
                .name(detail.get("name"))
                .breadth(size.get("breadth"))
                .height(size.get("height"))
                .length(size.get("length"))
                .load(size.get("load"))
                .containers(container)
                .build();
        return cargo;
    }
}
