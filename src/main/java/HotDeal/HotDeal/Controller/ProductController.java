package HotDeal.HotDeal.Controller;

import HotDeal.HotDeal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("{productId}/click")
    public ResponseEntity<Map<String, Object>> clickProduct(@PathVariable String productId) {
        return productService.clickProduct(productId);
    }
}
