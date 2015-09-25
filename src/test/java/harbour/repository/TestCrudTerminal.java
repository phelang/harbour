package harbour.repository;

//import harborManagementSystem.App;

import harbour.config.factory.ContainerFactory;
import harbour.config.factory.TerminalFactory;
import harbour.domain.Container;
import harbour.domain.Terminal;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;*/

/**
 *Author P.Qhu  on 2015/08/10.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudTerminal { //extends AbstractTestNGSpringContextTests{
    private Long id;

    //@Autowired
    TerminalRepository repository;

    //@Test
    public void create() throws Exception {
        List<Container> containers = new ArrayList<Container>();

        Map<String, Integer> sides = new HashMap<String, Integer>();

        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CO1001", null);
        Container container2 = ContainerFactory.createContainer(sides, "CO1002", null);



        containers.add(container);
        containers.add(container2);

        Terminal terminal = TerminalFactory.createTerminal("TSE01", "SE", 200, containers);
        this.repository.save(terminal);
        id = terminal.getId();
        Assert.assertNotNull(terminal.getId());

    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Terminal terminal = repository.findOne(id);
        Assert.assertNotNull(terminal);

    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Terminal terminal = repository.findOne(id);

        Terminal newTerminal = new Terminal.Builder(terminal.getTerminalCode())
                .copy(terminal)
                .teminalLocation("SW")
                .build();

        repository.save(newTerminal);

        Terminal updatedTerminal = repository.findOne(id);
        Assert.assertEquals("SW", updatedTerminal.getTeminalLocation());

    }
    //@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Terminal terminal = repository.findOne(id);

        repository.delete(terminal);

        Terminal delTerminal = repository.findOne(id);
        Assert.assertNull(delTerminal);

    }
}
