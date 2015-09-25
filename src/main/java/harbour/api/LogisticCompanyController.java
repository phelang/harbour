package harbour.api;

import harbour.domain.LogisticCompany;
import harbour.domain.PackageProduct;
import harbour.model.LogisticCompanyResource;
import harbour.model.PackageProductResource;
import harbour.services.LogisticCompanyService;
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
@RequestMapping(value="/logisticscompany/")
public class LogisticCompanyController {


    @Autowired
    private LogisticCompanyService service;


    //-------------------Retrieve All Logistic Companies--------------------------------------------------------
    @RequestMapping(value="/logisticcompanies/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LogisticCompanyResource>> getAllLogistiCompanies() {

        List<LogisticCompanyResource> logisticCompanyHateos = new ArrayList<LogisticCompanyResource>();

        List<LogisticCompany> logisticCompanies = service.findAll();



        for (LogisticCompany logCompany: logisticCompanies) {

            //-------------------Retrieve All PckageProducts HATEOS LINKS----------------------------------------------

            List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

            List<PackageProduct> packageProducts = logCompany.getPackagedProd();

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


            //-------------------NOW CREATE HATEOS LINKS for LogisticCompanies--------------------------------------------------------
            LogisticCompanyResource logisticRes = new LogisticCompanyResource
                    .Builder(logCompany.getCompanyCode())
                    .resid(logCompany.getId())
                    .name(logCompany.getName())
                    .tel(logCompany.getTel())
                    .email(logCompany.getEmail())
                    .packageProducts(pkgHateos)   // get package products
                    .build();

            Link link = (new

                    // create a link to this method on
                    Link(
                    linkTo(methodOn(LogisticCompanyController.class).getLogisticCompany(logisticRes.getResId()))
                            .toString()).withSelfRel()
            );

            logisticRes.add(link);
            logisticCompanyHateos.add(logisticRes);
        }

        return new ResponseEntity<List<LogisticCompanyResource>>(logisticCompanyHateos, HttpStatus.OK);
    }

    //-------------------Retrieve Single Logistic Company --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogisticCompanyResource> getLogisticCompany(@PathVariable("id") long id) {

        LogisticCompany logisticCompany = service.findById(id);

        if (logisticCompany == null) {

            return new ResponseEntity<LogisticCompanyResource>(HttpStatus.NOT_FOUND);
        }


        List<PackageProductResource> pkgHateos = new ArrayList<PackageProductResource>();

        List<PackageProduct> packageProducts = logisticCompany.getPackagedProd();

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

        LogisticCompanyResource logisticCompanyResourceRes = new LogisticCompanyResource
                .Builder(logisticCompany.getCompanyCode())
                .resid(logisticCompany.getId())
                .name(logisticCompany.getName())
                .tel(logisticCompany.getTel())
                .email(logisticCompany.getEmail())
                .packageProducts(pkgHateos)   // get package products
                .build();
				
        Link link = (new

                // create a link to this method on
                Link(
                linkTo(methodOn(LogisticCompanyController.class).getLogisticCompany(logisticCompanyResourceRes.getResId()))
                        .toString()).withSelfRel()
        );

        logisticCompanyResourceRes.add(link);


        return new ResponseEntity<LogisticCompanyResource>(logisticCompanyResourceRes, HttpStatus.OK);
    }



    //------------------- Create Logistic --------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createLogistic( @RequestBody LogisticCompany newCompany,  UriComponentsBuilder ucBuilder) {

        service.save(newCompany);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/container/{id}").buildAndExpand(newCompany.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a LogisticCompany --------------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LogisticCompany> updateLogistic(@PathVariable("id") long id, @RequestBody LogisticCompany newCompany) {

        service.update(newCompany);
        return new ResponseEntity<LogisticCompany>(newCompany, HttpStatus.OK);
    }

    //------------------- Delete a LogisticCompany --------------------------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<LogisticCompany> deleteLogistic(@PathVariable("id") long id) {

        LogisticCompany company = service.findById(id);
        if (company == null) {
            return new ResponseEntity<LogisticCompany>(HttpStatus.NOT_FOUND);
        }

        service.delete(company);
        return new ResponseEntity<LogisticCompany>(HttpStatus.NO_CONTENT);
    }




}
