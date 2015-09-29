package harbour.api;

import harbour.domain.Cargo;
import harbour.domain.Container;
import harbour.model.CargoResource;
import harbour.model.ContainerResource;
import harbour.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Author P.Qhu  on 2015/09/22.
 */
@RestController
@RequestMapping(value="/cargo/")
public class CargoController {
	
	@Autowired
    private CargoService service;


    //-------------------Retrieve All Cargo Ships--------------------------------------------------------
    @RequestMapping(value="/cargos/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CargoResource>> getAllCargo() {

        List<CargoResource> cargoHateos = new ArrayList<CargoResource>();

        List<Cargo> cargos = service.findAll();



        for (Cargo cargo: cargos) {

            //-------------------Retrieve All Container HATEOS LINKS----------------------------------------------

            List<ContainerResource> containerHateos = new ArrayList<ContainerResource>();

            List<Container> containers = cargo.getContainer();

            for(Container container: containers){       // create HATEOS Links for sub-entities


                ContainerResource containerRes = new ContainerResource
                        .Builder(container.getContainerCode())
						.resid(container.getId())
						.width(container.getWidth())
						.height(container.getHeight())
						.packageProducts(null)   // get package products
						.build();

                Link link = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(ContainerController.class).getContainer(container.getId()))
                                .toString()).withRel("c" + container.getId())
                );


                containerRes.add(link);
                containerHateos.add(containerRes);
            }



            //-------------------NOW CREATE HATEOS LINKS for Cargo--------------------------------------------------------
            CargoResource res = new CargoResource
                    .Builder(cargo.getCargoCode())
                    .resid(cargo.getId())
                    .name(cargo.getName())
                    .length(cargo.getLength())
                    .breadth(cargo.getBreadth())
                    .load(cargo.getContainerLoad())
                    .containers(containerHateos)
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(CargoController.class).getCargoRes(res.getResId()))
                            .toString()).withSelfRel()
            );

            res.add(link);
            cargoHateos.add(res);
        }

        return new ResponseEntity<List<CargoResource>>(cargoHateos, HttpStatus.OK);
    }
	
	
	 //-------------------Retrieve Single Cargo --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoResource> getCargoRes (@PathVariable("id") long id) {

        Cargo cargo = service.findById(id);

        if (cargo == null) {

            return new ResponseEntity<CargoResource>(HttpStatus.NOT_FOUND);
        }


        List<ContainerResource> containerHateos = new ArrayList<ContainerResource>();

        List<Container> containers = cargo.getContainer();

        //if(containers != null) {
            for (Container container : containers) {       // create HATEOS Links for sub-entities


                ContainerResource containerRes = new ContainerResource
                        .Builder(container.getContainerCode())
                        .resid(container.getId())
                        .width(container.getWidth())
                        .height(container.getHeight())
                        .packageProducts(null)   // get package products
                        .build();

                Link link = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(ContainerController.class).getContainer(containerRes.getResId()))
                                .toString()).withRel("c" + container.getId())
                );


                containerRes.add(link);
                containerHateos.add(containerRes);
            }
        //}

        CargoResource cargoRes = new CargoResource
                .Builder(cargo.getCargoCode())
                .resid(cargo.getId())
                .name(cargo.getName())
                .length(cargo.getLength())
                .breadth(cargo.getBreadth())
                .load(cargo.getContainerLoad())
                .containers(containerHateos)
                .build();

        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(CargoController.class).getCargoRes(cargo.getId()))
                        .toString()).withSelfRel()
        );

        cargoRes.add(link);

        return new ResponseEntity<CargoResource>(cargoRes, HttpStatus.OK);
    }

	//------------------- Create a Cargo --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createCargo(@RequestBody Cargo cargo,    UriComponentsBuilder ucBuilder) {
    
        service.save(cargo);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cargo/{id}").buildAndExpand(cargo.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	

	
	//------------------- Update a Cargo --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cargo> updateCargo(@PathVariable("id") long id, @RequestBody Cargo newCargo) {

        service.update(newCargo);
        return new ResponseEntity<Cargo>(newCargo, HttpStatus.OK);
    }

	//------------------- Delete a Terminal --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Cargo> deleteCargo(@PathVariable("id") long id) {
      
        Cargo cargo = service.findById(id);
        if (cargo == null) {
            return new ResponseEntity<Cargo>(HttpStatus.NOT_FOUND);
        }

        service.delete(cargo);
        return new ResponseEntity<Cargo>(HttpStatus.NO_CONTENT);
    }

}
