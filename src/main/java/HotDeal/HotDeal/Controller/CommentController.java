package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("write")
    public ResponseEntity<Map<String, Object>> writeCommentToProduct(HttpServletRequest request, @RequestParam String productId, @RequestBody Map<String, String> comment) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return commentService.writeCommentToProduct(userId, productId, comment.get("comment"));
    }

    @PutMapping("{commentId}/edit")
    public ResponseEntity<Map<String, Object>> editCommentToProduct(HttpServletRequest request, @PathVariable("commentId") String commentId, @RequestBody  Map<String, String> comment) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return commentService.editCommentToProduct(userId, commentId, comment.get("comment"));
    }

    @DeleteMapping("{commentId}/delete")
    public ResponseEntity<Map<String, Object>> deleteCommentToProduct(HttpServletRequest request,@PathVariable("commentId") String commentId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return commentService.deleteCommentToProduct(userId,  commentId);
    }
    @PostMapping("{commentId}/recommend")
    public ResponseEntity<Map<String, Object>> recommendComment(HttpServletRequest request,@PathVariable("commentId") String commentId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return commentService.recommendComment(userId,  commentId);
    }
    @PostMapping("{commentId}/disrecommend")
    public ResponseEntity<Map<String, Object>> disrecommendComment(HttpServletRequest request,@PathVariable("commentId") String commentId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return commentService.disrecommendComment(userId,  commentId);
    }
}
