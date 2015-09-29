package harbour.api;

import harbour.domain.Cargo;
import harbour.domain.Dock;
import harbour.model.CargoResource;
import harbour.model.DockResource;
import harbour.services.DockService;
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
 * Author P.Qhu on 2015/09/22.
 */
@RestController
@RequestMapping(value="/dock/")
public class DockController {
	
	@Autowired
    private DockService service;
	
	
	//-------------------Retrieve All Docks--------------------------------------------------------
    @RequestMapping(value="/docks/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DockResource>> getAllDock() {

        List<DockResource> dockHateos = new ArrayList<DockResource>();
        List<CargoResource> cargoHateos = new ArrayList<CargoResource>();

        List<Dock> docks = service.findAll();

        //CargoResource cargoRes = null;


        for (Dock dock: docks) {

            //-------------------Retrieve All Cargo HATEOS LINKS----------------------------------------------

            Cargo cargo = dock.getCargo();

                CargoResource cargoRes = new CargoResource
                        .Builder(cargo.getCargoCode())
                        .resid(cargo.getId())
                        .name(cargo.getName())
                        .length(cargo.getLength())
                        .breadth(cargo.getBreadth())
                        .load(cargo.getContainerLoad())
                        .containers(null)
                        .build();


                Link linkCargo = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(CargoController.class).getCargoRes(cargo.getId()))
                                .toString()).withRel("cargo" + cargo.getId())
                );


                cargoRes.add(linkCargo);
                cargoHateos.add(cargoRes);



            //-------------------NOW CREATE HATEOS LINKS for Dock--------------------------------------------------------

            DockResource dockRes = new DockResource
                    .Builder(dock.getDockCode())
                    .resid(dock.getId())
                    .length(dock.getLength())
                    .breadth(dock.getBreadth())
                    .cargo(cargoRes)
                    .build();

            Link linkDock = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(DockController.class).getDock(dockRes.getResId()))
                            .toString()).withSelfRel()
            );

            dockRes.add(linkDock);
            dockHateos.add(dockRes);
        }

        return new ResponseEntity<List<DockResource>>(dockHateos, HttpStatus.OK);
    }
	
	 //-------------------Retrieve Single Dock --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DockResource> getDock (@PathVariable("id") long id) {

        Dock dock = service.findById(id);

        if (dock == null) {

            return new ResponseEntity<DockResource>(HttpStatus.NOT_FOUND);
        }

              Cargo cargo = dock.getCargo();

        CargoResource cargoRes = new CargoResource
                .Builder(cargo.getCargoCode())
                .resid(cargo.getId())
                .name(cargo.getName())
                .length(cargo.getLength())
                .breadth(cargo.getBreadth())
                .load(cargo.getContainerLoad())
                .containers(null)
                .build();

        Link linkCargo = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(CargoController.class).getCargoRes(cargo.getId()))
                        .slash(cargoRes.getResId()).toString()).withSelfRel()
        );

        cargoRes.add(linkCargo);


        DockResource dockRes = new DockResource
                    .Builder(dock.getDockCode())
                    .resid(dock.getId())
                    .length(dock.getLength())
                    .breadth(dock.getBreadth())
                    .cargo(cargoRes)
                    .build();

        Link linkDock = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(DockController.class).getDock(dockRes.getResId()))
                            .slash(dockRes.getResId()).toString()).withSelfRel()
            );

        dockRes.add(linkDock);

        return new ResponseEntity<DockResource>(dockRes, HttpStatus.OK);
    }

	//------------------- Create a Dock --------------------------------------------------------

	@RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDock(@RequestBody Dock dock,    UriComponentsBuilder ucBuilder) {
    
        service.save(dock);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dock/{id}").buildAndExpand(dock.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	

	
	//------------------- Update a Dock --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dock> updateDock(@PathVariable("id") long id, @RequestBody Dock newDock) {

        service.update(newDock);
        return new ResponseEntity<Dock>(newDock, HttpStatus.OK);
    }

	//------------------- Delete a Dock --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Dock> deleteDock(@PathVariable("id") long id) {
      
        Dock dock = service.findById(id);
        if (dock == null) {
            return new ResponseEntity<Dock>(HttpStatus.NOT_FOUND);
        }

        service.delete(dock);
        return new ResponseEntity<Dock>(HttpStatus.NO_CONTENT);
    }
	
}
