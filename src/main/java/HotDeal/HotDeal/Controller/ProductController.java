package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Exception.Validator;
import HotDeal.HotDeal.Service.ProductService;
import HotDeal.HotDeal.Service.SearchService;
import com.taobao.api.ApiException;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
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

    //private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");

    private final ProductService productService;
    private final SearchService searchService;

    @PostMapping("click")
    public ResponseEntity<Map<String, Object>> clickProduct(HttpServletRequest request, @RequestParam String productId) {

        System.out.println("!"+productId+"!");
        String userId = (String) request.getAttribute("userId");
        if (userId==null){
            return productService.clickProduct(productId);
        }else return productService.clickProduct(productId, userId);
    }

    @GetMapping("detail")
    public ResponseEntity<Map<String, Object>> getProductDetail(@RequestParam String name) {
        System.out.println("!"+name+"!");
        return productService.getProductDetail(name);
    }

    @PostMapping("wishlist")
    public ResponseEntity<Map<String, Object>> setProductToWishlist(HttpServletRequest request, @RequestParam String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.setProductToWishlist(userId, productId);
    }

    @GetMapping("comments")
    public ResponseEntity<Map<String, Object>> getCommentsByProduct(@RequestParam String productId) {
        return productService.getCommentsByProduct(productId);
    }

    @PostMapping("recommend")
    public ResponseEntity<Map<String, Object>> recommendProduct(HttpServletRequest request, @RequestParam String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.recommendProduct(userId, productId);
    }
//
    //
    @PostMapping("disrecommend")
    public ResponseEntity<Map<String, Object>> disrecommendProduct(HttpServletRequest request, @RequestParam String productId) {
        String userId = (String) request.getAttribute("userId");
        Validator.checkIfLogin(userId);
        return productService.disrecommendProduct(userId, productId);
    }
    @PostMapping("{pageNumber}/search")
    public ResponseEntity<Map<String, Object>> searchProduct(@PathVariable("pageNumber") Integer pageNumber,@RequestParam String keyword) throws ParseException, ApiException {
        return searchService.searchProductAli(keyword,pageNumber);
    }
}
