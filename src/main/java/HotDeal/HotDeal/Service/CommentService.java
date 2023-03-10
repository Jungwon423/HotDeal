package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Domain.Product;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Exception.CommentNotFound;
import HotDeal.HotDeal.Exception.ProductNotFound;
import HotDeal.HotDeal.Exception.UserNotFound;
import HotDeal.HotDeal.Repository.CommentRepository;
import HotDeal.HotDeal.Repository.ProductRepository;
import HotDeal.HotDeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<Map<String, Object>> writeCommentToProduct(String userId, String productId, String commentContent) {
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                        .orElseThrow(UserNotFound::new);
        Comment comment = new Comment();
        comment.setTimestamp(getTimeNow());
        comment.setWriterId(userId);
        comment.setProductId(productId);
        comment.setContent(commentContent);
        commentRepository.save(comment);

        List<Comment> comments = user.getComments();
        comments.add(comment);
        user.setComments(comments);
        userRepository.save(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);
        List<Comment> productComments = product.getComments();
        productComments.add(comment);
        product.setComments(productComments);
        productRepository.save(product);

        responseJson.put("message","댓글이 댓글 컬렉션에 저장되었습니다");
        responseJson.put("message2","댓글이 유저정보에 저장되었습니다");
        responseJson.put("message3","댓글이 제품정보에 저장되었습니다");
        //responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> editCommentToProduct(String userId, String commentId, String editCommentContent) {
        Map<String, Object> responseJson = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        Comment beforeComment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        Comment editComment = new Comment();
        editComment.setTimestamp(getTimeNow());
        editComment.setWriterId(userId);
        editComment.setProductId(beforeComment.getProductId());
        editComment.setContent(editCommentContent);
        commentRepository.save(editComment);
        //원래 댓글 삭제하고 새로 저장?

        List<Comment> comments = user.getComments();
        comments.remove(beforeComment);
        comments.add(editComment);
        user.setComments(comments);
        userRepository.save(user);

        Product product = productRepository.findById(beforeComment.getProductId())
                .orElseThrow(ProductNotFound::new);
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
                .orElseThrow(CommentNotFound::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
        user.getComments().remove(comment);
        userRepository.save(user);

        Product product = productRepository.findById(comment.getProductId())
                .orElseThrow(ProductNotFound::new);
        product.getComments().remove(comment);
        productRepository.save(product);
        commentRepository.delete(comment);
        //commentRepository.deleteById(commentId);  //삭제

        responseJson.put("message","댓글이 삭제되었습니다");
        responseJson.put("message2","댓글이 유저정보에서 삭제되었습니다");
        responseJson.put("message3","댓글이 제품정보에서 삭제되었습니다");
        responseJson.put("comments", product.getComments());
        //responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> deleteSelectedComments(String userId, List<String> selectedComments) {
        Map<String, Object> responseJson = new HashMap<>();

        for (String selectedComment : selectedComments) {
            deleteCommentToProduct(userId, selectedComment);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        responseJson.put("message", "선택된 댓글이 삭제되었습니다");
        responseJson.put("user_comments",user.getComments());   //사용자 comments 보내주기
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> recommendComment(String userId, String commentId) {
        Map<String, Object> responseJson = new HashMap<>();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        List<String> commentUsers = comment.getGoodUser();

        boolean checkIfRecommend = commentUsers.contains(userId);
        if (!checkIfRecommend){
            commentUsers.add(userId);
            responseJson.put("message","댓글 추천한 유저아이디 추가함");
        }
        else{
            commentUsers.remove(userId);
            responseJson.put("message","댓글 추천한 유저아이디 삭제함");
        }
        comment.setGoodUser(commentUsers);
        commentRepository.save(comment);

        Product product = productRepository.findById(comment.getProductId())
                        .orElseThrow(ProductNotFound::new);
        List<Comment> productComments = product.getComments();   //제품에서 댓글 목록 불러와서

        Comment matchComment = productComments.stream()    //현재 수정하려는 댓글 ID를 찾는다.
                .filter(productComment -> comment.getId().equals(productComment.getId()))
                .findAny()
                .orElse(null); 
        productComments.remove(matchComment);       //수정 전 댓글을 지우고
        productComments.add(comment);           //수정 후 댓글 삽입
        product.setComments(productComments);
        productRepository.save(product);
        
        responseJson.put("users",commentUsers);
        responseJson.put("recommendCheck",!checkIfRecommend);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public ResponseEntity<Map<String, Object>> disrecommendComment(String userId, String commentId) {
        Map<String, Object> responseJson = new HashMap<>();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        List<String> commentUsers = comment.getBadUser();

        boolean checkIfDisrecommend = commentUsers.contains(userId);
        if (!checkIfDisrecommend){
            commentUsers.add(userId);
            responseJson.put("message","댓글 비추한 유저아이디 추가함");
        }
        else{
            commentUsers.remove(userId);
            responseJson.put("message","댓글 비추한 유저아이디 삭제함");
        }
        comment.setBadUser(commentUsers);
        commentRepository.save(comment);

        Product product = productRepository.findById(comment.getProductId())
                .orElseThrow(ProductNotFound::new);
        List<Comment> productComments = product.getComments();   //제품에서 댓글 목록 불러와서

        Comment matchComment = productComments.stream()    //현재 수정하려는 댓글 ID를 찾는다.
                .filter(productComment -> comment.getId().equals(productComment.getId()))
                .findAny()
                .orElse(null);
        productComments.remove(matchComment);       //수정 전 댓글을 지우고
        productComments.add(comment);           //수정 후 댓글 삽입
        product.setComments(productComments);
        productRepository.save(product);

        responseJson.put("users",commentUsers);
        responseJson.put("disrecommendCheck",!checkIfDisrecommend);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    public String getTimeNow(){
        LocalDateTime now = LocalDateTime.now();
        // 포맷팅
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        return formatedNow;
    }
}
