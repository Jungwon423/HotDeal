package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExchangeRepository extends MongoRepository<ExchangeRate, String> {
    List <ExchangeRate> findByName(String name);
}