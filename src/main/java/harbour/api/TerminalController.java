package harbour.api;

import harbour.domain.Container;
import harbour.domain.Terminal;
import harbour.model.ContainerResource;
import harbour.model.TerminalResource;
import harbour.services.TerminalService;
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
@RequestMapping(value="/terminal/")
public class TerminalController {
	
	@Autowired
    private TerminalService service;


    //-------------------Retrieve All Terminals--------------------------------------------------------
    @RequestMapping(value="/terminals/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TerminalResource>> getAllTerminals() {

        List<TerminalResource> terminalHateos = new ArrayList<TerminalResource>();

        List<Terminal> terminals = service.findAll();



        for (Terminal terminal: terminals) {

            //-------------------Retrieve All Container HATEOS LINKS----------------------------------------------

            List<ContainerResource> containerHateos = new ArrayList<ContainerResource>();

            List<Container> containers = terminal.getContainer();

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


            //-------------------NOW CREATE HATEOS LINKS for CONTAINER--------------------------------------------------------
            TerminalResource terminalResource = new TerminalResource
                    .Builder(terminal.getTerminalCode())
                    .resId(terminal.getId())
                    .teminalLocation(terminal.getTeminalLocation())
                    .containerLoad(terminal.getContainerLoad())
                    .containers(containerHateos)   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(TerminalController.class).getTerminal(terminalResource.getResId()))
                            .toString()).withSelfRel()
            );

            terminalResource.add(link);
            terminalHateos.add(terminalResource);
        }

        return new ResponseEntity<List<TerminalResource>>(terminalHateos, HttpStatus.OK);
    }
	
	
	 //-------------------Retrieve Single Container --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminalResource> getTerminal(@PathVariable("id") long id) {

		
        Terminal terminal = service.findById(id);

        if (terminal == null) {

            return new ResponseEntity<TerminalResource>(HttpStatus.NOT_FOUND);
        }


        List<ContainerResource> containerHateos = new ArrayList<ContainerResource>();

        List<Container> containers = terminal.getContainer();

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
                    linkTo(methodOn(ContainerController.class).getContainer(containerRes.getResId()))
                            .toString()).withRel("c" + containerRes.getResId())
            );


            containerRes.add(link);
            containerHateos.add(containerRes);
        }

        TerminalResource terminalResource = new TerminalResource
                .Builder(terminal.getTerminalCode())
                .resId(terminal.getId())
                .containerLoad(terminal.getContainerLoad())
                .teminalLocation(terminal.getTeminalLocation())
                .containers(containerHateos)   // get package products
                .build();

        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(TerminalController.class).getTerminal(terminalResource.getResId()))
                        .toString()).withSelfRel()
        );

        terminalResource.add(link);

        return new ResponseEntity<TerminalResource>(terminalResource, HttpStatus.OK);
    }
	
	
	//------------------- Create a Terminal --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createTerminal(@RequestBody Terminal terminal,    UriComponentsBuilder ucBuilder) {
    
        service.save(terminal);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/terminal/{id}").buildAndExpand(terminal.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
    //------------------- Update a Terminal --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Terminal> updateTerminal(@PathVariable("id") long id, @RequestBody Terminal newTerminal) {

        service.update(newTerminal);
        return new ResponseEntity<Terminal>(newTerminal, HttpStatus.OK);
    }
	
	//------------------- Delete a Terminal --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Terminal> deleteTerminal(@PathVariable("id") long id) {
      
        Terminal terminal = service.findById(id);
        if (terminal == null) {
            return new ResponseEntity<Terminal>(HttpStatus.NOT_FOUND);
        }

        service.delete(terminal);
        return new ResponseEntity<Terminal>(HttpStatus.NO_CONTENT);
    }	
}
