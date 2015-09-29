package harbour.api;

import harbour.domain.Container;
import harbour.domain.PackageProduct;
import harbour.model.ContainerResource;
import harbour.model.PackageProductResource;
import harbour.services.ContainerService;
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
 * Author P.Qhu  on 2015/09/16.
 */
@RestController
@RequestMapping(value="/container/")
public class ContainerController {

    @Autowired
    private ContainerService service;


    //-------------------Retrieve All Containers--------------------------------------------------------
    @RequestMapping(value="/containers/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContainerResource>> getAllContainers() {

        List<ContainerResource> containerHateos = new ArrayList<ContainerResource>();

        List<Container> containers = service.findAll();



        for (Container container: containers) {

            //-------------------Retrieve All PckageProducts HATEOS LINKS----------------------------------------------

            List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

            List<PackageProduct> packageProducts = container.getPackagedProd();

            for(PackageProduct pkgProd: packageProducts){       // create HATEOS Links for sub-entities


                PackageProductResource pkgProdRes = new PackageProductResource

                        .Builder(pkgProd.getPackageCode())
                        .resid(pkgProd.getId())
                        .description(pkgProd.getDescription())
                        .itemType(pkgProd.getItemType())
                        .packageDate(pkgProd.getPackageDate())
                        .quantity(pkgProd.getQuantity())   // get package products
                        .build();

                Link link = (new

                        // create a link to this method on
                        Link(
                        linkTo(methodOn(PackageProductController.class).getPackageProduct(pkgProd.getId()))
                                .toString()).withRel("pkg" + pkgProd.getId())
                );


                pkgProdRes.add(link);
                pkgHateos.add(pkgProdRes);
            }


            //-------------------NOW CREATE HATEOS LINKS for CONTAINER--------------------------------------------------------
            ContainerResource res = new ContainerResource
                    .Builder(container.getContainerCode())
                    .resid(container.getId())
                    .width(container.getWidth())
                    .height(container.getHeight())
                    .packageProducts(pkgHateos)   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(ContainerController.class).getContainer(res.getResId()))
                            .toString()).withSelfRel()
            );

            res.add(link);
            containerHateos.add(res);
        }

        return new ResponseEntity<List<ContainerResource>>(containerHateos, HttpStatus.OK);
    }

    //-------------------Retrieve Single Container --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResource> getContainer(@PathVariable("id") long id) {

        Container container = service.findById(id);

        if (container == null) {

            return new ResponseEntity<ContainerResource>(HttpStatus.NOT_FOUND);
        }


        List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

        List<PackageProduct> packageProducts = container.getPackagedProd();

        for(PackageProduct pkgProd: packageProducts){       // create HATEOS Links for sub-entities


            PackageProductResource pkgProdRes = new PackageProductResource

                    .Builder(pkgProd.getPackageCode())
                    .resid(pkgProd.getId())
                    .description(pkgProd.getDescription())
                    .itemType(pkgProd.getItemType())
                    .packageDate(pkgProd.getPackageDate())
                    .quantity(pkgProd.getQuantity())   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(PackageProductController.class).getPackageProduct(pkgProd.getId()))
                            .toString()).withRel("pkg" + pkgProd.getId())
            );


            pkgProdRes.add(link);
            pkgHateos.add(pkgProdRes);
        }

        ContainerResource containerRes = new ContainerResource
                .Builder(container.getContainerCode())
                .resid(container.getId())
                .width(container.getWidth())
                .height(container.getHeight())
                .packageProducts(pkgHateos)   // get package products
                .build();

        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(ContainerController.class).getContainer(containerRes.getResId()))
                        .toString()).withSelfRel()
        );

        containerRes.add(link);


        return new ResponseEntity<ContainerResource>(containerRes, HttpStatus.OK);
    }
	
	

	//------------------- Create a Container --------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteContainer(@RequestBody Container container,    UriComponentsBuilder ucBuilder) {

	    service.save(container);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/container/{id}").buildAndExpand(container.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	

    //------------------- Update a Container --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Container> updateContainer(@PathVariable("id") long id, @RequestBody Container newContainer) {

         service.update(newContainer);
        return new ResponseEntity<Container>(newContainer, HttpStatus.OK);
    }
	
	//------------------- Delete a Container --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Container> deleteContainer(@PathVariable("id") long id) {
      
        Container container = service.findById(id);
        if (container == null) {
            return new ResponseEntity<Container>(HttpStatus.NOT_FOUND);
        }

        service.delete(container);
        return new ResponseEntity<Container>(HttpStatus.NO_CONTENT);
    }	

}

