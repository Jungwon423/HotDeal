package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeRepository extends MongoRepository<ExchangeRate, String> {
    ExchangeRate findByName(String dollar);
}