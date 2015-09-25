package harbour.api;

import harbour.domain.*;
import harbour.model.*;
import harbour.services.PortService;
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
@RequestMapping(value="/port/")
public class PortController {
	
	@Autowired
    private PortService service;
	
	
	//-------------------Retrieve All Ports--------------------------------------------------------
    @RequestMapping(value="/ports/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PortResource>> getAllPorts() {

        List<PortResource> portHateos = new ArrayList<PortResource>();

        List<Port> ports = service.findAll();



        for (Port port: ports) {

            //-------------------Retrieve All LogisticCompany HATEOS LINKS----------------------------------------------

            List<LogisticCompanyResource> logisticCompanyHateos = new ArrayList<LogisticCompanyResource>();

            List<LogisticCompany> logisticCompanies = port.getLogisticCompanies();

            for(LogisticCompany logisticCompany: logisticCompanies){ 		
				
				
				 LogisticCompanyResource logisticCompanyResourceRes = new LogisticCompanyResource
						.Builder(logisticCompany.getCompanyCode())
						.resid(logisticCompany.getId())
						.name(logisticCompany.getName())
						.tel(logisticCompany.getTel())
						.email(logisticCompany.getEmail())
						.packageProducts(null)   // get package products
						.build();
						
				Link linkLogCompany = (new

						// create a link to this method on
						Link(
						linkTo(methodOn(LogisticCompanyController.class).getLogisticCompany(logisticCompanyResourceRes.getResId()))
								.toString()).withRel("logistic" + logisticCompanyResourceRes.getResId())
				);
				
				logisticCompanyResourceRes.add(linkLogCompany);
				logisticCompanyHateos.add(logisticCompanyResourceRes);		

            }
            //-------------------Retrieve All Docks HATEOS LINKS----------------------------------------------


            List<DockResource> dockHateos = new ArrayList<DockResource>();
            List<Dock> docks = port.getDocks();

            for (Dock dock: docks) {

                //-------------------NOW CREATE HATEOS LINKS for Dock--------------------------------------------------------

                DockResource dockRes = new DockResource
                        .Builder(dock.getDockCode())
                        .resid(dock.getId())
                        .length(dock.getLength())
                        .breadth(dock.getBreadth())
                        .cargo(null)
                        .build();

                Link linkDock = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(DockController.class).getDock(dockRes.getResId()))
                               .toString()).withRel("dock" + dockRes.getResId())
                );

                dockRes.add(linkDock);
                dockHateos.add(dockRes);
            }

			//-------------------Retrieve All Warehouse HATEOS LINKS----------------------------------------------
			
			List<WarehouseResource> warehouseHateos = new ArrayList<WarehouseResource>();

			List<Warehouse> warehouses = port.getWarehouses();



			for (Warehouse warehouse: warehouses) {			

				//-------------------NOW CREATE HATEOS LINKS for WAREHOUSES--------------------------------------------------------
                WarehouseResource warehouseRes = new WarehouseResource
                        .Builder(warehouse.getWarehouseCode())
                        .resId(warehouse.getId())
                        .name(warehouse.getName())
                        .packageLoad(warehouse.getPackageLoad())
                        .packageProducts(null)   // get package products
                        .build();

				Link linkWarehouse = (new

						// create a link to this method on
						Link(
						linkTo(methodOn(WarehouseController.class).getWarehouse(warehouseRes.getResId()))			// fix this
								.toString()).withRel("warehouse" + warehouseRes.getResId())
				);

                warehouseRes.add(linkWarehouse);
				warehouseHateos.add(warehouseRes);
			}		

			//-------------------Retrieve All Terminal HATEOS LINKS----------------------------------------------
			
			List<TerminalResource> terminalHateos = new ArrayList<TerminalResource>();

			List<Terminal> terminals = port.getTerminals();


            for (Terminal terminal: terminals) {

                          //-------------------NOW CREATE HATEOS LINKS for Terminal--------------------------------------------------------
                TerminalResource terminalResource = new TerminalResource
                        .Builder(terminal.getTerminalCode())
                        .resId(terminal.getId())
                        .containerLoad(terminal.getContainerLoad())
                        .containers(null)   // get package products
                        .build();

                Link link = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(TerminalController.class).getTerminal(terminalResource.getResId()))
                                .toString()).withRel("terminal" + terminalResource.getResId() )
                );

                terminalResource.add(link);
                terminalHateos.add(terminalResource);
            }




            /**********************************************************************************************
				*
				* 				NOW CREATE HATEOS LINKS for			PORT
				*
			***********************************************************************************************/
			
            PortResource portRes = new PortResource
                    .Builder(port.getPortCode())
                    .resId(port.getId())
                    .name(port.getName())
                    .portType(port.getPortType())
                    .location(port.getLocation())
					.companys(logisticCompanyHateos)  
					.warehouses(warehouseHateos)  
					.docks(dockHateos)  
                    .terminals(terminalHateos)   
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(PortController.class).getPort(portRes.getResId()))
                            .toString()).withSelfRel()
            );

            portRes.add(link);
            portHateos.add(portRes);
        }

        return new ResponseEntity<List<PortResource>>(portHateos, HttpStatus.OK);
    }

	 //-------------------Retrieve Single Port --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PortResource> getPort (@PathVariable("id") long id) {

        Port port = service.findById(id);

        if (port == null) {

            return new ResponseEntity<PortResource>(HttpStatus.NOT_FOUND);
        }
		

        /*List<LogisticCompanyResource> logisticCompanyHateos = new ArrayList<LogisticCompanyResource>();

        List<LogisticCompany> logisticCompanies = port.getLogisticCompanies();

        System.out.println("Size of logistics" + logisticCompanies.size());

        for(LogisticCompany logisticCompany: logisticCompanies){


            LogisticCompanyResource logisticCompanyResourceRes = new LogisticCompanyResource
                    .Builder(logisticCompany.getCompanyCode())
                    .resid(logisticCompany.getId())
                    .name(logisticCompany.getName())
                    .tel(logisticCompany.getTel())
                    .email(logisticCompany.getEmail())
                    .packageProducts(null)   // get package products
                    .build();

            Link linkLogCompany = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(LogisticCompanyController.class).getLogisticCompany(logisticCompanyResourceRes.getResId()))
                            .toString()).withRel("logistic" + logisticCompanyResourceRes.getResId())
            );

            logisticCompanyResourceRes.add(linkLogCompany);
            logisticCompanyHateos.add(logisticCompanyResourceRes);

        }


        // Get WAREHOUSE

        List<WarehouseResource> warehouseHateos = new ArrayList<WarehouseResource>();

        List<Warehouse> warehouses = port.getWarehouses();


        for (Warehouse warehouse: warehouses) {

            //-------------------NOW CREATE HATEOS LINKS for WAREHOUSES--------------------------------------------------------
            WarehouseResource warehouseRes = new WarehouseResource
                    .Builder(warehouse.getWarehouseCode())
                    .resId(warehouse.getId())
                    .name(warehouse.getName())
                    .packageLoad(warehouse.getPackageLoad())
                    .packageProducts(null)   // get package products
                    .build();

            Link linkWarehouse = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(WarehouseController.class).getWarehouse(warehouseRes.getResId()))			// fix this
                            .toString()).withRel("warehouse" + warehouseRes.getResId())
            );

            warehouseRes.add(linkWarehouse);
            warehouseHateos.add(warehouseRes);
        }


        // Dock

        List<DockResource> dockHateos = new ArrayList<DockResource>();

        List<Dock> docks = port.getDocks();



        for (Dock dock: docks) {

            //-------------------NOW CREATE HATEOS LINKS for Dock--------------------------------------------------------
            DockResource dockRes = new DockResource
                    .Builder(dock.getDockCode())
                    .resid(dock.getId())
                    .length(dock.getLength())
                    .breadth(dock.getBreadth())
                    .cargo(null)
                    .build();

            Link linkDock = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(DockController.class).getDock(dockRes.getResId()))
                            .toString()).withRel("dock" + dockRes.getResId())
            );

            dockRes.add(linkDock);
            dockHateos.add(dockRes);
        }

		// Get TERMINAL

        List<TerminalResource> terminalHateos = new ArrayList<TerminalResource>();

        List<Terminal> terminals = port.getTerminals();


        for (Terminal terminal: terminals) {

            //-------------------NOW CREATE HATEOS LINKS for Terminal--------------------------------------------------------
            TerminalResource terminalResource = new TerminalResource
                    .Builder(terminal.getTerminalCode())
                    .resId(terminal.getId())
                    .containerLoad(terminal.getContainerLoad())
                    .containers(null)   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(TerminalController.class).getTerminal(terminalResource.getResId()))
                            .toString()).withRel("terminal" + terminalResource.getResId() )
            );

            terminalResource.add(link);
            terminalHateos.add(terminalResource);
        }*/


		// Create PORT
        PortResource portRes = new PortResource
                    .Builder(port.getPortCode())
                    .resId(port.getId())
                    .name(port.getName())
                    .portType(port.getPortType())
                    .location(port.getLocation())
					.companys(null)
                .warehouses(null)
                .docks(null)
                .terminals(null)
                .build();

        Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(PortController.class).getPort(portRes.getResId()))
                            .toString()).withSelfRel()
            );
			
        portRes.add(link);

        return new ResponseEntity<PortResource>(portRes, HttpStatus.OK);
    }
	

	//------------------- Create a Port --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createPort(@RequestBody Port port,    UriComponentsBuilder ucBuilder) {
    
        service.save(port);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/port/{id}").buildAndExpand(port.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	

	
	//------------------- Update a Port --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Port> updatePort(@PathVariable("id") long id, @RequestBody Port newPort) {

        service.update(newPort);
        return new ResponseEntity<Port>(newPort, HttpStatus.OK);
    }


	//------------------- Delete a Port --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Port> deletePort(@PathVariable("id") long id) {
      
        Port port = service.findById(id);
        if (port == null) {
            return new ResponseEntity<Port>(HttpStatus.NOT_FOUND);
        }

        service.delete(port);
        return new ResponseEntity<Port>(HttpStatus.NO_CONTENT);
    }
	
}
