package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByMarketName(String MarketName);
}
