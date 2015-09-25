package harbour.services.impl;

import harbour.domain.Terminal;
import harbour.repository.TerminalRepository;
import harbour.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/16.
 */
@Service
public class TerminalImpl implements TerminalService {
    @Autowired
    private TerminalRepository repository;

    @Override
    public Terminal findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Terminal save(Terminal entity) {
        return repository.save(entity);
    }

    @Override
    public Terminal update(Terminal entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Terminal entity) {
        repository.delete(entity);
    }

    @Override
    public List<Terminal> findAll() {
        List<Terminal> terminals = new ArrayList<Terminal>();
        Iterable<Terminal> values = repository.findAll();
        for (Terminal value: values) {
            terminals.add(value);
        }
        return terminals;
    }
}
