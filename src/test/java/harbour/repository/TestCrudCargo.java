package harbour.repository;

import harbour.config.factory.CargoFactory;
import harbour.config.factory.ContainerFactory;
import harbour.domain.Cargo;
import harbour.domain.Container;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;import harborManagementSystem.App;*/

/**
 * Author P.Qhu on 2015/08/13.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudCargo {//extends AbstractTestNGSpringContextTests{
    private Long id;
    //@Autowired
    CargoRepository repository;

    //@Test
    public void create() throws Exception {
        List<Container>  containers = new ArrayList<Container>();
        /* CREATE CONTAINERS */
        Map<String, Integer> sides = new HashMap<String, Integer>();

        sides.put("width", 200);
        sides.put("height", 200);

        Container container = ContainerFactory.createContainer(sides, "CC1001", null);
        Container container2 = ContainerFactory.createContainer(sides, "CC1002", null);


        containers.add(container);
        containers.add(container2);
        /*- - - - - - - - - - -*/


        Map<String, Integer> size = new HashMap<String, Integer>();

        size.put("breadth", 200);
        size.put("height", 200);
        size.put("length", 200);
        size.put("load", 300);

        Map<String, String> detail= new HashMap<String, String>();
        detail.put("name", "Waves");
        detail.put("code", "CX101");

        Cargo cargo = CargoFactory.createCargo(size, detail, containers);

        this.repository.save(cargo);
        id = cargo.getId();
        Assert.assertNotNull(cargo.getId());

    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Cargo cargo = repository.findOne(id);
        Assert.assertNotNull(cargo);
    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {

        Cargo cargo = repository.findOne(id);

        Cargo newCargo = new Cargo.Builder(cargo.getCargoCode())
                .copy(cargo)
                .name("AB Cruizer")
                .build();

        repository.save(newCargo);

        Cargo updatedCargo = repository.findOne(id);
        Assert.assertEquals("AB Cruizer", updatedCargo.getName());


    }
    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Cargo cargo = repository.findOne(id);
        repository.delete(cargo);
        Cargo delCargo = repository.findOne(id);
        Assert.assertNull(delCargo);
    }*/
}
