package harbour.config.factory;

import harbour.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/08/10.
 */
public class PortFactory {
    public static Port createPort(Map<String, String> value, Location loc,
                                  List<Terminal> terminals,
                                  List<Dock> docks,
                                  List<LogisticCompany> logisticCompanys,
                                  List<Warehouse> warehouses)
    {

        Port port = new Port.Builder(value.get("code"))
                .name(value.get("name"))
                .portType(value.get("type"))
                .location(loc)
                .terminals(terminals)
                .companys(logisticCompanys)
                .warehouses(warehouses)
                .docks(docks)
                .build();
        return port;
    }
}
