package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String Name);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByMarketName(String marketName);
    List<Product> findByCategoryNameAndMarketName(String categoryName, String marketName);
}
