package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");

    private final ProductService productService;

    @PostMapping("{productId}/click")
    public ResponseEntity<Map<String, Object>> clickProduct(HttpServletRequest request, @PathVariable String productId) {
        String userId = (String) request.getAttribute("userId");
        if (userId==null){
            return productService.clickProduct(productId);
        }else return productService.clickProduct(productId, userId);
    }

    @GetMapping("detail")
    public ResponseEntity<Map<String, Object>> getProductDetail(@RequestParam String name) {
        return productService.getProductDetail(name);
    }

    @PostMapping("{productId}/wishlist")   //테스트용 : Amazon Essentials 남아 및 유아용 방수 스노우 턱받이
    public ResponseEntity<Map<String, Object>> setProductToWishlist(HttpServletRequest request, @PathVariable String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.setProductToWishlist(userId, productId);
    }

    @GetMapping("{productId}/comments")
    public ResponseEntity<Map<String, Object>> getCommentsByProduct(@PathVariable("productId") String productId) {
        return productService.getCommentsByProduct(productId);
    }

    @PostMapping("{productId}/recommend")
    public ResponseEntity<Map<String, Object>> recommendProduct(HttpServletRequest request, @PathVariable String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.recommendProduct(userId, productId);
    }

    @PostMapping("{productId}/bad")
    public ResponseEntity<Map<String, Object>> badProduct(HttpServletRequest request, @PathVariable String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.badProduct(userId, productId);
    }
}
