package harbour.api;

import harbour.domain.PackageProduct;
import harbour.model.PackageProductResource;
import harbour.services.PackageProductService;
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
 * Author P.Qhu on 2015/09/16.
 */
@RestController
@RequestMapping("/packageproducts/")
public class PackageProductController {

    @Autowired
    private PackageProductService service;

    //-------------------Retrieve All PackageProducts--------------------------------------------------------

    @RequestMapping(value = "/packages/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PackageProductResource>> getAllPackageProd() {

        List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();
        List<PackageProduct> packageProducts = service.findAll();

        if(packageProducts.isEmpty()){
            return new ResponseEntity<List<PackageProductResource>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }



        for (PackageProduct pkgProd: packageProducts) {

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
                    linkTo(methodOn(PackageProductController.class))
                            .slash(pkgProdRes.getResId()).toString()).withSelfRel()
            );

            pkgProdRes.add(link);
            pkgHateos.add(pkgProdRes);
        }


        return new ResponseEntity<List<PackageProductResource>>(pkgHateos, HttpStatus.OK);


    }



    //-------------------Retrieve Single PackageProduct--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PackageProductResource> getPackageProduct(@PathVariable("id") long id) {

        PackageProduct packageProduct = service.findById(id);
        if (packageProduct == null) {

            return new ResponseEntity<PackageProductResource>(HttpStatus.NOT_FOUND);
        }


        PackageProductResource  pkgProdRes = new PackageProductResource.
                Builder(packageProduct.getPackageCode())
                .resid(packageProduct.getId())
                .description(packageProduct.getDescription())
                .itemType(packageProduct.getItemType())
                .packageDate(packageProduct.getPackageDate())
                .quantity(packageProduct.getQuantity())   // get package products
                .build();


        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(PackageProductController.class).getPackageProduct(pkgProdRes.getResId()))
                        .toString()).withSelfRel()
        );

        pkgProdRes.add(link);


        return new ResponseEntity<PackageProductResource>(pkgProdRes, HttpStatus.OK);

    }


    //-------------------Create a PackageProduct--------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createPackageProduct(@RequestBody PackageProduct packageProduct,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating PackageProduct " + packageProduct.getPackageCode());

//     USE THIS IF YOU WANT TO CHECK UNIQUE OBJECT
//      if (SubjectService.isSubjectExist(Subject)) {
//            System.out.println("A Subject with name " + Subject.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        service.save(packageProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/packageproducts/{id}").buildAndExpand(packageProduct.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }




    //------------------- Update a Subject --------------------------------------------------------

    // for me to update an object in reality I would use the first service -- getPackageProduct() to return a single object
    // the this object would be popped up on the screen for editing
    // after editing the I would call this updatePackage method passing the full object
    // for updating, The Course Project has a fault when it gets to this poit.

    /***
     *  1. for example you pass a new PackageProduct, without calling an object that exists alread.
     *
     *  2. The new object passed as a jason object cannot be update onto the existing object
     *  that is being read from the database without one overwriting others data.
     *
     *  3. The question is haw do you update an object from database with an objec passed
     *  onto the update function of which the only method is copy?
     *
     *  4. Trying to solve this, would violate the principle of programming
     *  thus I decide the course solution is untrustable and not possible
     *
     *  SOLUTIO - Use service,
     *
     *  1. getPackageProduct()
     *  2. Display this onto the screen for user to edit, and then
     *  4. Pass this object as complete and ready for update to
     *  service  updatePackage()
     *
     */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePackage(@RequestBody PackageProduct newPackageProduct) {

        /* an Object that is passed here must already be read from database using getPackageProduct
        and must be an existing object else a new object is inserted.
        */

        HttpHeaders headers = new HttpHeaders();
        service.update(newPackageProduct);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    //------------------- Delete a PackageProduct --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePackageProd(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting PackageProduct with id " + id);

        PackageProduct pgkProduct = service.findById(id);
        if (pgkProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(pgkProduct);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }
}
