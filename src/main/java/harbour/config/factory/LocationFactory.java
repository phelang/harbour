package harbour.config.factory;

import harbour.domain.Location;

import java.util.Map;

/**
 * Created by student on 2015/08/10.
 */
public class LocationFactory{
    public static Location createLocation(Map<String, String> value)
    {

        Location location = new Location.Builder(value.get("tel"))
                .continent(value.get("continent"))
                .country(value.get("country"))
                .city(value.get("city"))
                .postalCode(value.get("code"))
                .build();
        return location;
    }
}
