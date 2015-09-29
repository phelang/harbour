package harbour.services.impl;

import harbour.domain.Dock;
import harbour.repository.DockRepository;
import harbour.services.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/16.
 */
@Service
public class DockImpl implements DockService {
    @Autowired
    private DockRepository repository;

    @Override
    public Dock findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Dock save(Dock entity) {
        return repository.save(entity);
    }

    @Override
    public Dock update(Dock entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Dock entity) {
        repository.delete(entity);
    }

    @Override
    public List<Dock> findAll() {
        List<Dock> docks = new ArrayList<Dock>();
        Iterable<Dock> values = repository.findAll();

        for(Dock value: values ){
            docks.add(value);
        }
        return docks;
    }
}
