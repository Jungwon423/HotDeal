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

    public ResponseEntity<Map<String, Object>> editCommentToProduct(String userId, String commentId, Comment editComment) {
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        Comment beforeComment = commentRepository.findById(commentId)
                .orElseThrow(IdNotFoundException::new);
        editComment.setWriterId(userId);
        editComment.setProductId(beforeComment.getProductId());
        commentRepository.save(editComment);
        //원래 댓글 삭제하고 새로 저장?

        List<Comment> comments = user.getComments();
        comments.remove(beforeComment);
        comments.add(editComment);
        user.setComments(comments);
        userRepository.save(user);

        Product product = productRepository.findById(beforeComment.getProductId())
                .orElseThrow(IdNotFoundException::new);
        List<Comment> productComments = product.getComments();
        productComments.remove(beforeComment);
        productComments.add(editComment);
        product.setComments(comments);
        productRepository.save(product);
        commentRepository.delete(beforeComment);

        responseJson.put("message","댓글이 수정되었습니다");
        responseJson.put("message2","수정된 댓글이 유저정보에 저장되었습니다");
        responseJson.put("message3","수정된 댓글이 제품정보에 저장되었습니다");
        //responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> deleteCommentToProduct(String userId, String commentId) {
        Map<String, Object> responseJson = new HashMap<>();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(IdNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        user.getComments().remove(comment);
        userRepository.save(user);

        Product product = productRepository.findById(comment.getProductId())
                .orElseThrow(IdNotFoundException::new);
        product.getComments().remove(comment);
        productRepository.save(product);
        commentRepository.deleteById(commentId);  //삭제

        responseJson.put("message","댓글이 삭제되었습니다");
        responseJson.put("message2","댓글이 유저정보에서 삭제되었습니다");
        responseJson.put("message3","댓글이 제품정보에서 삭제되었습니다");
        //responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> recommendComment(String userId, String commentId) {
        Map<String, Object> responseJson = new HashMap<>();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(IdNotFoundException::new);
        List<String> comments = comment.getGood();
        if (comments.contains(userId)) {   //이미 포함하고 있으면 삭제
            comments.remove(userId);
        } else {    //아니면 추가
            comments.add(userId);
        }
        comment.setGood(comments);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public ResponseEntity<Map<String, Object>> disrecommendComment(String userId, String commentId) {
        Map<String, Object> responseJson = new HashMap<>();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(IdNotFoundException::new);
        List<String> comments = comment.getBad();
        if (comments.contains(userId)) {   //이미 포함하고 있으면 삭제
            comments.remove(userId);
        } else {    //아니면 추가
            comments.add(userId);
        }
        comment.setBad(comments);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
}
