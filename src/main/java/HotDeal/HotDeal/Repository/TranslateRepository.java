package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Translate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslateRepository extends MongoRepository<Translate, String> {

}