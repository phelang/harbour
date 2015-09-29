package harbour.services.impl;

import harbour.domain.Container;
import harbour.repository.ContainerRepository;
import harbour.services.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/16.
 */
@Service
public class ContainerImpl implements ContainerService {
    @Autowired
    private ContainerRepository repository;

    @Override
    public Container findById(Long id) {
        return  repository.findOne(id);
    }

    @Override
    public Container save(Container entity) {
        return repository.save(entity);
    }

    @Override
    public Container update(Container entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Container entity) {
        repository.delete(entity);
    }

    @Override
    public List<Container> findAll() {

        List<Container> containers = new ArrayList<Container>();
        Iterable<Container> values = repository.findAll();

        for(Container value: values ){
            containers.add(value);
        }
        return containers;
    }
}
