package harbour.services.impl;
import harbour.domain.Cargo;
import harbour.repository.CargoRepository;
import harbour.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/16.
 */

@Service
public class CargoImpl implements CargoService {

    @Autowired
    private CargoRepository repository;

    @Override
    public Cargo findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Cargo save(Cargo entity) {
        return repository.save(entity);
    }

    @Override
    public Cargo update(Cargo entity) {
        return repository.save(entity);

    }

    @Override
    public void delete(Cargo entity) {
        repository.delete(entity);
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> cargos = new ArrayList<Cargo>();
        Iterable<Cargo> values = repository.findAll();

        for(Cargo value: values ){
            cargos.add(value);
        }
        return cargos;
    }
}
