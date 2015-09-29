package harbour.config.factory;

import harbour.domain.Container;
import harbour.domain.Terminal;

import java.util.List;

/**
 * Created by student on 2015/08/10.
 */
public class TerminalFactory {
    public static Terminal createTerminal(String code,
                                          String loc, int load,
                                             List<Container> containers)
    {

        Terminal terminal = new Terminal.Builder(code)
                .teminalLocation(loc)
                .containerLoad(load)
                .containers(containers)
                .build();
        return terminal;
    }

}
