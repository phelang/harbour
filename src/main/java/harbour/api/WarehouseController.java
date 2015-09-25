package harbour.api;

import harbour.domain.PackageProduct;
import harbour.domain.Warehouse;
import harbour.model.PackageProductResource;
import harbour.model.WarehouseResource;
import harbour.services.WarehouseService;
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
@RequestMapping(value="/warehouse/")
public class WarehouseController {
	
	@Autowired
    private WarehouseService service;


    //-------------------Retrieve All Warehouses--------------------------------------------------------
    @RequestMapping(value="/warehouses/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WarehouseResource>> getAllWarehouses() {

        List<WarehouseResource> warehouseHateos = new ArrayList<WarehouseResource>();

        List<Warehouse> warehouses = service.findAll();



        for (Warehouse warehouse: warehouses) {

            //-------------------Retrieve All Warehouse HATEOS LINKS----------------------------------------------

            List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

            List<PackageProduct> packageProducts = warehouse.getPackagedProd();

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
            WarehouseResource res = new WarehouseResource
                    .Builder(warehouse.getWarehouseCode())
                    .resId(warehouse.getId())
                    .name(warehouse.getName())
                    .packageLoad(warehouse.getPackageLoad())
                    .packageProducts(pkgHateos)   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(WarehouseController.class).getWarehouse(res.getResId()))
                            .toString()).withSelfRel()
            );

            res.add(link);
            warehouseHateos.add(res);
        }

        return new ResponseEntity<List<WarehouseResource>>(warehouseHateos, HttpStatus.OK);
    }
	
	//-------------------Retrieve Single Warehouse --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WarehouseResource> getWarehouse(@PathVariable("id") long id) {

        Warehouse warehouse = service.findById(id);

        if (warehouse == null) {

            return new ResponseEntity<WarehouseResource>(HttpStatus.NOT_FOUND);
        }


        List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

        List<PackageProduct> packageProducts = warehouse.getPackagedProd();

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

        WarehouseResource warehouseRes = new WarehouseResource
                .Builder(warehouse.getWarehouseCode())
                .resId(warehouse.getId())
                .name(warehouse.getName())
                .packageLoad(warehouse.getPackageLoad())
                .packageProducts(pkgHateos)   // get package products
                .build();

        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(WarehouseController.class).getWarehouse(warehouseRes.getResId()))
                        .toString()).withSelfRel()
        );

        warehouseRes.add(link);


        return new ResponseEntity<WarehouseResource>(warehouseRes, HttpStatus.OK);
    }

	
	//------------------- Update a Warehouse --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") long id, @RequestBody Warehouse newWarehouse) {

        service.update(newWarehouse);
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.OK);
    }
	
	//------------------- Delete a Container --------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createWarehouse(@RequestBody Warehouse warehouse,    UriComponentsBuilder ucBuilder) {
		
		service.save(warehouse);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/warehouse/{id}").buildAndExpand(warehouse.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable("id") long id) {

        Warehouse warehouse = service.findById(id);
        if (warehouse == null) {
            return new ResponseEntity<Warehouse>(HttpStatus.NOT_FOUND);
        }

        service.delete(warehouse);
        return new ResponseEntity<Warehouse>(HttpStatus.NO_CONTENT);
    }


	
}
