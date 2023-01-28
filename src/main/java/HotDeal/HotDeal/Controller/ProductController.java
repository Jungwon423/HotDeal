package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");

    private final ProductService productService;

    @PostMapping("{productId}/click")
    public ResponseEntity<Map<String, Object>> clickProduct(@PathVariable String productId) {
        return productService.clickProduct(productId);
    }

    @GetMapping("detail")
    public ResponseEntity<Map<String, Object>> getProductDetail(@RequestParam String name) {
        return productService.getProductDetail(name);
    }
}
