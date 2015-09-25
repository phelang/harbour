package harbour.domain;

import harbour.config.factory.TerminalFactory;
import org.junit.Assert;
//import org.junit.Test;

/**
 * Author P.Qhu  on 2015/08/15.
 */
public class TerminalTest {
    //@Test
    public void testCreate() throws Exception {
        Terminal terminal = TerminalFactory.createTerminal("TSE01", "SE", 200, null);
        Assert.assertEquals("TSE01", terminal.getTerminalCode());
        Assert.assertEquals("SE", terminal.getTeminalLocation());
        Assert.assertEquals(200, terminal.getContainerLoad());
    }
    //@Test
    public void testUpdate() throws Exception {
        Terminal terminal = TerminalFactory.createTerminal( "TSE01","SE", 200, null);

        Terminal newTerminal = new Terminal.Builder(terminal.getTerminalCode())
                .copy(terminal)
                .teminalLocation("S")
                .build();

        Assert.assertEquals("TSE01", newTerminal.getTerminalCode());
        Assert.assertEquals("S", newTerminal.getTeminalLocation());
        Assert.assertEquals("SE", terminal.getTeminalLocation());

    }
}
