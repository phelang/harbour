package harbour.services;

import java.util.List;

/**
 * Created by student on 2015/09/16.
 */
public interface Services <H, ID> {

    public H findById(ID id);

    public H save(H entity);

    public H update(H entity);

    public void delete(H entity);

    public List<H> findAll();
}
