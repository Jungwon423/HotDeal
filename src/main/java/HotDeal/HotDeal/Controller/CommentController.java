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

    @PostMapping("{productId}/write")
    public ResponseEntity<Map<String, Object>> writeCommentToProduct(HttpServletRequest request, @PathVariable("productId") String productId, @RequestBody Comment comment) {
        //String userId = (String) request.getAttribute("userId");
        String userId = "test";
        Validator.checkIfLogin(userId);
        return commentService.writeCommentToProduct(userId, productId, comment);
    }

    @PutMapping("{commentId}/edit")
    public ResponseEntity<Map<String, Object>> editCommentToProduct(HttpServletRequest request, @PathVariable("commentId") String commentId, @RequestBody Comment comment) {
        //String userId = (String) request.getAttribute("userId");
        String userId = "test";
        Validator.checkIfLogin(userId);
        return commentService.editCommentToProduct(userId, commentId, comment);
    }

    @DeleteMapping("{commentId}/delete")
    public ResponseEntity<Map<String, Object>> deleteCommentToProduct(HttpServletRequest request,@PathVariable("commentId") String commentId) {
        //String userId = (String) request.getAttribute("userId");
        String userId = "test";
        Validator.checkIfLogin(userId);
        return commentService.deleteCommentToProduct(userId,  commentId);
    }
}
