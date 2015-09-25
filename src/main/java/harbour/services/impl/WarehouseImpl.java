package harbour.services.impl;

import harbour.domain.Warehouse;
import harbour.repository.WarehouseRepository;
import harbour.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/16.
 */
@Service
public class WarehouseImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository repository;

    @Override
    public Warehouse findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Warehouse save(Warehouse entity) {
        return repository.save(entity);
    }

    @Override
    public Warehouse update(Warehouse entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Warehouse entity) {
        repository.delete(entity);
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = new ArrayList<Warehouse>();
        Iterable<Warehouse> values = repository.findAll();
        for (Warehouse value: values) {
            warehouses.add(value);
        }
        return warehouses;
    }
}
