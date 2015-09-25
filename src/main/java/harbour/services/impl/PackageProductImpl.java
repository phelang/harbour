package harbour.services.impl;

import harbour.domain.PackageProduct;
import harbour.repository.PackageProductRepository;
import harbour.services.PackageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/16.
 */
@Service
public class PackageProductImpl implements PackageProductService {
    @Autowired
    PackageProductRepository repository;

    @Override
    public PackageProduct findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public PackageProduct save(PackageProduct entity) {
        return repository.save(entity);
    }

    @Override
    public PackageProduct update(PackageProduct entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PackageProduct entity) {
        repository.delete(entity);
    }

    @Override
    public List<PackageProduct> findAll() {
        List<PackageProduct> allPackageProd = new ArrayList<PackageProduct>();
        Iterable<PackageProduct> pkgProducts = repository.findAll();
        for (PackageProduct pkgProd : pkgProducts) {
            allPackageProd.add(pkgProd);
        }
        return allPackageProd;
    }
}
