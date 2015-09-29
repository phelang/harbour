package harbour.services.impl;

import harbour.domain.Port;
import harbour.repository.PortRepository;
import harbour.services.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *Author P.Qhu  on 2015/08/16.
 */
@Service
public class PortImpl implements PortService {

    @Autowired
    private PortRepository repository;

    @Override
    public Port findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Port save(Port entity) {
        return repository.save(entity);
    }

    @Override
    public Port update(Port entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Port entity) {
        repository.delete(entity);
    }

    @Override
    public List<Port> findAll() {
        List<Port> ports = new ArrayList<Port>();
        Iterable<Port> values = repository.findAll();
        for (Port value: values) {
            ports.add(value);
        }
        return ports;
    }
}
