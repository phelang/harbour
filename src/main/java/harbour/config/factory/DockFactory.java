package harbour.config.factory;

import harbour.domain.Cargo;
import harbour.domain.Dock;

/**
 * Created by student on 2015/05/03.
 */
public class DockFactory {

    public static Dock createDock(String dockCode,int length, int breadth,
                                  Cargo cargo){

        Dock dock = new Dock.Builder(dockCode)
                .length(length)
                .breadth(breadth)
                .cargo(cargo)
                .build();

        return dock;
    }
}
