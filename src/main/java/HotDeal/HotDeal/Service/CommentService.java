package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Exception.IdNotFoundException;
import HotDeal.HotDeal.Repository.CommentRepository;
import HotDeal.HotDeal.Repository.ProductRepository;
import HotDeal.HotDeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<Map<String, Object>> writeCommentToProduct(String userId, String productId, Comment comment) {
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                        .orElseThrow(IdNotFoundException::new);
        comment.setWriterId(userId);
        comment.setProductId(productId);
        commentRepository.save(comment);

        //List<Comment> comments = user.getComments();
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        user.setComments(comments);
        userRepository.save(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(IdNotFoundException::new);
        //List<Comment> comments = product.getComments();
        product.setComments(comments);
        productRepository.save(product);

        responseJson.put("message","댓글이 댓글 컬렉션에 저장되었습니다");
        responseJson.put("message2","댓글이 유저정보에 저장되었습니다");
        responseJson.put("message3","댓글이 제품정보에 저장되었습니다");
        //responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
}
