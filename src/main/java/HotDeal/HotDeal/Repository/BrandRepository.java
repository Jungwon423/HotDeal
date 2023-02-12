package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrandRepository extends MongoRepository<Brand, String> {
    List<Brand> findByType(Integer type);
}
