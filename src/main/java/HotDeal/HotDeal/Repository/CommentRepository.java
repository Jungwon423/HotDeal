package HotDeal.HotDeal.Repository;

import HotDeal.HotDeal.Domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CommentRepository extends MongoRepository<Comment, String>{
}
