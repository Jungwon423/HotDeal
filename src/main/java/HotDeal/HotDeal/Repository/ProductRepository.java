package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    //@Query("{ 'id' : ?0}")
    Product findByName(String Name);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByCategoryName2Containing(String categoryName2);
    List<Product> findByCategoryNameContaining(String categoryName);

    List<Product> findByNameContaining(String Name);
    List<Product> findByMarketName(String marketName);
    List<Product> findByCategoryNameAndMarketName(String categoryName, String marketName);
}
