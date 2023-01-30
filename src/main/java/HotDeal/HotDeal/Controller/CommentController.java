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
    public ResponseEntity<Map<String, Object>> writeCommentToProduct(HttpServletRequest request, @PathVariable("productId")  String productId, @RequestBody Comment comment){
        //String userId = (String) request.getAttribute("userId");
        String userId="test";
        Validator.checkIfLogin(userId);
        return commentService.writeCommentToProduct(userId,productId,comment);
    }

    @GetMapping("{productId}/{commentId}/read")
    public String getProductsByCategoryAndMarket(HttpServletRequest request, @PathVariable("productId")  String productId, @PathVariable("commentId") String commentId) {
        return "댓글좀 써봐라 마";
    }
}
