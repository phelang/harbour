package harbour.repository;

import harbour.domain.PackageProduct;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by student on 2015/08/12.
 */
public interface PackageProductRepository extends CrudRepository<PackageProduct, Long> {
}
