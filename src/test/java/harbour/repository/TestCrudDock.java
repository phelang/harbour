package harbour.repository;

import harbour.config.factory.CargoFactory;
import harbour.config.factory.DockFactory;
import harbour.domain.Cargo;
import harbour.domain.Dock;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;import harborManagementSystem.App;*/

/**
 * Author P.Qhu  on 2015/08/12.
 */

/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudDock { //extends AbstractTestNGSpringContextTests{
    private Long id;

    //@Autowired
    DockRepository repository;

    @Test
    public void create() throws Exception {
        Map<String, Integer> size = new HashMap<String, Integer>();
        Map<String, String> detail = new HashMap<String, String>();

        detail.put("code", "CS1001");
        detail.put("name", "Jet");

        size.put("breadth", 400);
        size.put("height", 900);
        size.put("length", 1100);
        size.put("load", 15000);

        Cargo cargo = CargoFactory.createCargo(size, detail, null);

        Dock dock  = DockFactory.createDock("DC001", 400, 100, cargo);
        this.repository.save(dock);
        id = dock.getId();
        Assert.assertNotNull(dock.getId());

    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Dock dock= repository.findOne(id);
        Assert.assertNotNull(dock);

    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Dock dock  = repository.findOne(id);

        Dock newDock = new Dock.Builder(dock.getDockCode())
                .copy(dock)
                .breadth(500)
                .length(500)
                .build();

        repository.save(newDock);

        Dock updateDock= repository.findOne(id);
        Assert.assertEquals(500, updateDock.getBreadth());
        Assert.assertEquals(500, updateDock.getLength());


    }
    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Dock dock= repository.findOne(id);
        repository.delete(dock);
        Dock delDock = repository.findOne(id);
        Assert.assertNull(delDock);
    }*/
}
