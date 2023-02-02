package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Dto.WishListDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByNickname(String nickname);
}
