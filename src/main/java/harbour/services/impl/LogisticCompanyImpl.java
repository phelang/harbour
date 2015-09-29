package harbour.services.impl;

import harbour.domain.LogisticCompany;
import harbour.repository.LogisticCompanyRepository;
import harbour.services.LogisticCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/16.
 */
@Service
public class LogisticCompanyImpl implements LogisticCompanyService {

    @Autowired
    private LogisticCompanyRepository repository;

    @Override
    public LogisticCompany findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public LogisticCompany save(LogisticCompany entity) {
        return repository.save(entity);
    }

    @Override
    public LogisticCompany update(LogisticCompany entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(LogisticCompany entity) {
        repository.delete(entity);
    }

    @Override
    public List<LogisticCompany> findAll() {
        List<LogisticCompany> logisticCompanies = new ArrayList<LogisticCompany>();
        Iterable<LogisticCompany> values  = repository.findAll();
        for (LogisticCompany value : values) {
            logisticCompanies.add(value);
        }
        return logisticCompanies;
    }
}
